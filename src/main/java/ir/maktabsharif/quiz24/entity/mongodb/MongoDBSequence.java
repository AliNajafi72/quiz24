package ir.maktabsharif.quiz24.entity.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MongoDBSequence {
    @Id
    private String id;
    private Long seq;
}
