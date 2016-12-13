package com.hisense.development.service;

import com.hisense.development.entity.Department;
import javaslang.control.Either;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface DepartmentService extends BaseService<Department>{
    public boolean deleteDepartment(Long id);
    public boolean isLegal(Department bo);
}
