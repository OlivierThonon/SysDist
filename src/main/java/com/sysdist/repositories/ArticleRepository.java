package com.sysdist.repositories;

import com.sysdist.models.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}