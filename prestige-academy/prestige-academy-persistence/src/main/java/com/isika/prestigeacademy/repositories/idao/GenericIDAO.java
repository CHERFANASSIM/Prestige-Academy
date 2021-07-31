package com.isika.prestigeacademy.repositories.idao;

import java.util.List;

public interface GenericIDAO<T> {
    T add(T t);
    boolean delete(T t);
    T update(T t);
    T getById(Long i);
    List<T> findeAll();
}
