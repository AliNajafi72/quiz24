package ir.maktabsharif.quiz24.services;

import ir.maktabsharif.quiz24.entities.mongodb.MultiChoiceQuestion;
import ir.maktabsharif.quiz24.repositories.MultiChoiceQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiChoiceQuestionService {

    private MultiChoiceQuestionRepository multiChoiceQuestionRepository;
    private MongoDBSequenceGeneratorService mongoDBSequenceGeneratorService;

    @Autowired
    public MultiChoiceQuestionService(MultiChoiceQuestionRepository multiChoiceQuestionRepository, MongoDBSequenceGeneratorService mongoDBSequenceGeneratorService) {
        this.multiChoiceQuestionRepository = multiChoiceQuestionRepository;
        this.mongoDBSequenceGeneratorService = mongoDBSequenceGeneratorService;
    }

    public MultiChoiceQuestion addNewMultiChoiceQuestion(
            Long teacherId,
            Long courseId,
            Long quizId,
            MultiChoiceQuestion multiChoiceQuestion
    ) {
        multiChoiceQuestion.setId(mongoDBSequenceGeneratorService.generateSequence(MultiChoiceQuestion.SEQUENCE_NAME));
        multiChoiceQuestion.setTeacherId(teacherId);
        multiChoiceQuestion.setCourseId(courseId);
        multiChoiceQuestion.setQuizId(quizId);
        multiChoiceQuestionRepository.save(multiChoiceQuestion);
        return multiChoiceQuestion;
    }
}
