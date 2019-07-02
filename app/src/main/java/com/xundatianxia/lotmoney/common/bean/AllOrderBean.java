package com.xundatianxia.lotmoney.common.bean;

/**
 * Created by zt on 2019/7/2.
 */
public class AllOrderBean {
    private String orderUrl;//图片地址
    private String orderTitle;//名称
    private String orderSaleOut;//已售
    private String orderRepertory;//库存
    private String orderNumber;//数量

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderSaleOut() {
        return orderSaleOut;
    }

    public void setOrderSaleOut(String orderSaleOut) {
        this.orderSaleOut = orderSaleOut;
    }

    public String getOrderRepertory() {
        return orderRepertory;
    }

    public void setOrderRepertory(String orderRepertory) {
        this.orderRepertory = orderRepertory;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
