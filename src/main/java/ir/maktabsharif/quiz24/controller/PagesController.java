package ir.maktabsharif.quiz24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping({"", "/", "/index"})
    public String indexPage() {
        return "index";
    }
}
