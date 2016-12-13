package com.hisense.hiutbd.platform.base.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class UserRole implements Serializable {

    private String userId;
    private String roleId;

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
