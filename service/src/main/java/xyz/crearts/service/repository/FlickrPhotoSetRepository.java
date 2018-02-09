package xyz.crearts.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.service.model.FlickrPhotoSet;

@RepositoryRestResource(collectionResourceRel = "flickr-photo-set", path = "flickr-photo-set")
public interface FlickrPhotoSetRepository extends JpaRepository<FlickrPhotoSet, Long> {
}
