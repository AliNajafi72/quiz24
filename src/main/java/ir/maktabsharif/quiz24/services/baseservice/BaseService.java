package ir.maktabsharif.quiz24.services.baseservice;

import java.util.Set;

public interface BaseService<E, C> {
    E save(E entity);
    C saveCommand(C command);
    E findById(Long id);
    C findCommandById(Long id);
    Set<E> findAll();
    void deleteById(Long id);
}
