package xyz.crearts.aphorism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import xyz.crearts.aphorism.services.EnvValuesService;

/**
 * @author ivan.kishchenko
 */
public class ContextHelper {
    @Autowired
    public EnvValuesService envValuesService;

    @ModelAttribute("title")
    public String getTitle() {
        return envValuesService.getValue("title", "Title");
    }
    @ModelAttribute("shortDescription")
    public String getShortDescription() {
        return envValuesService.getValue("short-description", "Hello World");
    }
}
