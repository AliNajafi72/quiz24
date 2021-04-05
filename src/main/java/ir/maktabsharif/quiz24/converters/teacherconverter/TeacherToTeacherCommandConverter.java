package ir.maktabsharif.quiz24.converters.teacherconverter;

import ir.maktabsharif.quiz24.commands.TeacherCommand;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseToCourseCommandConverter;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TeacherToTeacherCommandConverter implements Converter<Teacher, TeacherCommand> {

    private CourseToCourseCommandConverter courseToCourseCommandConverter;

    @Autowired
    public TeacherToTeacherCommandConverter(CourseToCourseCommandConverter courseToCourseCommandConverter) {
        this.courseToCourseCommandConverter = courseToCourseCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public TeacherCommand convert(Teacher source) {
        if (source == null)
            return null;
        final TeacherCommand teacherCommand = new TeacherCommand();
        teacherCommand.setId(source.getId());
        teacherCommand.setUsername(source.getUsername());
        teacherCommand.setPassword(source.getPassword());
        teacherCommand.setIdNumber(source.getIdNumber());
        teacherCommand.setPhoneNumber(source.getPhoneNumber());
        teacherCommand.setAddress(source.getAddress());
        teacherCommand.setStatus(source.getStatus());

        if (!(source.getCourses() == null) && source.getCourses().size()>0) {
            source.getCourses()
                    .forEach(course -> teacherCommand.getCourses().add(courseToCourseCommandConverter.convert(course)));
        }

        return teacherCommand;
    }
}
