package ir.maktabsharif.quiz24.services.teacherservice;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.commands.TeacherCommand;
import ir.maktabsharif.quiz24.converters.teacherconverter.TeacherCommandToTeacherConverter;
import ir.maktabsharif.quiz24.converters.teacherconverter.TeacherToTeacherCommandConverter;
import ir.maktabsharif.quiz24.entities.mysql.Course;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.entities.mysql.UserStatus;
import ir.maktabsharif.quiz24.exceptions.UserNotFoundException;
import ir.maktabsharif.quiz24.repositories.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;
    private TeacherCommandToTeacherConverter teacherCommandToTeacherConverter;
    private TeacherToTeacherCommandConverter teacherToTeacherCommandConverter;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              TeacherCommandToTeacherConverter teacherCommandToTeacherConverter,
                              TeacherToTeacherCommandConverter teacherToTeacherCommandConverter) {
        this.teacherRepository = teacherRepository;
        this.teacherCommandToTeacherConverter = teacherCommandToTeacherConverter;
        this.teacherToTeacherCommandConverter = teacherToTeacherCommandConverter;
    }

    @Override
    public Teacher save(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    @Transactional
    @Override
    public TeacherCommand saveCommand(TeacherCommand command) {
        Teacher detachedTeacher = teacherCommandToTeacherConverter.convert(command);
        Teacher teacher = this.save(detachedTeacher);
        return teacherToTeacherCommandConverter.convert(teacher);
    }

    @Override
    public Teacher findById(Long id) {
        Supplier<Exception> exceptionSupplier = () -> new UserNotFoundException("There is no user with ID of: " + id.toString());
        Teacher teacher = null;
        try {
            teacher = teacherRepository.findById(id).orElseThrow(exceptionSupplier);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return teacher;
    }

    @Transactional
    @Override
    public TeacherCommand findCommandById(Long id) {
        TeacherCommand teacherCommand = teacherToTeacherCommandConverter.convert(this.findById(id));
        return teacherCommand;
    }

    @Override
    public Set<Teacher> findAll() {
        Set<Teacher> teachers = new HashSet<>();
        teacherRepository.findAll().iterator().forEachRemaining(teachers::add);
        return teachers;
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Course addCourse(Course course, Long id) {
        Teacher teacher = this.findById(id);
        teacher.addCourse(course);
    }

    @Override
    public CourseCommand addCourseCommand(CourseCommand courseCommand, Long id) {

        return null;
    }

    @Override
    public List<Course> getAllCourses(Long id) {
        return null;
    }

    public List<Teacher> getAll() {
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
