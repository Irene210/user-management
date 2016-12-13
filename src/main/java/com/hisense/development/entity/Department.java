package com.hisense.development.entity;

import com.hisense.development.dao.BaseDao;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class Department extends BaseBo implements Serializable {

    String departmentNum;
    String manager;
    int telephone;
    String date;
    int displayId;
    Long fatherDepartment;

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public Long getFatherDepartment() {
        return fatherDepartment;
    }

    public void setFatherDepartment(Long fatherDepartment) {
        this.fatherDepartment = fatherDepartment;
    }
}
