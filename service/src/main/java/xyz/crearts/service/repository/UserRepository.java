package xyz.crearts.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.service.model.Aphorism;
import xyz.crearts.service.model.EnvelopmentValue;
import xyz.crearts.service.model.User;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String name);
}
