package com.hisense.development.service;

import com.hisense.development.dao.DepartmentDao;
import com.hisense.development.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class DepartmentServiceImpl extends AbstractBaseService<Department>
        implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    public boolean deleteDepartment(Long id){
            try {
                List<Department> childDepartment = departmentDao.findChildDepartment(id);
                if (childDepartment.size() > 0) {
                    for (Department child : childDepartment)
                        deleteDepartment(child.getId());
                    departmentDao.deleteDepartment(id);
                }else if (childDepartment.size() == 0){
                    return departmentDao.deleteDepartment(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
    }
}
