package com.hisense.hiutbd.platform.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class BaseBo implements Serializable {
    protected String name;

    protected String id;

    private User createUser;

    private Date createTime;

    private String changeUser;

    private Date changeTime;
    private Long lmt;

    public Long getLmt() {
        return lmt;
    }

    public void setLmt(Long lmt) {
        this.lmt = lmt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
