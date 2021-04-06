package ir.maktabsharif.quiz24.services.teacherservice;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.commands.TeacherCommand;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseCommandToCourseConverter;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseToCourseCommandConverter;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherCommandToTeacherConverter teacherCommandToTeacherConverter;
    private final TeacherToTeacherCommandConverter teacherToTeacherCommandConverter;
    private final CourseCommandToCourseConverter courseCommandToCourseConverter;
    private final CourseToCourseCommandConverter courseToCourseCommandConverter;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              TeacherCommandToTeacherConverter teacherCommandToTeacherConverter,
                              TeacherToTeacherCommandConverter teacherToTeacherCommandConverter,
                              CourseCommandToCourseConverter courseCommandToCourseConverter,
                              CourseToCourseCommandConverter courseToCourseCommandConverter) {
        this.teacherRepository = teacherRepository;
        this.teacherCommandToTeacherConverter = teacherCommandToTeacherConverter;
        this.teacherToTeacherCommandConverter = teacherToTeacherCommandConverter;
        this.courseCommandToCourseConverter = courseCommandToCourseConverter;
        this.courseToCourseCommandConverter = courseToCourseCommandConverter;
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
        return teacherToTeacherCommandConverter.convert(this.findById(id));
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
        return course;
    }

    @Transactional
    @Override
    public CourseCommand addCourseCommand(CourseCommand courseCommand, Long id) {
        Teacher teacher = this.findById(id);
        Course detachedCourse = courseCommandToCourseConverter.convert(courseCommand);
        Course course = teacher.addCourse(detachedCourse);
        this.save(teacher);
        return courseToCourseCommandConverter.convert(course);
    }

    @Override
    public List<Course> getAllCourses(Long id) {
        Teacher teacher = this.findById(id);
        return teacher.getCourses();
    }


    @Override
    public Teacher verifyTeacher(Long id) {
        Teacher teacher = this.findById(id);
        teacher.setStatus(UserStatus.APPROVED);
        this.save(teacher);
        return teacher;
    }
}
