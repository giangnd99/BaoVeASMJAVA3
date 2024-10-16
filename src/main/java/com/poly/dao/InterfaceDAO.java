package com.poly.dao;


import java.util.List;

public interface InterfaceDAO<T>{
    T create(T t) throws Exception;

    T update(T t) throws Exception;

    T get(Integer id) throws Exception;

    boolean remove(Integer id) throws Exception;

    List<T> listAll() throws Exception;
}
