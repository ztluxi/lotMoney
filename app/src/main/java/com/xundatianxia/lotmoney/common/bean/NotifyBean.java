package com.xundatianxia.lotmoney.common.bean;

/**
 * Created by Chu on 2018/3/10.
 */

public class NotifyBean {

    public enum TYPE {
        TYPE_LOGOUT, TYPE_LOGIN, TYPE_TIME_COUNTDOWN,TYPE_BECOME_SERVANT_SUCCESS
        ,TYPE_GET_GOLD_MINE_EXCHANGE,TYPE_MINE_MOUNTAIN_CHANGE,
        TYPE_BINDZHIFUB_SUCCESS,TYPE_BINDPHONE_SUCCESS,TYPE_QIEHUAN_SUCCESS,TYPE_QIEHUAN_MANOR_SUCCESS,TYPE_GET_MANOR_INFO_SUCCESS
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
