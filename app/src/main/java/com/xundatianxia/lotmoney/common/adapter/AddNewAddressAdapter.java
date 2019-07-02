package com.xundatianxia.lotmoney.common.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.common.bean.AddNewAddressBean;

import java.util.List;

/**
 * Created by zt on 2019/7/1.
 */
public class AddNewAddressAdapter extends BaseQuickAdapter<AddNewAddressBean, BaseViewHolder> {

    public AddNewAddressAdapter(int layoutResId, @Nullable List<AddNewAddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddNewAddressBean item) {

        helper.setText(R.id.tv_shopping_address_phone, item.getShoppingConsigneePhone());
        helper.setText(R.id.tv_shopping_consignee, item.getShoppingConsignee());
        helper.setText(R.id.tv_shopping_address, item.getShoppingConsigneeAddress());

        helper.addOnClickListener(R.id.iv_shopping_address_delete);
        helper.addOnClickListener(R.id.iv_shopping_address_edit);
        helper.addOnClickListener(R.id.cb_default_address);
        ImageView cbDefault = helper.getView(R.id.cb_default_address);
        if (item.getType() == 1) {
            cbDefault.setImageResource(R.drawable.ic_default_shopping_address_sel);
        } else {
            cbDefault.setImageResource(R.drawable.ic_choose_nor);
        }
    }
}
