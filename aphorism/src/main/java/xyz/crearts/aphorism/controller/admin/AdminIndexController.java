package xyz.crearts.aphorism.controller.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.crearts.aphorism.model.Aphorism;
import xyz.crearts.aphorism.model.Menu;
import xyz.crearts.aphorism.model.TinyMCEForm;
import xyz.crearts.aphorism.repository.AphorismRepository;
import xyz.crearts.aphorism.repository.MenuRepository;

import java.util.List;

/**
 * @author ivan.kishchenko
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminIndexController {
    private MenuRepository menuRepository;
    private AphorismRepository aphorismRepository;

    public AdminIndexController(MenuRepository menuRepository, AphorismRepository aphorismRepository) {
        this.menuRepository = menuRepository;
        this.aphorismRepository = aphorismRepository;
    }

    @ModelAttribute("menus")
    public List<Menu> getMenu() {
        return menuRepository.findAll();
    }

    @GetMapping({"/"})
    public String indexAction(Model model) {
        return "admin/index";
    }

    @GetMapping("/aphorism")
    public String aphorismAction(Model model) {
        model.addAttribute("aphorisms", this.aphorismRepository.findAll(PageRequest.of(0, 10)));
        model.addAttribute("aphorism", new Aphorism());
        model.addAttribute("fragment", "admin/aphorism :: aphorism");

        return "admin/index";
    }

    @PostMapping("/aphorism/submit")
    public String submitAction(@ModelAttribute Aphorism aphorism) {
        return "redirect:admin/aphorism";
    }
}
