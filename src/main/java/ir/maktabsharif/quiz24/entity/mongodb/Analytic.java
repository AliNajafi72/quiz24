package ir.maktabsharif.quiz24.entity.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Document(collection = "analytic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analytic {

    @Transient
    public static final String SEQUENCE_NAME = "analytics_sequence";

    @Id
    private Long id;
    private Long studentCount;
    private Long teacherCount;
    private Date lastSignupDate;
}
