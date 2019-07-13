package com.xundatianxia.lotmoney.common.bean;

/**
 * Created by Chu on 2018/3/10.
 */

public class NotifyBean {

    public enum TYPE {
        TYPE_LOGOUT, TYPE_LOGIN,
    }

    private TYPE type;
    private int code;
    private String result;
    private Object data;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result == null ? "" : result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
