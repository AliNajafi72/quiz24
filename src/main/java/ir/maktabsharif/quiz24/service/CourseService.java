package ir.maktabsharif.quiz24.service;

import ir.maktabsharif.quiz24.entity.mysql.Course;
import ir.maktabsharif.quiz24.entity.mysql.Quiz;
import ir.maktabsharif.quiz24.repository.CourseRepository;
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
