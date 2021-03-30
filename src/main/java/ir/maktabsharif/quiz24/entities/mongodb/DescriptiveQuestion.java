package ir.maktabsharif.quiz24.entities.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "descriptive_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptiveQuestion  extends Question {

    @Transient
    public static final QuestionType questionType = QuestionType.DESCRIPTIVE;

    private String answer;
}
