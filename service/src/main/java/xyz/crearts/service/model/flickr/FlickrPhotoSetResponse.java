package xyz.crearts.service.model.flickr;

import lombok.Data;

@Data
public class FlickrPhotoSetResponse<T> {
    private T photoset;
    private String stat;
}
