package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mysql.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {
}
