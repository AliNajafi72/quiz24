package ir.maktabsharif.quiz24.converters.quizconverter;

import ir.maktabsharif.quiz24.commands.QuizCommand;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseCommandToCourseConverter;
import ir.maktabsharif.quiz24.entities.mysql.Quiz;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class QuizCommandToQuizConverter implements Converter<QuizCommand, Quiz> {

    private CourseCommandToCourseConverter courseCommandToCourseConverter;

    @Autowired
    public QuizCommandToQuizConverter(CourseCommandToCourseConverter courseCommandToCourseConverter) {
        this.courseCommandToCourseConverter = courseCommandToCourseConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Quiz convert(QuizCommand source) {
        if (source == null)
            return null;

        final Quiz quiz = new Quiz();
        quiz.setId(source.getId());
        quiz.setTitle(source.getTitle());
        quiz.setDescription(source.getDescription());
        quiz.setDuration(source.getDuration());
        quiz.setCourse(courseCommandToCourseConverter.convert(source.getCourse()));

        return quiz;
    }
}
