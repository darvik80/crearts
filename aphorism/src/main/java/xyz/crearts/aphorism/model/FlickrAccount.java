package xyz.crearts.aphorism.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author ivan.kishchenko
 */

@Entity
public class FlickrAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String appKey;
    private String appSecret;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private Set<FlickrPhotoSet> photoSets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Set<FlickrPhotoSet> getPhotoSets() {
        return photoSets;
    }

    public void setPhotoSets(Set<FlickrPhotoSet> photoSets) {
        this.photoSets = photoSets;
    }
}
