package com.hisense.hiutbd.platform.dao;

import com.hisense.hiutbd.platform.base.persistence.DepartmentMapper;
import com.hisense.hiutbd.platform.base.domain.Department;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DepartmentDaoTest extends TestCase {
    @Autowired
    DepartmentMapper departmentDao;
    Department department = new Department();

    @Test
    public void testCreate() {
        department.setName("management1");
        department.setDisplayId(new Long(1));
        departmentDao.create(department);
    }

    @Test
    public void testFind() {
        Department foundDepartment = new Department();
        foundDepartment = departmentDao.find(new Long(1));
    }


    @Test
    public void testDelete(){

    }


    @Test
    public void testFindAll(){
        List<Department> all = departmentDao.findAll();
    }
}
