package ir.maktabsharif.quiz24.repository;

import ir.maktabsharif.quiz24.entity.mysql.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T> extends BaseRepository<T> {
    Optional<User> findByUsername(String username);
}
