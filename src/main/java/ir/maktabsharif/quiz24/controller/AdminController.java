package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import ir.maktabsharif.quiz24.entity.mysql.Student;
import ir.maktabsharif.quiz24.repository.AnalyticRepository;
import ir.maktabsharif.quiz24.repository.StudentRepository;
import ir.maktabsharif.quiz24.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    AnalyticRepository analyticRepository;
    StudentService studentService;

    @Autowired
    public AdminController(AnalyticRepository analyticRepository, StudentService studentService) {
        this.analyticRepository = analyticRepository;
        this.studentService = studentService;
    }

    @RequestMapping({"", "/", "/index"})
    public String adminPanelIndex(Model model) {
        Optional<Analytic> analyticOptional = Optional.ofNullable(analyticRepository.findAll().get(0));
        Analytic analytic = analyticOptional.isPresent()?analyticOptional.get():new Analytic(0L, 0L, new Date());
        model.addAttribute("analytic", analytic);
        return "admin";
    }

    @RequestMapping("/student")
    public String adminPanelStudent(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "admin-student";
    }
}
