package com.haulmont.testtask.dao;

import java.util.List;

public interface Dao<T> {
    void setDBManager(DBManager manager);
    void add(T entity);
    void update(T entity);
    void delete(long id);
    List<T> getAll();
}
