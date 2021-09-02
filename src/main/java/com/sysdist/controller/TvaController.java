package com.sysdist.controller;

import com.sysdist.models.Tva;
import com.sysdist.repositories.TvaRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TvaController {

    @Autowired
    TvaRepository tvaRepository;

    @GetMapping("/tva")
    public String getTva(@RequestParam(value = "categorie") String categorie)
    {
        Tva tva = tvaRepository.findTvaByCategorie(categorie);

        JSONObject rep = new JSONObject();

        if(tva != null)
            rep.put("tva", tva.getTvaValue());
        else
            rep.put("tva", 21);

        return rep.toString();
    }
}
