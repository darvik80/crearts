package xyz.crearts.aphorism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crearts.aphorism.model.Aphorism;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "aphorism", path = "aphorism")
public interface AphorismRepository extends JpaRepository<Aphorism, Long> {
}
