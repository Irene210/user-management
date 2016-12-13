package com.hisense.hiutbd.platform.base.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class UserRole implements Serializable {

    private Long userId;
    private Long roleId;

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
