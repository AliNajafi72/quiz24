package ir.maktabsharif.quiz24;

import ir.maktabsharif.quiz24.aspect.AnalyticsAspect;
import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import ir.maktabsharif.quiz24.entity.mongodb.DescriptiveQuestion;
import ir.maktabsharif.quiz24.entity.mongodb.Question;
import ir.maktabsharif.quiz24.repository.AnalyticRepository;
import ir.maktabsharif.quiz24.repository.DescriptiveQuestionRepository;
import ir.maktabsharif.quiz24.service.MongoDBSequenceGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Quiz24Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Quiz24Application.class, args);
        // FIXME
        Analytic analytic = new Analytic();
        MongoDBSequenceGeneratorService mongoDBSequenceGeneratorService = applicationContext.getBean(MongoDBSequenceGeneratorService.class);
        analytic.setId(mongoDBSequenceGeneratorService.generateSequence(Analytic.SEQUENCE_NAME));
        analytic.setStudentCount(0L);
        analytic.setTeacherCount(0L);
        analytic.setLastSignupDate(new Date());
        AnalyticRepository analyticRepository = applicationContext.getBean(AnalyticRepository.class);
        analyticRepository.save(analytic);
        // FIXME
        DescriptiveQuestionRepository descriptiveQuestionRepository =
                applicationContext.getBean(DescriptiveQuestionRepository.class);
        DescriptiveQuestion descriptiveQuestion = new DescriptiveQuestion();
        descriptiveQuestion.setId(mongoDBSequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
        descriptiveQuestion.setQuizId(1L);
        descriptiveQuestion.setAnswer("Hello!");
        descriptiveQuestionRepository.save(descriptiveQuestion);
    }

}
