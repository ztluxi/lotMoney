package com.xundatianxia.lotmoney.common.bean;

/**
 * Created by zt on 2019/7/1.
 */
public class ShoppingAddressBean {
    private int type;//是否是默认地址
    private String shoppingConsignee;//收货人
    private String shoppingConsigneePhone;//收货人电话
    private String shoppingConsigneeAddress;//收货人地址

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getShoppingConsignee() {
        return shoppingConsignee;
    }

    public void setShoppingConsignee(String shoppingConsignee) {
        this.shoppingConsignee = shoppingConsignee;
    }

    public String getShoppingConsigneePhone() {
        return shoppingConsigneePhone;
    }

    public void setShoppingConsigneePhone(String shoppingConsigneePhone) {
        this.shoppingConsigneePhone = shoppingConsigneePhone;
    }

    public String getShoppingConsigneeAddress() {
        return shoppingConsigneeAddress;
    }

    public void setShoppingConsigneeAddress(String shoppingConsigneeAddress) {
        this.shoppingConsigneeAddress = shoppingConsigneeAddress;
    }

}
