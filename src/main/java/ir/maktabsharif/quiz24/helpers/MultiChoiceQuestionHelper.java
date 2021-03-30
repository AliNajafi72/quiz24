package ir.maktabsharif.quiz24.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiChoiceQuestionHelper {
    private Long teacherId;
    private Long courseId;
    private Long quizId;
    private String tag;
    private String title;
    public String choice;
    public int choiceCount = 0;
    private Integer correctChoice;
    private List<String> choices = new ArrayList<>();
}
