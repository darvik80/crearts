package xyz.crearts.service.services;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.crearts.service.model.flickr.*;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class FlickrService {

    private static final String uri = "https://api.flickr.com/services/rest/";
    private static final String userId = "122537468@N08";
    private static final String apiKey = "6ab1c24194d653d741906c4d8113b5f6";
    private RestTemplate request = new RestTemplate();


    public PhotoSetInfo getPhotoSetInfo(String id) throws Exception {
        return this.call("flickr.photosets.getInfo", Collections.singletonMap("photoset_id", id), FlickrPhotoSetInfo.class).getPhotoset();
    }

    public PhotoSetPhotos getPhotos(String id) throws Exception {
        Map<String, Object> args = new HashMap<String, Object>() {{
            put("photoset_id", id);
            put("extras", "url_o");
        }};

        FlickrPhotoSetPhotos result =  this.call("flickr.photosets.getPhotos", args, FlickrPhotoSetPhotos.class);

        return result.getPhotoset();
    }


    private <T extends FlickrPhotoSetResponse> T call(String method, Map<String, Object> args, Class<T> type) throws Exception {

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().uri(new URI(uri))
                .queryParam("method", method)
                .queryParam("format", "json")
                .queryParam("nojsoncallback", 1)
                .queryParam("api_key", apiKey)
                .queryParam("user_id", userId);
        args.forEach(builder::queryParam);

        System.out.println(builder.build().toUri());
        return request.getForObject(builder.build().toUri(), type);
    }

    /*
    public static void main(String[] args) throws Exception {
        FlickrService service = new FlickrService();

        PhotoSet set = service.getPhotoSetInfo("72157666767031048");
        System.out.println(set);

        for(Photo photo : service.getPhotos("72157682704198903")) {
            if (photo.getWidth() > photo.getHeight()) {
                System.out.println(photo.getTitle() + " " + photo.getUrl() + "(" + photo.getWidth() + "x" + photo.getHeight() + ")");
            }
        }

    }
    */
}
