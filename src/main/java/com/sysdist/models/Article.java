package com.sysdist.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String categorie;
    private int quantite;
    private float prix;

    @OneToMany(mappedBy = "article")
    Set<PanierArticle> achats;


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

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Set<PanierArticle> getAchats() {
        return achats;
    }

    public void setAchats(Set<PanierArticle> achats) {
        this.achats = achats;
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