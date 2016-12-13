package com.hisense.hiutbd.platform.base.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class BaseBo implements Serializable {
    String name;
    Long id;

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
}
