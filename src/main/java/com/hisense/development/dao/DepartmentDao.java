package com.hisense.development.dao;

import com.hisense.development.entity.Department;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
@Repository
public interface DepartmentDao extends BaseDao<Department> {
    public boolean deleteDepartment(Long id);
    public List<Department> findChildDepartment(Long id);
}
