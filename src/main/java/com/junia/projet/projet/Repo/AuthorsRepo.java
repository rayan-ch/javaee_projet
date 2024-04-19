package com.junia.projet.projet.Repo;

import com.junia.projet.projet.Entities.Authors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepo extends CrudRepository<Authors, Long> {
    Authors findAllById(Long id);
    Authors findByEmail(String email);
}
