package ir.maktabsharif.quiz24.commands;

import ir.maktabsharif.quiz24.entities.mysql.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCommand extends UserCommand {
    private List<CourseCommand> courses = new ArrayList<>();
}
