package ir.maktabsharif.quiz24.entity.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Document(collection = "multi_choice_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiChoiceQuestion {
    @Transient
    public static final QuestionType questionType = QuestionType.MULTI_CHOICE;

    private Integer correctChoice;
    private List<String> choices;
}
