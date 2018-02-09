package xyz.crearts.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.service.model.EnvelopmentValue;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "env-values", path = "env-values")
public interface EnvelopmentValueRepository extends JpaRepository<EnvelopmentValue, Long> {

    Optional<EnvelopmentValue> findByName(String name);
}
