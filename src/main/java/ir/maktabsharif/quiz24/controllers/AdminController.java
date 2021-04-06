package ir.maktabsharif.quiz24.controllers;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.entities.mysql.Course;
import ir.maktabsharif.quiz24.entities.mysql.Student;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.services.AnalyticService;
import ir.maktabsharif.quiz24.services.studentservice.StudentService;
import ir.maktabsharif.quiz24.services.teacherservice.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AnalyticService analyticService;
    private final StudentService studentService;
    private final TeacherServiceImpl teacherServiceImpl;

    @Autowired
    public AdminController(
            AnalyticService analyticService,
            StudentService studentService,
            TeacherServiceImpl teacherServiceImpl
    ) {
        this.analyticService = analyticService;
        this.studentService = studentService;
        this.teacherServiceImpl = teacherServiceImpl;
    }

    @RequestMapping({"", "/", "/index"})
    public String adminPanelIndex(Model model) {
        model.addAttribute("analytic", analyticService.getAnalytic());
        return "admin";
    }

    @RequestMapping("/student")
    public String adminPanelStudent(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "admin-student";
    }


    @GetMapping("student/verify/{id}")
    public ModelAndView studentVerification(@PathVariable Long id) {
        studentService.verifyStudent(id);
        return new ModelAndView("redirect:/admin/student");
    }

    @RequestMapping("/teacher")
    public String adminPanelTeacher(Model model) {
        Set<Teacher> teachers = teacherServiceImpl.findAll();
        model.addAttribute("teachers", teachers);
        return "admin-teacher";
    }

    @GetMapping("teacher/verify/{id}")
    public ModelAndView teacherVerification(@PathVariable Long id) {
        teacherServiceImpl.verifyTeacher(id);
        return new ModelAndView("redirect:/admin/teacher");
    }

    @GetMapping("teacher/add-course/{id}")
    public String addCourse(@PathVariable String id, Model model) {
        model.addAttribute("course", new CourseCommand());
        model.addAttribute("teacherId", id);
        return "admin-teacher-new-course";
    }

    @PostMapping("teacher/add-course/{id}")
    @ResponseBody
    public ModelAndView addCourse(
            @PathVariable String id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "startDate") String startDateString,
            @RequestParam(value = "finishDate") String finishDateString
    ) throws ParseException {
        CourseCommand courseCommand = new CourseCommand();
        Date startDate = new SimpleDateFormat("yy-MM-dd").parse(startDateString);
        Date finishDate = new SimpleDateFormat("yy-MM-dd").parse(finishDateString);
        courseCommand.setTitle(title);
        courseCommand.setStartDate(startDate);
        courseCommand.setFinishDate(finishDate);
        teacherServiceImpl.addCourseCommand(courseCommand, Long.parseLong(id));
        return new ModelAndView("redirect:/admin/teacher");
    }
}
