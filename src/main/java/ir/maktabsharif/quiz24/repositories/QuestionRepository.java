package ir.maktabsharif.quiz24.repositories;

import ir.maktabsharif.quiz24.entities.mongodb.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository  extends MongoRepository<Question, String> {
}
