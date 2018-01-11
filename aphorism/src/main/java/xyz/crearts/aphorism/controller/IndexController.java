package xyz.crearts.aphorism.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.crearts.aphorism.model.EnvelopmentValue;
import xyz.crearts.aphorism.repository.AphorismRepository;
import xyz.crearts.aphorism.repository.EnvelopmentValueRepository;
import xyz.crearts.aphorism.services.EnvValuesService;

/**
 * @author ivan.kishchenko
 */
@Controller
public class IndexController extends ContextHelper {
    private AphorismRepository aphorismRepository;

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
