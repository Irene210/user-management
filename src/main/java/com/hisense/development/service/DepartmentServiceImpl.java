package com.hisense.development.service;

import com.hisense.development.dao.DepartmentDao;
import com.hisense.development.entity.Department;
import javaslang.control.Either;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class DepartmentServiceImpl extends AbstractBaseService<Department>
        implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;


    @Override
    public boolean isLegal(Department bo) {
        if (bo.getName() != null
                && bo.getDepartmentNum() != null
                && bo.getDate() != null
                && bo.getDisplayId() != null)
            return true;
        return false;
    }

    public boolean deleteDepartment(Long id) {
        try {
            List<Department> childDepartment = departmentDao.findChildDepartment(id);
            if (childDepartment.size() > 0) {
                for (Department child : childDepartment)
                    deleteDepartment(child.getId());
                departmentDao.deleteDepartment(id);
            } else if (childDepartment.size() == 0) {
                return departmentDao.deleteDepartment(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Either<Exception, Boolean> create(Department bo) {
        if (isLegal(bo) && baseDao.find(bo.getName()) == null) {
            logger().info("Creating: " + bo);
            return doAction(() -> baseDao.create(bo));
        }
        return Either.left(new Exception());
    }

    @Override
    public Either<Exception, Boolean> update(Department bo) {
        if (isLegal(bo)) {
            logger().info("Updating: " + bo);
            return doAction(() -> find(bo.getId()).map(s -> baseDao.update(bo)).getOrElse(false));
        }
        return Either.left(new Exception());

    }
}