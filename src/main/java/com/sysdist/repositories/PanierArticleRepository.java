package com.sysdist.repositories;

import com.sysdist.models.Panier;
import com.sysdist.models.PanierArticle;
import com.sysdist.models.PanierArticleKey;
import org.springframework.data.repository.CrudRepository;

public interface PanierArticleRepository extends CrudRepository<PanierArticle, PanierArticleKey> {

}
