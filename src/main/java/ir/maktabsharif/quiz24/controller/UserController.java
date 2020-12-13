package ir.maktabsharif.quiz24.controller;
import ir.maktabsharif.quiz24.entity.mysql.Student;
import ir.maktabsharif.quiz24.entity.mysql.Teacher;
import ir.maktabsharif.quiz24.entity.mysql.UserStatus;
import ir.maktabsharif.quiz24.service.StudentService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/student")
    public String studentSignup(Model model) {
        model.addAttribute("student", new Student());
        return "student-signup";
    }

    @PostMapping("/teacher-signup")
    public String teacherSignupHandler(@ModelAttribute Teacher teacher) {
        teacher.setStatus(UserStatus.WAITING);
        teacherService.addTeacher(teacher);
        return "index";
    }

    @PostMapping("/student-signup")
    public String studentSignupHandler(@ModelAttribute Student student) {
        student.setStatus(UserStatus.WAITING);
        studentService.addStudent(student);
        return "index";
    }
}
