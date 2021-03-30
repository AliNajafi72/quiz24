package ir.maktabsharif.quiz24.repositories;

import ir.maktabsharif.quiz24.entities.mysql.Course;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends BaseRepository<Course> {
    Optional<Course> findById(Long aLong);
}
