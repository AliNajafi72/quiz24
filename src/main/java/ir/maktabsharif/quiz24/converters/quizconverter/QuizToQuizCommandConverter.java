package ir.maktabsharif.quiz24.converters.quizconverter;

import ir.maktabsharif.quiz24.commands.QuizCommand;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseToCourseCommandConverter;
import ir.maktabsharif.quiz24.entities.mysql.Quiz;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class QuizToQuizCommandConverter implements Converter<Quiz, QuizCommand> {

    private CourseToCourseCommandConverter courseToCourseCommandConverter;

    @Autowired
    public QuizToQuizCommandConverter(CourseToCourseCommandConverter courseToCourseCommandConverter) {
        this.courseToCourseCommandConverter = courseToCourseCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public QuizCommand convert(Quiz source) {
        if (source == null)
            return null;

        final QuizCommand quizCommand = new QuizCommand();
        quizCommand.setId(source.getId());
        quizCommand.setTitle(source.getTitle());
        quizCommand.setDescription(source.getDescription());
        quizCommand.setDuration(source.getDuration());
        quizCommand.setCourse(courseToCourseCommandConverter.convert(source.getCourse()));

        return quizCommand;
    }
}
