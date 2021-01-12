package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mongodb.DescriptiveQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptiveQuestionRepository extends MongoRepository<DescriptiveQuestion, String> {
}
