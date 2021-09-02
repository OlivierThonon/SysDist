package com.sysdist.models;

import javax.persistence.*;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float prixtotal;

    @OneToOne
    private Panier panier;

    public Commande(float prixtotal, Panier panier) {
        this.prixtotal = prixtotal;
        this.panier = panier;
    }

    public Commande() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(float prixtotal) {
        this.prixtotal = prixtotal;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", prixtotal=" + prixtotal +
                ", panier=" + panier +
                '}';
    }
}
