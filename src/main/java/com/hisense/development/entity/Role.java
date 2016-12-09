package com.hisense.development.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class Role implements Serializable {
    private Long id;
    private String name;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create='" + createUser.getUsername() + '\'' +
                ", createDate='" + createDate + '\'' +
                ", available=" + available +
                '}';
    }
}
