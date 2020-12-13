package ir.maktabsharif.quiz24.aspect;

import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import ir.maktabsharif.quiz24.repository.AnalyticRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Aspect
public class AnalyticsAspect {

    AnalyticRepository analyticRepository;

    @Autowired
    public AnalyticsAspect(AnalyticRepository analyticRepository) {
        this.analyticRepository = analyticRepository;
    }

    @Before("execution(public String ir.maktabsharif.quiz24.controller.UserController.studentSignupHandler(*))")
    public void updateStudentAnalytics() {
        List<Analytic> analytics = analyticRepository.findAll();
        Analytic analytic = analytics.get(0);
        analyticRepository.deleteAll();
        analytic.setStudentCount(analytic.getStudentCount() + 1L);
        analytic.setLastSignupDate(new Date());
        analyticRepository.save(analytic);
    }

    @Before("execution(public String ir.maktabsharif.quiz24.controller.UserController.teacherSignupHandler(*))")
    public void updateTeacherAnalytics() {
        List<Analytic> analytics = analyticRepository.findAll();
        Analytic analytic = analytics.get(0);
        analyticRepository.deleteAll();
        analytic.setTeacherCount(analytic.getTeacherCount() + 1L);
        analytic.setLastSignupDate(new Date());
        analyticRepository.save(analytic);
    }
}
