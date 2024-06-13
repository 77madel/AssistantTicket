package com.odk.assistantticket.repository;

import com.odk.assistantticket.model.Categorie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategorieRepository extends CrudRepository<Categorie, Long> {
    Optional<Categorie> findById(Long id);
}
