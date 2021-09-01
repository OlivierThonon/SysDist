package com.sysdist.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Users user;

    @OneToMany(mappedBy = "panier")
    Set<PanierArticle> achats;

    public Panier(Users user) {
        this.user = user;
    }

    public Panier() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users User) {
        this.user = User;
    }

    public Set<PanierArticle> getAchats() {
        return achats;
    }

    public void setArticles(Set<PanierArticle> achats) {
        this.achats = achats;
    }
}