package com.sysdist.models;

import javax.persistence.*;

@Entity
public class PanierArticle {
    @EmbeddedId
    PanierArticleKey id;

    @ManyToOne
    @MapsId("articleId")
    @JoinColumn(name="article_id")
    Article article;

    @ManyToOne
    @MapsId("panierId")
    @JoinColumn(name="panier_id")
    Panier panier;

    int Quantite;

    public PanierArticle()
    {

    }

    public PanierArticle(Article article, Panier panier, int quantite) {
        this.article = article;
        this.panier = panier;
        Quantite = quantite;
        id = new PanierArticleKey(article.getId(), panier.getId());
    }

    public PanierArticleKey getId() {
        return id;
    }

    public void setId(PanierArticleKey id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }
}
