package ir.maktabsharif.quiz24.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseRepository<T> extends CrudRepository<T, UUID> {
}
