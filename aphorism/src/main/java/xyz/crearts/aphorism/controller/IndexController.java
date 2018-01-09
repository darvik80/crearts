package xyz.crearts.aphorism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.crearts.aphorism.repository.AphorismRepository;

import java.util.Random;

/**
 * @author ivan.kishchenko
 */
@Controller
public class IndexController {
    AphorismRepository aphorismRepository;

    IndexController(AphorismRepository aphorismRepository) {
        this.aphorismRepository = aphorismRepository;
    }

    @GetMapping({"/", ""})
    public String indexAction(Model model) {
        long id =(long) (Math.random() * aphorismRepository.count()) + 1;
        model.addAttribute("aphorism", aphorismRepository.getOne(id));

        return "index";
    }
}
