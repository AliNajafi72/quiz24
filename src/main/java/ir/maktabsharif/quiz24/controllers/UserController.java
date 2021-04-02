package ir.maktabsharif.quiz24.controllers;
import ir.maktabsharif.quiz24.entities.mysql.Student;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.entities.mysql.UserStatus;
import ir.maktabsharif.quiz24.services.studentservice.StudentService;
import ir.maktabsharif.quiz24.services.teacherservice.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class UserController {

    TeacherServiceImpl teacherServiceImpl;
    StudentService studentService;

    @Autowired
    public UserController(TeacherServiceImpl teacherServiceImpl, StudentService studentService) {
        this.teacherServiceImpl = teacherServiceImpl;
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
        teacherServiceImpl.addTeacher(teacher);
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
