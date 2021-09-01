package com.sysdist.controller;

import com.sysdist.models.Article;
import com.sysdist.models.Panier;
import com.sysdist.models.PanierArticle;
import com.sysdist.models.Users;
import com.sysdist.repositories.ArticleRepository;
import com.sysdist.repositories.PanierArticleRepository;
import com.sysdist.repositories.PanierRepository;
import com.sysdist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

        if(user.isPresent())
        {
            Article article = articleRepository.findById(id).get();

            Panier panier = panierRepository.findPanierByUser(user.get());

            if(panier == null)
            {
                panierRepository.save(new Panier(user.get()));

                panier = panierRepository.findPanierByUser(user.get());
            }

            panierArticleRepository.save(new PanierArticle(article, panier, quantite));

            article.setQuantite(article.getQuantite() - quantite);
            articleRepository.save(article);

            model.addAttribute("articles", articleRepository.findAll());

        }
        return "index";
    }

    @GetMapping("/panier")
    public String getPanier(HttpServletRequest request, Model model)
    {
        return"panier";
    }

}
