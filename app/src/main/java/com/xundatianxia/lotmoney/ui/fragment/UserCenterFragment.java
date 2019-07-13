package com.xundatianxia.lotmoney.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.ui.activity.AllOrderActivity;
import com.xundatianxia.lotmoney.ui.activity.ShoppingAddressActivity;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by zt on 2019/7/3.
 * 用户中心
 */
public class UserCenterFragment extends BaseFragment {


    @BindView(R.id.text_title)
    TextView tv_title;
    @BindView(R.id.image_title_right)
    ImageView image_title_right;

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_center;
    }

    @Override
    protected void initView() {
        tv_title.setText(getString(R.string.main_tab_mine));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.my_integral, R.id.my_clear_cache, R.id.my_shopping_address, R.id.tv_all_order,
            R.id.tv_pending_receipt, R.id.tv_wait_for_delivery, R.id.tv_pre_payment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_integral:

                break;
            case R.id.my_clear_cache:

                break;
            case R.id.tv_all_order:
                Intent intent = new Intent(getActivity(), AllOrderActivity.class);
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
            case R.id.my_shopping_address:
                startActivity(new Intent(getActivity(), ShoppingAddressActivity.class));
                break;
            case R.id.tv_pre_payment:
                Intent intent1 = new Intent(getActivity(), AllOrderActivity.class);
                intent1.putExtra("type", 1);
                startActivity(intent1);
                break;
            case R.id.tv_pending_receipt:
                Intent intent2 = new Intent(getActivity(), AllOrderActivity.class);
                intent2.putExtra("type", 2);
                startActivity(intent2);
                break;
            case R.id.tv_wait_for_delivery:
                Intent intent3 = new Intent(getActivity(), AllOrderActivity.class);
                intent3.putExtra("type", 3);
                startActivity(intent3);
                break;
        }
    }
}
