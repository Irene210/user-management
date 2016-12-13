package com.hisense.hiutbd.platform.base.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class Role extends BaseBo implements Serializable {

    private Boolean available = Boolean.FALSE;


    public Role() {
    }

    public Role(String id,String name, Boolean available) {
        this.id=id;
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
