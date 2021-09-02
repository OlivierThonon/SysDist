package com.sysdist.repositories;

import com.sysdist.models.Article;
import com.sysdist.models.Panier;
import com.sysdist.models.PanierArticle;
import com.sysdist.models.PanierArticleKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface PanierArticleRepository extends CrudRepository<PanierArticle, PanierArticleKey> {
    Set<PanierArticle> findPanierArticleByPanier (Panier panier);
    Optional<PanierArticle> findPanierArticleByPanierAndArticle (Panier panier, Article article);
}
