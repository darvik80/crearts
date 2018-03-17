package xyz.crearts.service.model.flickr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
class Content {
    String _content;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoSetInfo {
    private String id;
    private String owner;
    private String username;
    private String primary;
    private String secret;
    private String server;
    private String farm;
    private Integer photos;
    @JsonProperty("count_views")
    private Integer countViews;
    @JsonProperty("count_comments")
    private Integer countComments;
    @JsonProperty("count_photos")
    private Integer countPhotos;
    private Content title;
    private Content description;
    @JsonProperty("can_comment")
    private byte canComment;
    @JsonProperty("date_create")
    private Date dateCreate;
    @JsonProperty("date_update")
    private Date dateUpdate;
}
