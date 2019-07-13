package com.xundatianxia.lotmoney.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * Created by zt on 2019/7/2.
 */
public class AllOrderBean extends BaseObservable implements Parcelable {

    private String orderUrl;//图片地址
    private String orderTitle;//类型名称
    private String orderSaleOut;//已售
    private String orderRepertory;//库存
    private String orderNumber;//数量
    private String orderProductTitle;//产品名称
    private String orderNewPrice;//最新价格
    private String orderOldPrice;//老价格
    private int type;//交易状态 1是代付款，2是代发货，3是待收货
    public AllOrderBean(Parcel in) {
        orderUrl = in.readString();
        orderTitle = in.readString();
        orderSaleOut = in.readString();
        orderRepertory = in.readString();
        orderNumber = in.readString();
        orderProductTitle = in.readString();
        orderNewPrice = in.readString();
        orderOldPrice = in.readString();
        type = in.readInt();
    }

    public static final Creator<AllOrderBean> CREATOR = new Creator<AllOrderBean>() {
        @Override
        public AllOrderBean createFromParcel(Parcel in) {
            return new AllOrderBean(in);
        }

        @Override
        public AllOrderBean[] newArray(int size) {
            return new AllOrderBean[size];
        }
    };

    public AllOrderBean() {

    }
    @Bindable
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        notifyChange();
    }

    public String getOrderProductTitle() {
        return orderProductTitle;
    }

    public void setOrderProductTitle(String orderProductTitle) {
        this.orderProductTitle = orderProductTitle;
    }

    public String getOrderNewPrice() {
        return orderNewPrice;
    }

    public void setOrderNewPrice(String orderNewPrice) {
        this.orderNewPrice = orderNewPrice;
    }

    public String getOrderOldPrice() {
        return orderOldPrice;
    }

    public void setOrderOldPrice(String orderOldPrice) {
        this.orderOldPrice = orderOldPrice;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(orderUrl);
        parcel.writeString(orderTitle);
        parcel.writeString(orderSaleOut);
        parcel.writeString(orderRepertory);
        parcel.writeString(orderNumber);
        parcel.writeString(orderProductTitle);
        parcel.writeString(orderNewPrice);
        parcel.writeString(orderOldPrice);
        parcel.writeInt(type);
    }
}
