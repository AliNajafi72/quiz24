package ir.maktabsharif.quiz24.repositories;

import ir.maktabsharif.quiz24.entities.mysql.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T> extends BaseRepository<T> {
    Optional<User> findByUsername(String username);
}
