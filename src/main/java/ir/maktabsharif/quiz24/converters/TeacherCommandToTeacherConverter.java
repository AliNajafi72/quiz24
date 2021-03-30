package ir.maktabsharif.quiz24.converters;

import ir.maktabsharif.quiz24.commands.TeacherCommand;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TeacherCommandToTeacherConverter implements Converter<TeacherCommand, Teacher>{

    private CourseCommandToCourseConverter courseCommandToCourseConverter;

    public TeacherCommandToTeacherConverter(CourseCommandToCourseConverter courseCommandToCourseConverter) {
        this.courseCommandToCourseConverter = courseCommandToCourseConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Teacher convert(TeacherCommand source) {
        if (source == null)
            return null;
        final Teacher teacher = new Teacher();
        teacher.setId(source.getId());
        teacher.setUsername(source.getUsername());
        teacher.setPassword(source.getPassword());
        teacher.setIdNumber(source.getIdNumber());
        teacher.setPhoneNumber(source.getPhoneNumber());
        teacher.setAddress(source.getAddress());
        teacher.setStatus(source.getStatus());

        if (!(source.getCourses() == null) && source.getCourses().size()>0) {
            source.getCourses()
                    .forEach(course -> teacher.getCourses().add(courseCommandToCourseConverter.convert(course)));
        }

        return teacher;
    }
}
