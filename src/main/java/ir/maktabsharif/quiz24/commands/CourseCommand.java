package ir.maktabsharif.quiz24.commands;

import ir.maktabsharif.quiz24.entities.mysql.Quiz;
import ir.maktabsharif.quiz24.entities.mysql.Student;
import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCommand{

    private Long id;
    private String title;
    private Date startDate;
    private Date finishDate;
    private Teacher teacher;
    private List<Student> students;
    private List<Quiz> quizzes;
}
