package com.hisense.development.dao;

import com.hisense.development.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public interface BaseDao <T> {
    public boolean create(T entity) throws Exception;

    public List<T> findAll() throws Exception ;

    public T find(String name) throws Exception ;

    public boolean deleteAll() throws Exception ;

    public boolean delete(List<String> names) throws Exception ;

    public boolean update(T entity) throws Exception ;
}
