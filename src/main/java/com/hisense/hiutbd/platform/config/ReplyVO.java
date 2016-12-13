package com.hisense.hiutbd.platform.config;

/**
 * 响应值对象
 *
 * @author xo
 */
public class ReplyVO<T> {

    private int errorCode;

    private String errorMsg;

    private T data;

    public ReplyVO() {
        this.errorCode = 0;
        this.errorMsg = null;
    }

    public ReplyVO(T data) {
        this.errorCode = 0;
        this.errorMsg = null;
        this.data = data;
    }
    
    public ReplyVO(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public ReplyVO(int errorCode, String errorMsg, T data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
