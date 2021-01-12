package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mongodb.MultiChoiceQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultiChoiceQuestionRepository extends MongoRepository<MultiChoiceQuestion, String> {

}
