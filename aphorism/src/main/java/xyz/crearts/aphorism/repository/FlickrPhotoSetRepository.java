package xyz.crearts.aphorism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.crearts.aphorism.model.EnvelopmentValue;
import xyz.crearts.aphorism.model.FlickrPhotoSet;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "flickr-photo-set", path = "flickr-photo-set")
public interface FlickrPhotoSetRepository extends JpaRepository<FlickrPhotoSet, Long> {
}
