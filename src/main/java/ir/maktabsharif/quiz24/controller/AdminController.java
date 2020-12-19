package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mysql.Course;
import ir.maktabsharif.quiz24.entity.mysql.Student;
import ir.maktabsharif.quiz24.entity.mysql.Teacher;
import ir.maktabsharif.quiz24.service.AnalyticService;
import ir.maktabsharif.quiz24.service.StudentService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    AnalyticService analyticService;
    StudentService studentService;
    TeacherService teacherService;

    @Autowired
    public AdminController(
            AnalyticService analyticService,
            StudentService studentService,
            TeacherService teacherService
    ) {
        this.analyticService = analyticService;
        this.studentService = studentService;
        this.teacherService = teacherService;
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
        List<Teacher> teachers = teacherService.getTeachers();
        model.addAttribute("teachers", teachers);
        return "admin-teacher";
    }

    @GetMapping("teacher/verify/{id}")
    public ModelAndView teacherVerification(@PathVariable Long id) {
        teacherService.verifyTeacher(id);
        return new ModelAndView("redirect:/admin/teacher");
    }

    @GetMapping("teacher/add-course/{id}")
    public String addCourse(@PathVariable String id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("teacherId", id);
        return "admin-teacher-course";
    }

    @PostMapping("teacher/add-course/{id}")
    @ResponseBody
    public ModelAndView addCourse(
            @PathVariable String id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "startDate") String startDateString,
            @RequestParam(value = "finishDate") String finishDateString
    ) throws ParseException {
        Course course = new Course();
        Date startDate = new SimpleDateFormat("yy-MM-dd").parse(startDateString);
        Date finishDate = new SimpleDateFormat("yy-MM-dd").parse(finishDateString);
        course.setTitle(title);
        course.setStartDate(startDate);
        course.setFinishDate(finishDate);
        teacherService.addCourseForTeacher(course, Long.parseLong(id));
        return new ModelAndView("redirect:/admin/teacher");
    }
}
