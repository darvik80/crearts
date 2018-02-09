package xyz.crearts.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.service.model.FlickrAccount;

@RepositoryRestResource(collectionResourceRel = "flickr-account", path = "flickr-account")
public interface FlickrAccountRepository extends JpaRepository<FlickrAccount, Long> {
}
