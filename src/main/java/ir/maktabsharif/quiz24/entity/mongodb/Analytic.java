package ir.maktabsharif.quiz24.entity.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analytic {
    @Id
    private Long studentCount;
    private Long teacherCount;
    private Date lastSignupDate;
}
