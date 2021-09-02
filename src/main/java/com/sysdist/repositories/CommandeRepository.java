package com.sysdist.repositories;

import com.sysdist.models.Article;
import com.sysdist.models.Commande;
import org.springframework.data.repository.CrudRepository;

public interface CommandeRepository extends CrudRepository<Commande, Long> {
}
