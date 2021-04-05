package ir.maktabsharif.quiz24.services.teacherservice;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.commands.TeacherCommand;
import ir.maktabsharif.quiz24.entities.mysql.Course;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import ir.maktabsharif.quiz24.services.baseservice.BaseService;

import java.util.List;

public interface TeacherService extends BaseService<Teacher, TeacherCommand> {
    Teacher verifyTeacher(Long id);
    Course addCourse(Course course, Long id);
    CourseCommand addCourseCommand(CourseCommand courseCommand, Long id);
    List<Course> getAllCourses(Long id);
}
