package com.hisense.hiutbd.platform.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class Role extends BaseBo implements Serializable {
    private Boolean available = Boolean.FALSE;
    private User createUser;
    private Date createDate;

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role() {
    }

    public Role(String name, Boolean available) {
        this.name = name;
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
