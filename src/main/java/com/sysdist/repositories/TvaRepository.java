package com.sysdist.repositories;

import com.sysdist.models.Tva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvaRepository extends JpaRepository<Tva, Long> {

    Tva findTvaByCategorie(String categorie);

}
