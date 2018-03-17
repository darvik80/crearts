package xyz.crearts.service.model.flickr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhotoSetPhoto {
    private String id;
    private String title;
    private Boolean isPrimary;
    private Boolean isPublic;
    private Boolean isFriend;
    private Boolean isFamily;
    @JsonProperty("url_o")
    private String url;
    @JsonProperty("height_o")
    private Integer height;
    @JsonProperty("width_o")
    private Integer width;
}
