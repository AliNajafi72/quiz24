package ir.maktabsharif.quiz24.converters.studentconverter;

import ir.maktabsharif.quiz24.commands.StudentCommand;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseToCourseCommandConverter;
import ir.maktabsharif.quiz24.entities.mysql.Student;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentCommandConverter implements Converter<Student, StudentCommand> {

    private CourseToCourseCommandConverter courseToCourseCommandConverter;

    @Autowired
    public StudentToStudentCommandConverter(CourseToCourseCommandConverter courseToCourseCommandConverter) {
        this.courseToCourseCommandConverter = courseToCourseCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public StudentCommand convert(Student source) {
        if (source == null)
            return null;

        final StudentCommand studentCommand = new StudentCommand();
        studentCommand.setId(source.getId());
        studentCommand.setUsername(source.getUsername());
        studentCommand.setPassword(source.getPassword());
        studentCommand.setIdNumber(source.getIdNumber());
        studentCommand.setPhoneNumber(source.getPhoneNumber());
        studentCommand.setAddress(source.getAddress());
        studentCommand.setStatus(source.getStatus());

        if (!(source.getCourses() == null) && source.getCourses().size()>0)
            source.getCourses().forEach(course -> studentCommand.getCourses().add(courseToCourseCommandConverter.convert(course)));

        return studentCommand;
    }
}
