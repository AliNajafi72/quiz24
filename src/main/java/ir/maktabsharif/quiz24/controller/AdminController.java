package ir.maktabsharif.quiz24.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/panel")
    public String adminPanel() {
        return "admin-panel";
    }
}
