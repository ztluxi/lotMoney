package com.xundatianxia.lotmoney.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseActivity;
import com.xundatianxia.lotmoney.common.adapter.ShoppingAddressAdapter;
import com.xundatianxia.lotmoney.common.bean.ShoppingAddressBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingAddressActivity extends BaseActivity {

    private ShoppingAddressAdapter shoppingAddressAdapter;
    private List<ShoppingAddressBean> shoppingAddressBeans = new ArrayList<>();
    @BindView(R.id.image_title_left)
    ImageView imageTitleLeft;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.rv_add_new_address)
    RecyclerView rvAddNewAddress;
    @BindView(R.id.tv_add_new_address)
    TextView tvAddNewAddress;

    @Override
    public int getLayout() {
        return R.layout.avtivity_shopping_address;
    }

    @Override
    public void initView() {
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        initTitle(getString(R.string.my_shopping_address));
        imageTitleLeft.setVisibility(View.VISIBLE);
        rvAddNewAddress.setLayoutManager(new LinearLayoutManager(this));
        shoppingAddressAdapter = new ShoppingAddressAdapter(R.layout.avtivity_shopping_address_item, shoppingAddressBeans);
        rvAddNewAddress.setAdapter(shoppingAddressAdapter);
        onclick();
    }

    void onclick() {
        shoppingAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                ShoppingAddressBean bean = shoppingAddressBeans.get(position);
                switch (itemViewId) {
                    case R.id.iv_shopping_address_delete:
                        adapter.remove(position);
                        adapter.notifyItemChanged(position);
                        break;
                    case R.id.iv_shopping_address_edit:


                        break;
                    case R.id.cb_default_address:
                        bean.setType(2);
                        adapter.notifyItemChanged(position);
                        break;

                }

            }
        });


    }

    @Override
    public void initData() {
        shoppingAddressBeans.clear();
        for (int i = 0; i < 5; i++) {
            ShoppingAddressBean bean = new ShoppingAddressBean();
            bean.setType(1);
            bean.setShoppingConsignee("张三" + i);
            bean.setShoppingConsigneeAddress("浙江-杭州-下城区 好家电家具" + i);
            bean.setShoppingConsignee("1850305856" + i);
            shoppingAddressBeans.add(bean);
        }
        shoppingAddressAdapter.addData(shoppingAddressBeans);
        shoppingAddressAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.image_title_left, R.id.tv_add_new_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_new_address:
                startActivity(new Intent(ShoppingAddressActivity.this, AddNewAddressActivity.class));
                break;
        }
    }
}
