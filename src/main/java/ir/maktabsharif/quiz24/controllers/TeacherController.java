package ir.maktabsharif.quiz24.controllers;

import ir.maktabsharif.quiz24.entities.mongodb.DescriptiveQuestion;
import ir.maktabsharif.quiz24.entities.mongodb.MultiChoiceQuestion;
import ir.maktabsharif.quiz24.entities.mysql.Quiz;
import ir.maktabsharif.quiz24.exceptions.UserNotFoundException;
import ir.maktabsharif.quiz24.helpers.MultiChoiceQuestionHelper;
import ir.maktabsharif.quiz24.services.courseservice.CourseService;
import ir.maktabsharif.quiz24.services.DescriptiveQuestionService;
import ir.maktabsharif.quiz24.services.MultiChoiceQuestionService;
import ir.maktabsharif.quiz24.services.teacherservice.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    TeacherServiceImpl teacherServiceImpl;
    CourseService courseService;
    DescriptiveQuestionService descriptiveQuestionService;
    MultiChoiceQuestionService multiChoiceQuestionService;
    MultiChoiceQuestionHelper multiChoiceQuestionHelper;

    @Autowired
    public TeacherController(TeacherServiceImpl teacherServiceImpl, CourseService courseService, DescriptiveQuestionService descriptiveQuestionService, MultiChoiceQuestionService multiChoiceQuestionService, MultiChoiceQuestionHelper multiChoiceQuestionHelper) {
        this.teacherServiceImpl = teacherServiceImpl;
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
    public String coursesPage(@PathVariable String teacherId, Model model) throws Exception {
        model.addAttribute("courses", teacherServiceImpl.getTeacherAllCourses(Long.parseLong(teacherId)));
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
    public ModelAndView addNewQuizHandler(@PathVariable Long courseId, @PathVariable Long teacherId, @ModelAttribute Quiz quiz) {
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

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String teacherNotFoundHandler(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "error-pages/404";
    }
}
