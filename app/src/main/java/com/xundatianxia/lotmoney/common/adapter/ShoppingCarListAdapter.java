package com.xundatianxia.lotmoney.common.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.MyApplication;
import com.xundatianxia.lotmoney.common.bean.HomeListBean;
import com.xundatianxia.lotmoney.common.bean.ShoppingCarBean;
import com.xundatianxia.lotmoney.common.utils.GlideUtils;

import java.util.List;

public class ShoppingCarListAdapter extends BaseQuickAdapter<ShoppingCarBean, BaseViewHolder> {


    private Context mContext;

    public ShoppingCarListAdapter(int layoutResId, @Nullable List<ShoppingCarBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ShoppingCarBean shoppingCarBean) {
//        helper.setText(R.id.tv_shopping_car_title, shoppingCarBean.getShoppingCarTitle());
        helper.setText(R.id.tv_shopping_car_sale_out, shoppingCarBean.getShoppingSaleOut());
        helper.setText(R.id.tv_shopping_car_repertory, shoppingCarBean.getShoppingCarRepertory());
        helper.setText(R.id.tv_shopping_car_price, shoppingCarBean.getShoppingCarPrice());
        helper.setText(R.id.tv_shopping_car_number, shoppingCarBean.getShoppingCarNumber());

        helper.addOnClickListener(R.id.iv_shopping_car_delete);
        helper.addOnClickListener(R.id.ll_shopping_car_subtract);
        helper.addOnClickListener(R.id.ll_shopping_car_add);
//        ImageView imageView = helper.getView(R.id.iv_shopping_car_url);
//        GlideUtils.getInstance().loadUserImage(MyApplication.getContext(), shoppingCarBean.getShoppingCarUrl(), imageView, R.drawable.ic_baoyou);
        ImageView delete = helper.getView(R.id.iv_shopping_car_delete);
        LinearLayout subtractLinearLayout = helper.getView(R.id.ll_shopping_car_subtract);
        LinearLayout addLinearLayout = helper.getView(R.id.ll_shopping_car_add);
    }

}
