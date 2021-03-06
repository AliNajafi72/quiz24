package ir.maktabsharif.quiz24.controller;
import ir.maktabsharif.quiz24.entity.mysql.Student;
import ir.maktabsharif.quiz24.entity.mysql.Teacher;
import ir.maktabsharif.quiz24.entity.mysql.UserStatus;
import ir.maktabsharif.quiz24.service.StudentService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/signup")
public class UserController {

    TeacherService teacherService;
    StudentService studentService;

    @Autowired
    public UserController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @RequestMapping({"", "/", "index"})
    public String signupIndex() {
        return "signup";
    }

    @RequestMapping("/teacher")
    public String teacherSignup(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-signup";
    }

    @PostMapping("/teacher-signup")
    public ModelAndView teacherSignupHandler(@ModelAttribute Teacher teacher) {
        teacher.setStatus(UserStatus.WAITING);
        teacherService.addTeacher(teacher);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/student")
    public String studentSignup(Model model) {
        model.addAttribute("isSignupSuccessful", false);
        model.addAttribute("student", new Student());
        return "student-signup";
    }

    @PostMapping("/student-signup")
    public String studentSignupHandler(@ModelAttribute Student student, Model model) {
        student.setStatus(UserStatus.WAITING);
        studentService.addStudent(student);
        model.addAttribute("isSignupSuccessful", true);
        return "student-signup";
    }
}
