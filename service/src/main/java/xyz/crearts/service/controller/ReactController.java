package xyz.crearts.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ivan.kishchenko
 */
@Controller
@RequestMapping(value = "/react")
public class ReactController {
    @GetMapping({"/"})
    public String reactJsAction(Model model) {
        return "react";
    }
}
