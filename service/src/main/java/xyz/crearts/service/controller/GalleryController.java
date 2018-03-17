package xyz.crearts.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.crearts.service.model.FlickrPhotoSet;
import xyz.crearts.service.repository.FlickrPhotoSetRepository;
import xyz.crearts.service.services.FlickrService;

import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Controller
@RequestMapping(value = "/gallery")
public class GalleryController extends ContextHelper {
    private FlickrPhotoSetRepository flickrPhotoSetRepository;

    private FlickrService flickrService;

    public GalleryController(FlickrPhotoSetRepository flickrPhotoSetRepository) {
        this.flickrPhotoSetRepository = flickrPhotoSetRepository;
    }

    @ModelAttribute("photoSets")
    List<FlickrPhotoSet> getPhotoSets() {
        return flickrPhotoSetRepository.findAll();
    }

    @GetMapping({"/", ""})
    public String galleryAction(Model model) {
        return "gallery";
    }

    @GetMapping("/{id}")
    public String galleryAction(@PathVariable(value="id") long photoSetId,  Model model) {
        FlickrPhotoSet photoSet = flickrPhotoSetRepository.getOne(photoSetId);
        model.addAttribute("photoSet", photoSet);

        return "album";
    }
}
