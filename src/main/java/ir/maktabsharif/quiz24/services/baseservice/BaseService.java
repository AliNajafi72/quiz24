package ir.maktabsharif.quiz24.services.baseservice;

import org.springframework.stereotype.Service;

public interface BaseService<E, C> {
    E save(E entity);
    C saveCommand(C command);
    E findById(Long l);
    C findCommandById(Long l);
    E findAll();
    E deleteById(E entity);
}
