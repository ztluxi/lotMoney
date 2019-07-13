package com.xundatianxia.lotmoney.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HomeListBean implements MultiItemEntity, Parcelable {
    public static final int TOP = 1;
    public static final int IMG = 2;
    private String productUrl;
    private String productTitle;
    private String productType;//类型
    private String productStore;//库存
    private int itemType;

    public HomeListBean(Parcel in) {
        productUrl = in.readString();
        productTitle = in.readString();
        productType = in.readString();
        productStore = in.readString();
        itemType = in.readInt();
        productSell = in.readString();
        productOldPrice = in.readString();
        productNewPrice = in.readString();
    }

    public static final Creator<HomeListBean> CREATOR = new Creator<HomeListBean>() {
        @Override
        public HomeListBean createFromParcel(Parcel in) {
            return new HomeListBean(in);
        }

        @Override
        public HomeListBean[] newArray(int size) {
            return new HomeListBean[size];
        }
    };

    public HomeListBean() {

    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    private String productSell;//已售
    private String productOldPrice;//旧价格
    private String productNewPrice;//最新价格

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductStore() {
        return productStore;
    }

    public void setProductStore(String productStore) {
        this.productStore = productStore;
    }

    public String getProductSell() {
        return productSell;
    }

    public void setProductSell(String productSell) {
        this.productSell = productSell;
    }

    public String getProductOldPrice() {
        return productOldPrice;
    }

    public void setProductOldPrice(String productOldPrice) {
        this.productOldPrice = productOldPrice;
    }

    public String getProductNewPrice() {
        return productNewPrice;
    }

    public void setProductNewPrice(String productNewPrice) {
        this.productNewPrice = productNewPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productUrl);
        parcel.writeString(productTitle);
        parcel.writeString(productType);
        parcel.writeString(productStore);
        parcel.writeInt(itemType);
        parcel.writeString(productSell);
        parcel.writeString(productOldPrice);
        parcel.writeString(productNewPrice);
    }
}
