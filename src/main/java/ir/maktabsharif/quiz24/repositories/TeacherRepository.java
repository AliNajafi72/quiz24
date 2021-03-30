package ir.maktabsharif.quiz24.repositories;

import ir.maktabsharif.quiz24.entities.mysql.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {
}
