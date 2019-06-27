package com.xundatianxia.lotmoney.common.bean;

/**
 * Created by zhoutao on 2019/6/21.
 */

public class BaseNotifyBean {
    public enum TYPE {
        TYPE_SHARE_RESULT,
    }

    private TYPE type;
    private String message;
    private Object obj;
    private int integer;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

}
