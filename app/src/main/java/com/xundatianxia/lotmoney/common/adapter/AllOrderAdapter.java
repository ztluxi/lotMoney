package com.xundatianxia.lotmoney.common.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.MyApplication;
import com.xundatianxia.lotmoney.common.bean.AllOrderBean;
import com.xundatianxia.lotmoney.common.utils.GlideUtils;

import java.util.List;

/**
 * Created by zt on 2019/7/2.
 */
public class AllOrderAdapter extends BaseQuickAdapter<AllOrderBean, BaseViewHolder> {


    public AllOrderAdapter(int layoutResId, @Nullable List<AllOrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllOrderBean item) {
                helper.setText(R.id.tv_order_new_price, item.getOrderNewPrice());
                helper.setText(R.id.tv_order_old_price, item.getOrderOldPrice());
                helper.setText(R.id.tv_order_number, item.getOrderNumber());
                helper.setText(R.id.tv_order_sale_out, item.getOrderSaleOut());
                helper.setText(R.id.tv_order_repertory, item.getOrderRepertory());
                helper.setText(R.id.tv_order_title, item.getOrderTitle());
                ImageView imageView = helper.getView(R.id.iv_order_product_url);
                GlideUtils.getInstance().loadUserImage(MyApplication.getContext(), item.getOrderUrl(), imageView, R.drawable.ic_user_center_bg);

    }
}
