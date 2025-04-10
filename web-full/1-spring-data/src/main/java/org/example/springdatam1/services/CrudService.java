package org.example.springdatam1.services;

import java.util.List;

public interface CrudService<T,ID> {

    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    T update(ID id,T entity);
    void delete(ID id);



}
