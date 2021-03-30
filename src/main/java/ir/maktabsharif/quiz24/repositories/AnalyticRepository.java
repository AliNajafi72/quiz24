package ir.maktabsharif.quiz24.repositories;

import ir.maktabsharif.quiz24.entities.mongodb.Analytic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalyticRepository extends MongoRepository<Analytic, String> {
    Optional<Analytic> findById(String s);
}
