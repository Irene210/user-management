package com.hisense.development.user.serviceimpl;

import com.hisense.development.base.persistence.DepartmentMapper;
import com.hisense.development.base.domain.Department;
import com.hisense.development.user.service.DepartmentService;
import com.hisense.development.user.serviceimpl.AbstractBaseService;
import javaslang.control.Either;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
public class DepartmentServiceImpl extends AbstractBaseService<Department>
        implements DepartmentService {
    @Autowired
    DepartmentMapper departmentDao;

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
        if (isLegal(bo) && baseMapper.find(bo.getName()) == null) {
            logger().info("Creating: " + bo);
            return doAction(() -> baseMapper.create(bo));
        }
        return Either.left(new Exception());
    }

    @Override
    public Either<Exception, Boolean> update(Department bo) {
        if (isLegal(bo)) {
            return super.update(bo);
        }
        return Either.left(new Exception());

    }
}