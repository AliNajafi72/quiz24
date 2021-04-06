package ir.maktabsharif.quiz24.converters.studentconverter;

import ir.maktabsharif.quiz24.commands.StudentCommand;
import ir.maktabsharif.quiz24.converters.courseconverter.CourseCommandToCourseConverter;
import ir.maktabsharif.quiz24.entities.mysql.Student;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class StudentCommandToStudentConverter implements Converter<StudentCommand, Student> {

    private CourseCommandToCourseConverter courseCommandToCourseConverter;

    @Autowired
    public StudentCommandToStudentConverter(CourseCommandToCourseConverter courseCommandToCourseConverter) {
        this.courseCommandToCourseConverter = courseCommandToCourseConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Student convert(StudentCommand source) {
        if (source == null)
            return null;

        final Student student = new Student();
        student.setId(source.getId());
        student.setUsername(source.getUsername());
        student.setPassword(source.getPassword());
        student.setIdNumber(source.getIdNumber());
        student.setPhoneNumber(source.getPhoneNumber());
        student.setAddress(source.getAddress());
        student.setStatus(source.getStatus());

        if (!(source.getCourses() == null) && source.getCourses().size()>0)
            source.getCourses().forEach(course -> student.getCourses().add(courseCommandToCourseConverter.convert(course)));

        return student;
    }
}
