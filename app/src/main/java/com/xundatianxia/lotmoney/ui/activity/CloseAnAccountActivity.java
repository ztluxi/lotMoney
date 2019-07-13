package com.xundatianxia.lotmoney.ui.activity;

import android.view.View;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseActivity;

/**
 * Created by zt on 2019/7/5.
 * 结算页面
 */
public class CloseAnAccountActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_close_an_account;
    }

    @Override
    public void initView() {
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        initTitle(getString(R.string.shopping_car_close_an_account));
        image_title_left.setVisibility(View.VISIBLE);

    }

    @Override
    public void initData() {

    }
}
