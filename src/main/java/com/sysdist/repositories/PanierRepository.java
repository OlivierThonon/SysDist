package com.sysdist.repositories;

import com.sysdist.models.Article;
import com.sysdist.models.Panier;
import com.sysdist.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PanierRepository extends CrudRepository<Panier, Long> {

    @Query("select p from Panier p where p.user = :#{#user} and p.commande is null")
    Panier findPanierByUser(Users user);

    /*
    @Query(
           "SELECT p FROM panier p WHERE p.user_username = user AND p.commande_id IS NULL"
    )*/


}
