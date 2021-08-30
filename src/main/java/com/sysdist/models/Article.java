package com.sysdist.models;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String categorie;
    private int quantite;
    private float prix;


    public Article() {

    }

    public Article(String nom, String categorie, int quantite, float prix) {
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}