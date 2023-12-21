package com.mowii.mowii.service;

import java.util.List;

public interface MowiiService<T> {
    public T save(T item);
    public T getById(String id);
    public List<T> getAll();
    public T update(String id, T updatedItem);
    public T delete(String id);
}
