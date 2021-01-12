package ir.maktabsharif.quiz24.entity.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Document(collection = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Question implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "question_sequence";

    @Id
    private Long id;
    private Long teacherId;
    private Long courseId;
    private Long quizId;
    private String tag;
    private String title;
}
