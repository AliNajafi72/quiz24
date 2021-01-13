package ir.maktabsharif.quiz24.service;

import ir.maktabsharif.quiz24.entity.mongodb.DescriptiveQuestion;
import ir.maktabsharif.quiz24.entity.mongodb.Question;
import ir.maktabsharif.quiz24.repository.DescriptiveQuestionRepository;
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
