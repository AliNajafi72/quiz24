package ir.maktabsharif.quiz24.converters;

import ir.maktabsharif.quiz24.commands.CourseCommand;
import ir.maktabsharif.quiz24.entities.mysql.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandToCourseConverter implements Converter<CourseCommand, Course> {
    @Override
    public Course convert(CourseCommand source) {
        return null;
    }
}
