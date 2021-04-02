package ir.maktabsharif.quiz24.services.teacherservice;

import ir.maktabsharif.quiz24.entities.mysql.Course;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.entities.mysql.UserStatus;
import ir.maktabsharif.quiz24.exceptions.UserNotFoundException;
import ir.maktabsharif.quiz24.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class TeacherServiceImpl {

    TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    public Teacher verifyTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseGet(Teacher::new);
        teacher.setStatus(UserStatus.APPROVED);
        teacherRepository.save(teacher);
        return teacher;
    }

    public Course addCourseForTeacher(Course course, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        teacher.addCourse(course);
        teacherRepository.save(teacher);
        return course;
    }

    public List<Course> getTeacherAllCourses(Long id) throws Exception {
        Supplier<Exception> userNotFoundSupplier = ()-> new UserNotFoundException("There is no user with ID of: " + id.toString());
        Teacher teacher = teacherRepository.findById(id).orElseThrow(userNotFoundSupplier);
        return teacher.getCourses();
    }
}
