package com.xundatianxia.lotmoney.common.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.common.bean.AllOrderBean;

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
        helper.setText(R.id.tv_order_title, item.getOrderTitle());
    }
}
