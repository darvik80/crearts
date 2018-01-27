package xyz.crearts.aphorism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.aphorism.model.Menu;

/**
 * @author ivan.kishchenko
 */
@RepositoryRestResource(collectionResourceRel = "menu", path = "menu")
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
