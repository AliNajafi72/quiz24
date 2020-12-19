package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mysql.Quiz;
import ir.maktabsharif.quiz24.service.CourseService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    TeacherService teacherService;
    CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping({"/", "index", ""})
    public String TeacherDashboard() {
        return "teacher";
    }

    @GetMapping("/course/{teacherId}")
    public String coursesPage(@PathVariable Long teacherId, Model model) {
        model.addAttribute("courses", teacherService.getTeacherAllCourses(teacherId));
        model.addAttribute("teacherId", teacherId);
        return "teacher-course";
    }

    @GetMapping("/course/{teacherId}/{courseId}")
    public String courseExamsPage(@PathVariable Long teacherId, @PathVariable Long courseId, Model model) {
        List<Quiz> quizzes = courseService.getCourseExam(courseId);
        model.addAttribute("exams", quizzes);
        return "teacher-course-exam";
    }
}
