package com.junia.projet.projet.Repo;

import com.junia.projet.projet.Entities.Authors;
import com.junia.projet.projet.Entities.Tutorials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorialsRepo extends CrudRepository<Tutorials, Long> {
    Optional<Tutorials> findById(Long id);
    List<Tutorials> findByAuthorId(Long authorId);
}
