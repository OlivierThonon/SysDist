package com.sysdist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about()
    {
        return "about";
    }
}
