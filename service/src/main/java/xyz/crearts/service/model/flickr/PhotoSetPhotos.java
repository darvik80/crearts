package xyz.crearts.service.model.flickr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoSetPhotos {
    private String id;
    private String owner;
    private String ownername;
    private Integer photos;
    private String title;
    private List<PhotoSetPhoto> photo;
    private Integer total;

    private Integer page;
    @JsonProperty("per_page")
    private Integer perPage;
    private Integer pages;
}
