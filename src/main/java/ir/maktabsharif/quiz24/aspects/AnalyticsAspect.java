package ir.maktabsharif.quiz24.aspects;

import ir.maktabsharif.quiz24.entities.mongodb.Analytic;
import ir.maktabsharif.quiz24.repositories.AnalyticRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Aspect
@Order(1)
public class AnalyticsAspect {

    AnalyticRepository analyticRepository;

    @Autowired
    public AnalyticsAspect(AnalyticRepository analyticRepository) {
        this.analyticRepository = analyticRepository;
    }

    @Before("execution(public * ir.maktabsharif.quiz24.controllers.UserController.studentSignupHandler(*))")
    public void updateStudentAnalytics() {
        List<Analytic> analytics = analyticRepository.findAll();
        Analytic analytic = analytics.get(0);
        analyticRepository.deleteAll();
        analytic.setStudentCount(analytic.getStudentCount() + 1L);
        analytic.setLastSignupDate(new Date());
        analyticRepository.save(analytic);
    }

    @Before("execution(public * ir.maktabsharif.quiz24.controllers.UserController.teacherSignupHandler(*))")
    public void updateTeacherAnalytics(JoinPoint joinPoint) {
        List<Analytic> analytics = analyticRepository.findAll();
        Analytic analytic = analytics.get(0);
        analyticRepository.deleteAll();
        analytic.setTeacherCount(analytic.getTeacherCount() + 1L);
        analytic.setLastSignupDate(new Date());
        analyticRepository.save(analytic);
    }
}
