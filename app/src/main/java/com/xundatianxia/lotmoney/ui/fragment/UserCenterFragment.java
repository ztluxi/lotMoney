package com.xundatianxia.lotmoney.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.ui.activity.ShoppingAddressActivity;

import butterknife.BindView;
import butterknife.OnClick;

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

    @OnClick({R.id.my_integral, R.id.my_clear_cache, R.id.my_shopping_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_integral:

                break;
            case R.id.my_clear_cache:

                break;
            case R.id.my_shopping_address:
                startActivity(new Intent(getActivity(), ShoppingAddressActivity.class));
                break;
        }
    }
}
