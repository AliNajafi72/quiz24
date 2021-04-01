package ir.maktabsharif.quiz24.converters;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.entities.mysql.Course;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandToCourseConverter implements Converter<CourseCommand, Course> {

    @Autowired
    private StudentCommandToStudentConverter studentCommandToStudentConverter;
    @Autowired
    private TeacherCommandToTeacherConverter teacherCommandToTeacherConverter;
    @Autowired
    private QuizCommandToQuizConverter quizCommandToQuizConverter;

    public CourseCommandToCourseConverter(StudentCommandToStudentConverter studentCommandToStudentConverter,
                                          TeacherCommandToTeacherConverter teacherCommandToTeacherConverter,
                                          QuizCommandToQuizConverter quizCommandToQuizConverter) {
        this.studentCommandToStudentConverter = studentCommandToStudentConverter;
        this.teacherCommandToTeacherConverter = teacherCommandToTeacherConverter;
        this.quizCommandToQuizConverter = quizCommandToQuizConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Course convert(CourseCommand source) {
        if(source == null)
            return null;
        final Course course = new Course();
        course.setId(source.getId());
        course.setTitle(source.getTitle());
        course.setStartDate(source.getStartDate());
        course.setFinishDate(source.getFinishDate());
        course.setTeacher(teacherCommandToTeacherConverter.convert(source.getTeacher()));

        source.getStudents()
                .forEach(studentCommand -> course.getStudents().add(studentCommandToStudentConverter.convert(studentCommand)));

        source.getQuizzes()
                .forEach(quizCommand -> course.getQuizzes().add(quizCommandToQuizConverter.convert(quizCommand)));

        return course;
    }
}
