package ir.maktabsharif.quiz24.commands;

import ir.maktabsharif.quiz24.entities.mysql.Course;
import ir.maktabsharif.quiz24.entities.mysql.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCommand extends User {
    private List<CourseCommand> courses = new ArrayList<>();
}
