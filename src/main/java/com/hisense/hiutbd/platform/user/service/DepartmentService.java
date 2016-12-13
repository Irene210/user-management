package com.hisense.hiutbd.platform.user.service;

import com.hisense.hiutbd.platform.base.domain.Department;

/**
 * Created by Administrator on 2016/12/12.
 */
public interface DepartmentService extends BaseService<Department> {
    public boolean deleteDepartment(Long id);
    public boolean isLegal(Department bo);
}
