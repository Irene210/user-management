package com.hisense.development.dao;

import com.hisense.development.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public interface BaseDao <T> {
    public boolean create(T entity) ;

    public List<T> findAll()  ;

    public T find(Long id) ;

    public T find(String name) ;

    public boolean deleteAll() ;

    public boolean delete(List<Long> ids);

    public boolean update(T entity) ;
}
