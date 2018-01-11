package xyz.crearts.aphorism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.aphorism.model.EnvelopmentValue;
import xyz.crearts.aphorism.model.FlickrAccount;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "flickr-account", path = "flickr-account")
public interface FlickrAccountRepository extends JpaRepository<FlickrAccount, Long> {
}
