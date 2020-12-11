package ir.maktabsharif.quiz24;

import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import ir.maktabsharif.quiz24.repository.AnalyticRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Date;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Quiz24Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Quiz24Application.class, args);
        AnalyticRepository analyticRepository = applicationContext.getBean(AnalyticRepository.class);
        analyticRepository.save(new Analytic(0L,0L, new Date()));
    }

}
