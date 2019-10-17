package com.tatitati.meterreading.repository;

import java.util.List;

public interface Repository<T>{
    public T save(T t);

    public T update(T t);

    public T create(T t);

    public T findById(Integer id);

    public void delete(T t);

    public List<T> findAll();

    public int countAll();
}