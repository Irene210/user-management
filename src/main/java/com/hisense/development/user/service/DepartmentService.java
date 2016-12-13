package com.hisense.development.user.service;

import com.hisense.development.base.domain.Department;
import com.hisense.development.user.service.BaseService;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface DepartmentService extends BaseService<Department> {
    public boolean deleteDepartment(Long id);
    public boolean isLegal(Department bo);
}
