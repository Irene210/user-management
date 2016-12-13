package com.hisense.hiutbd.platform.user.vo;

import com.hisense.hiutbd.platform.base.domain.User;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class RoleVo {

    private Long id;

    private String name;

    private Boolean available = Boolean.FALSE;

    private User createUser;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

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
}
