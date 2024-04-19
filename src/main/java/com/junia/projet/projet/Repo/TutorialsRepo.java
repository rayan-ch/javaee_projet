package com.junia.projet.projet.Repo;

import com.junia.projet.projet.Entities.Authors;
import com.junia.projet.projet.Entities.Tutorials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialsRepo extends CrudRepository<Tutorials, Long> {
    List<Tutorials> findByAuthorId(Long authorId);
}
