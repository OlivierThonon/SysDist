package com.sysdist.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categorie;
    private float TvaValue;

    public Tva(String categorie, float tvaValue) {
        this.categorie = categorie;
        TvaValue = tvaValue;
    }

    public Tva() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getTvaValue() {
        return TvaValue;
    }

    public void setTvaValue(float tvaValue) {
        TvaValue = tvaValue;
    }
}
