package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mongodb.Analytic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalyticRepository extends MongoRepository<Analytic, String> {
    Optional<Analytic> findById(String s);
}
