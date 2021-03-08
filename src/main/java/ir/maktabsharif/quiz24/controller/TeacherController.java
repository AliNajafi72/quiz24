package ir.maktabsharif.quiz24.controller;

import ir.maktabsharif.quiz24.entity.mongodb.DescriptiveQuestion;
import ir.maktabsharif.quiz24.entity.mongodb.MultiChoiceQuestion;
import ir.maktabsharif.quiz24.entity.mysql.Quiz;
import ir.maktabsharif.quiz24.exception.UserNotFoundException;
import ir.maktabsharif.quiz24.helper.MultiChoiceQuestionHelper;
import ir.maktabsharif.quiz24.service.CourseService;
import ir.maktabsharif.quiz24.service.DescriptiveQuestionService;
import ir.maktabsharif.quiz24.service.MultiChoiceQuestionService;
import ir.maktabsharif.quiz24.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    TeacherService teacherService;
    CourseService courseService;
    DescriptiveQuestionService descriptiveQuestionService;
    MultiChoiceQuestionService multiChoiceQuestionService;
    MultiChoiceQuestionHelper multiChoiceQuestionHelper;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService, DescriptiveQuestionService descriptiveQuestionService, MultiChoiceQuestionService multiChoiceQuestionService, MultiChoiceQuestionHelper multiChoiceQuestionHelper) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.descriptiveQuestionService = descriptiveQuestionService;
        this.multiChoiceQuestionService = multiChoiceQuestionService;
        this.multiChoiceQuestionHelper = multiChoiceQuestionHelper;
    }

    @GetMapping({"/", "index", ""})
    public String TeacherDashboard() {
        return "teacher";
    }

    @GetMapping("/{teacherId}/course")
    public String coursesPage(@PathVariable Long teacherId, Model model) throws Exception {
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

    @GetMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/descriptive")
    public String descriptiveQuestion(@PathVariable String courseId,
                                  @PathVariable String quizId,
                                  @PathVariable String teacherId,
                                  Model model) {
        model.addAttribute(new DescriptiveQuestion());
        return "descriptive-question";
    }

    @PostMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/descriptive")
    public ModelAndView descriptiveQuestionHandler(@PathVariable Long courseId,
                                                   @PathVariable Long quizId,
                                                   @PathVariable Long teacherId,
                                                   @ModelAttribute DescriptiveQuestion descriptiveQuestion) {
        descriptiveQuestionService.addNewDescriptiveQuestion(
                teacherId, courseId, quizId, descriptiveQuestion
        );
        return new ModelAndView("redirect:/teacher/" + teacherId + "/course/");
    }

    @GetMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/multichoice")
    public String multiChoiceQuestion(@PathVariable String courseId,
                                      @PathVariable String quizId,
                                      @PathVariable String teacherId,
                                      Model model) {
        MultiChoiceQuestion multiChoiceQuestion = new MultiChoiceQuestion();
        multiChoiceQuestion.setChoices(multiChoiceQuestionHelper.getChoices());
        model.addAttribute(multiChoiceQuestion);
        return "multi-choice-question";
    }

    @PostMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/multiChoice")
    public ModelAndView multiChoiceQuestionHandler(
            @PathVariable Long courseId,
            @PathVariable Long quizId,
            @PathVariable Long teacherId,
            @ModelAttribute MultiChoiceQuestion multiChoiceQuestion) {
        multiChoiceQuestion.setTeacherId(multiChoiceQuestionHelper.getTeacherId());
        multiChoiceQuestion.setCourseId(multiChoiceQuestionHelper.getCourseId());
        multiChoiceQuestion.setQuizId(multiChoiceQuestionHelper.getQuizId());
        multiChoiceQuestion.setTag(multiChoiceQuestionHelper.getTag());
        multiChoiceQuestion.setTitle(multiChoiceQuestionHelper.getTitle());
        multiChoiceQuestion.setChoices(multiChoiceQuestionHelper.getChoices());
        multiChoiceQuestionService.addNewMultiChoiceQuestion(teacherId, courseId, quizId, multiChoiceQuestion);
        return new ModelAndView("redirect:/teacher/" + teacherId + "/course/");
    }

    @PostMapping("/{teacherId}/course/{courseId}/quiz/{quizId}/multiChoice/choice")
    public String addChoice(
            @PathVariable Long courseId,
            @PathVariable Long quizId,
            @PathVariable Long teacherId,
            @ModelAttribute MultiChoiceQuestion multiChoiceQuestion,
            Model model) {
        multiChoiceQuestionHelper.getChoices().add(multiChoiceQuestion.getChoice());
        multiChoiceQuestionHelper.setTeacherId(teacherId);
        multiChoiceQuestionHelper.setCourseId(courseId);
        multiChoiceQuestionHelper.setQuizId(quizId);
        multiChoiceQuestionHelper.setTag(multiChoiceQuestion.getTag());
        multiChoiceQuestionHelper.setTitle(multiChoiceQuestion.getTitle());
        multiChoiceQuestion.setChoices(multiChoiceQuestionHelper.getChoices());
        model.addAttribute(multiChoiceQuestion);
        return "multi-choice-question";
    }
}
