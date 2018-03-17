package xyz.crearts.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.crearts.service.model.flickr.PhotoSetPhotos;
import xyz.crearts.service.repository.AphorismRepository;
import xyz.crearts.service.services.FlickrService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Controller
public class IndexController extends ContextHelper {
    private AphorismRepository aphorismRepository;
    private FlickrService flickrService;

    IndexController(AphorismRepository aphorismRepository, FlickrService flickrService) {
        this.aphorismRepository = aphorismRepository;
        this.flickrService = flickrService;
    }

    @GetMapping({"/", ""})
    public String indexAction(Model model) {
        long id =(long) (Math.random() * aphorismRepository.count()) + 1;
        model.addAttribute("aphorism", aphorismRepository.getOne(id));

        try {
            PhotoSetPhotos photos = flickrService.getPhotos("72157666767031048");
            if (photos != null && photos.getPhoto() != null) {
                model.addAttribute("carousel", photos.getPhoto());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "index";
    }
}
