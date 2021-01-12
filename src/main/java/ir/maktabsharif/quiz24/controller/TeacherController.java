package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mysql.Quiz;
import ir.maktabsharif.quiz24.service.CourseService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/{teacherId}/course")
    public String coursesPage(@PathVariable Long teacherId, Model model) {
        model.addAttribute("courses", teacherService.getTeacherAllCourses(teacherId));
        model.addAttribute("teacherId", teacherId);
        return "teacher-course";
    }

    @GetMapping("/{teacherId}/course/{courseId}")
    public String courseExamsPage(@PathVariable Long teacherId, @PathVariable Long courseId, Model model) {
        List<Quiz> quizzes = courseService.getCourseQuiz(courseId);
        model.addAttribute("exams", quizzes);
        return "teacher-course-exam";
    }

    @GetMapping("/{teacherId}/course/{courseId}/quiz")
    public String addNewQuiz(@PathVariable Long courseId, @PathVariable Long teacherId, Model model) {
        model.addAttribute("quiz", new Quiz());
        return "teacher-course-new-exam";
    }

    @PostMapping("/{teacherId}/course/{courseId}/quiz")
    public ModelAndView addNewQuizHandler(@PathVariable Long courseId,@PathVariable Long teacherId, @ModelAttribute Quiz quiz) {
        courseService.addCourseQuiz(courseId, quiz);
        return new ModelAndView("redirect:/teacher/" + teacherId + "/course/");
    }

    @GetMapping("/{teacherId}/course/{courseId}/quiz/{quizId}")
    public String quizModification(
            @PathVariable Long teacherId,
            @PathVariable Long courseId,
            @PathVariable Long quizId
    ) {
        return "quiz-modify";
    }

    @GetMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/multiChoice")
    public String multiChoiceQuiz(@PathVariable String courseId,
                                  @PathVariable String quizId,
                                  @PathVariable String teacherId,
                                  Model model) {
        return "multi-choice-question";
    }

    @GetMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/descriptive")
    public String descriptiveQuiz(@PathVariable String courseId,
                                  @PathVariable String quizId,
                                  @PathVariable String teacherId,
                                  Model model) {
        return "descriptive-question";

    }
}
