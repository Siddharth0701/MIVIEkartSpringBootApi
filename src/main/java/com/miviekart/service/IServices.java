package com.miviekart.service;

import java.util.List;

public interface IServices<T>  {
    List<T> findAll();
	T findById(final int id);
	T create(T t);
	boolean delete(final int id);
    T update(T  t,int id);
    
}
