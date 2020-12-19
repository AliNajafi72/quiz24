package ir.maktabsharif.quiz24.aspect;

import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import ir.maktabsharif.quiz24.entity.mysql.User;
import ir.maktabsharif.quiz24.repository.AnalyticRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Iterator;
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

    @Before("execution(public * ir.maktabsharif.quiz24.controller.UserController.studentSignupHandler(*))")
    public void updateStudentAnalytics() {
        List<Analytic> analytics = analyticRepository.findAll();
        Analytic analytic = analytics.get(0);
        analyticRepository.deleteAll();
        analytic.setStudentCount(analytic.getStudentCount() + 1L);
        analytic.setLastSignupDate(new Date());
        analyticRepository.save(analytic);
    }

    @Before("execution(public * ir.maktabsharif.quiz24.controller.UserController.teacherSignupHandler(*))")
    public void updateTeacherAnalytics(JoinPoint joinPoint) {
        List<Analytic> analytics = analyticRepository.findAll();
        Analytic analytic = analytics.get(0);
        analyticRepository.deleteAll();
        analytic.setTeacherCount(analytic.getTeacherCount() + 1L);
        analytic.setLastSignupDate(new Date());
        analyticRepository.save(analytic);
    }
}
