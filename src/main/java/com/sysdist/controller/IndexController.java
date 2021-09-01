package com.sysdist.controller;

import com.sysdist.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("articles", articleRepository.findAll());
        return "index";
    }

}