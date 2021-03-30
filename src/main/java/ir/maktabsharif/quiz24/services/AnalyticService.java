package ir.maktabsharif.quiz24.services;

import ir.maktabsharif.quiz24.entities.mongodb.Analytic;
import ir.maktabsharif.quiz24.repositories.AnalyticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AnalyticService {

    AnalyticRepository analyticRepository;

    @Autowired
    public AnalyticService(AnalyticRepository analyticRepository) {
        this.analyticRepository = analyticRepository;
    }

    public Analytic getAnalytic() {
        Optional<Analytic> analyticOptional = Optional.ofNullable(analyticRepository.findAll().get(0));
        return analyticOptional.orElseGet(() -> new Analytic(0L, 0L, 0L, new Date()));
    }
}
