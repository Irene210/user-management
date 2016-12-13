package com.hisense.hiutbd.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public interface Loggable {
    default Logger logger() {
        return LoggerFactory.getLogger(this.getClass().getName());
    }
}
