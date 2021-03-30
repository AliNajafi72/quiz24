package ir.maktabsharif.quiz24.services;

import ir.maktabsharif.quiz24.entities.mongodb.DescriptiveQuestion;
import ir.maktabsharif.quiz24.entities.mongodb.Question;
import ir.maktabsharif.quiz24.repositories.DescriptiveQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescriptiveQuestionService {

    private DescriptiveQuestionRepository descriptiveQuestionRepository;
    private MongoDBSequenceGeneratorService mongoDBSequenceGeneratorService;

    @Autowired
    public DescriptiveQuestionService(DescriptiveQuestionRepository descriptiveQuestionRepository, MongoDBSequenceGeneratorService mongoDBSequenceGeneratorService) {
        this.descriptiveQuestionRepository = descriptiveQuestionRepository;
        this.mongoDBSequenceGeneratorService = mongoDBSequenceGeneratorService;
    }

    public DescriptiveQuestion addNewDescriptiveQuestion(Long teacherId,
                                                         Long courseId,
                                                         Long quizId,
                                                         DescriptiveQuestion descriptiveQuestion) {
        descriptiveQuestion.setId(mongoDBSequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
        descriptiveQuestion.setTeacherId(teacherId);
        descriptiveQuestion.setCourseId(courseId);
        descriptiveQuestion.setQuizId(quizId);
        descriptiveQuestionRepository.save(descriptiveQuestion);
        return descriptiveQuestion;
    }
}
