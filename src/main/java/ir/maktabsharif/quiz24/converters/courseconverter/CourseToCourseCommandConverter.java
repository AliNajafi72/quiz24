package ir.maktabsharif.quiz24.converters.courseconverter;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.converters.quizconverter.QuizToQuizCommandConverter;
import ir.maktabsharif.quiz24.converters.studentconverter.StudentToStudentCommandConverter;
import ir.maktabsharif.quiz24.converters.teacherconverter.TeacherToTeacherCommandConverter;
import ir.maktabsharif.quiz24.entities.mysql.Course;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseCommandConverter implements Converter<Course, CourseCommand> {

    private QuizToQuizCommandConverter quizToQuizCommandConverter;
    private TeacherToTeacherCommandConverter teacherToTeacherCommandConverter;
    private StudentToStudentCommandConverter studentToStudentCommandConverter;

    @Autowired
    public CourseToCourseCommandConverter(QuizToQuizCommandConverter quizToQuizCommandConverter,
                                          TeacherToTeacherCommandConverter teacherToTeacherCommandConverter,
                                          StudentToStudentCommandConverter studentToStudentCommandConverter) {
        this.quizToQuizCommandConverter = quizToQuizCommandConverter;
        this.teacherToTeacherCommandConverter = teacherToTeacherCommandConverter;
        this.studentToStudentCommandConverter = studentToStudentCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public CourseCommand convert(Course source) {
        if(source == null)
            return null;
        final CourseCommand courseCommand = new CourseCommand();
        courseCommand.setId(source.getId());
        courseCommand.setTitle(source.getTitle());
        courseCommand.setStartDate(source.getStartDate());
        courseCommand.setFinishDate(source.getFinishDate());
        courseCommand.setTeacher(teacherToTeacherCommandConverter.convert(source.getTeacher()));

        source.getStudents()
                .forEach(studentCommand -> courseCommand.getStudents().add(studentToStudentCommandConverter.convert(studentCommand)));

        source.getQuizzes()
                .forEach(quizCommand -> courseCommand.getQuizzes().add(quizToQuizCommandConverter.convert(quizCommand)));

        return courseCommand;
    }
}
