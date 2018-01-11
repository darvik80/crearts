package xyz.crearts.aphorism.model;

import javax.persistence.*;

/**
 * @author ivan.kishchenko
 */

@Entity
public class FlickrPhotoSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String albumId;
    private String description;

    @ManyToOne
    private FlickrAccount account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FlickrAccount getAccount() {
        return account;
    }

    public void setAccount(FlickrAccount account) {
        this.account = account;
    }
}
