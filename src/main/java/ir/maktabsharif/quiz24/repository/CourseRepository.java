package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mysql.Course;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends BaseRepository<Course> {
    Optional<Course> findById(Long aLong);
}
