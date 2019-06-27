package com.xundatianxia.lotmoney.common.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HomeListBean implements MultiItemEntity  {
    public static final int TOP = 1;
    public static final int IMG = 2;
    private String productUrl;
    private String productTitle;
    private String productType;//类型
    private String productStore;//库存
    private int itemType;

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
}
