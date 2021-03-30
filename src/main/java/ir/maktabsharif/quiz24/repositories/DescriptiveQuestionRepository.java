package ir.maktabsharif.quiz24.repositories;

import ir.maktabsharif.quiz24.entities.mongodb.DescriptiveQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptiveQuestionRepository extends MongoRepository<DescriptiveQuestion, String> {
}
