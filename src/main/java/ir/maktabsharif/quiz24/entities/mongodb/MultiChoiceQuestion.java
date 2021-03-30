package ir.maktabsharif.quiz24.entities.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Transient;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "multi_choice_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiChoiceQuestion extends Question {
    @Transient
    public static final QuestionType questionType = QuestionType.MULTI_CHOICE;

    @Transient
    public String choice;

    @Transient
    public int choiceCount;

    private Integer correctChoice;
    private List<String> choices;
}
