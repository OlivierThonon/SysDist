package com.sysdist.controller;

import com.sysdist.models.*;
import com.sysdist.repositories.*;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class CheckoutController {

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PanierArticleRepository panierArticleRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @GetMapping("/checkout")
    public String checkout(HttpServletRequest request, Model model)
    {
        Users user = userRepository.findById(request.getRemoteUser()).get();

        Set<PanierArticle> panierArticleSet = panierArticleRepository.findPanierArticleByPanier(panierRepository.findPanierByUser(user));
        ArrayList<Article> articles = new ArrayList<Article>();

        panierArticleSet.forEach((PanierArticle pa) -> {
            Article tmp = articleRepository.findById(pa.getArticle().getId()).get();

            tmp.setQuantite(pa.getQuantite());

            String url = "http://localhost:50000/tva/?categorie=" + tmp.getCategorie();


            HttpGet requestHttp = new HttpGet(url);
            requestHttp.addHeader("custom-key", "mkyong");
            requestHttp.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

            CloseableHttpClient httpClient = HttpClients.createDefault();

            try
            {
                CloseableHttpResponse response = httpClient.execute(requestHttp);
                String rep = EntityUtils.toString(response.getEntity());
                System.out.printf("BODY.TOSTRING() : " + rep);
                JSONParser parser = new JSONParser();
                JSONObject resp = (JSONObject) parser.parse(rep);

                tmp.setPrix(tmp.getPrix() + tmp.getPrix()*Float.parseFloat(resp.get("tva").toString())/100);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            articles.add(tmp);
        });

        float prixtotal = 0;
        for(int i = 0; i<articles.size(); i++)
        {
            prixtotal += articles.get(i).getPrix()*articles.get(i).getQuantite();
            System.out.printf("Prix total : " + prixtotal);
        }


        model.addAttribute("panier", articles);
        model.addAttribute("prixtotal", prixtotal);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String pay(@RequestParam(value = "prixtotal") float prix, @RequestParam(value = "prixlivraison") int prixlivraison, HttpServletRequest request, Model model)
    {
        Users user = userRepository.findById(request.getRemoteUser()).get();
        Panier panier = panierRepository.findPanierByUser(user);
        float prixfinal = prix+prixlivraison;

        System.out.printf("User solde : " + user.getSolde() + " prixfinal : " + prixfinal);
        if(prixfinal <= user.getSolde())
        {
            System.out.printf("Solde OK");
            Commande commande = new Commande(prixfinal, panier);
            commandeRepository.save(commande);

            panier.setCommande(commande);
            panierRepository.save(panier);
        }
        else
        {
            System.out.printf("Solde NOK");
        }

        model.addAttribute("articles", articleRepository.findAll());
        return "index";
    }

}
