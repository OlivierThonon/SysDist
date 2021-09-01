package com.sysdist.repositories;

import com.sysdist.models.Article;
import com.sysdist.models.Panier;
import com.sysdist.models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PanierRepository extends CrudRepository<Panier, Long> {

    Panier findPanierByUser(Users user);

}
