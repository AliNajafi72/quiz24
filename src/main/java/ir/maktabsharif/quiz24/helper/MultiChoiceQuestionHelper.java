package ir.maktabsharif.quiz24.helper;

import ir.maktabsharif.quiz24.entity.mongodb.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
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
