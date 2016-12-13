package com.hisense.hiutbd.platform.base.persistence;

import com.hisense.hiutbd.platform.base.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
    public boolean deleteDepartment(Long id);
    public List<Department> findChildDepartment(Long id);
}
