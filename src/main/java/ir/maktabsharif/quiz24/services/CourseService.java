package ir.maktabsharif.quiz24.services;

import ir.maktabsharif.quiz24.entities.mysql.Course;
import ir.maktabsharif.quiz24.entities.mysql.Quiz;
import ir.maktabsharif.quiz24.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Quiz> getCourseQuiz(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return course.getQuizzes();
    }

    public Quiz addCourseQuiz(Long courseId, Quiz quiz) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        course.addQuiz(quiz);
        courseRepository.save(course);
        return quiz;
    }
}
