package com.haulmont.testtask.dao;

import java.util.List;

public interface Dao<T> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> getAll();
}
