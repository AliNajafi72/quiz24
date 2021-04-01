package ir.maktabsharif.quiz24.commands;

import ir.maktabsharif.quiz24.entities.mysql.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizCommand {
    private Long id;
    private String title;
    private String description;
    private Integer duration;
    private CourseCommand course;
}
