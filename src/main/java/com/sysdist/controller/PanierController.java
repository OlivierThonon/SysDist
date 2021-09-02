package com.sysdist.controller;

import com.sysdist.models.Article;
import com.sysdist.models.Panier;
import com.sysdist.models.PanierArticle;
import com.sysdist.models.Users;
import com.sysdist.repositories.ArticleRepository;
import com.sysdist.repositories.PanierArticleRepository;
import com.sysdist.repositories.PanierRepository;
import com.sysdist.repositories.UserRepository;
import net.minidev.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Controller
public class PanierController {

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PanierArticleRepository panierArticleRepository;

    @PostMapping("/panier")
    public String addtiem(@RequestParam(value = "idarticle") Long id, @RequestParam(value = "nbArticle") int quantite , HttpServletRequest request, Model model)
    {
        Optional<Users> user = userRepository.findById(request.getRemoteUser());

        //Vérifier que l'user est bien OK
        if(user.isPresent())
        {
            //Récupération de l'article et du user donc on va avoir besoin
            Article article = articleRepository.findById(id).get();

            if(article.getQuantite() >= quantite)
            {
                Panier panier = panierRepository.findPanierByUser(user.get());

                //Vérification si panier déjà existant. Si non, créer nouveau
                if(panier == null)
                {
                    panierRepository.save(new Panier(user.get()));

                    panier = panierRepository.findPanierByUser(user.get());
                }

                //Mise a jour de la quantité disponible
                article.setQuantite(article.getQuantite() - quantite);
                articleRepository.save(article);

                //Vérification si article déjà dans panier. Si oui, ajout de la quantité
                Optional<PanierArticle> panierArticle = panierArticleRepository.findPanierArticleByPanierAndArticle(panier, article);

                if(panierArticle.isPresent())
                    quantite += panierArticle.get().getQuantite();


                //Sauvegarde du nouvel achat
                panierArticleRepository.save(new PanierArticle(article, panier, quantite));
            }

            model.addAttribute("articles", articleRepository.findAll());

        }
        return "index";
    }

    @GetMapping("/panier")
    public String getPanier(HttpServletRequest request, Model model)
    {
        ArrayList<Article> articles = new ArrayList<Article>();
        Set <PanierArticle> panier = panierArticleRepository.findPanierArticleByPanier(panierRepository.findPanierByUser(userRepository.findById(request.getRemoteUser()).get()));
        panier.forEach((PanierArticle pa) -> {
            Article tmp = articleRepository.findById(pa.getArticle().getId()).get();
            tmp.setQuantite(pa.getQuantite());

            articles.add(tmp);
        });

        model.addAttribute("panier", articles);

        return"panier";
    }

    @PostMapping("/panierdel")
    public String deleteArticleFromPanier(@RequestParam(value = "idarticle", defaultValue = "0") Long id, @RequestParam(value = "all", defaultValue = "false") boolean all, HttpServletRequest request, Model model)
    {
        Users user = userRepository.findById(request.getRemoteUser()).get();

        PanierArticle panierArticle = panierArticleRepository.findPanierArticleByPanierAndArticle(panierRepository.findPanierByUser(user), articleRepository.findById(id).get()).get();

        Article article = articleRepository.findById(panierArticle.getArticle().getId()).get();
        article.setQuantite(article.getQuantite() + panierArticle.getQuantite());
        articleRepository.save(article);

        panierArticleRepository.delete(panierArticle);

        ArrayList<Article> articles = new ArrayList<Article>();
        Set<PanierArticle> panierArticleSet = panierArticleRepository.findPanierArticleByPanier(panierRepository.findPanierByUser(user));

        panierArticleSet.forEach((PanierArticle pa) -> {
            Article tmp = articleRepository.findById(pa.getArticle().getId()).get();
            tmp.setQuantite(pa.getQuantite());

            articles.add(tmp);
        });

        model.addAttribute("panier", articles);

        return"panier";
    }

}
