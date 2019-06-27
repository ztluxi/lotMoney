package com.xundatianxia.lotmoney.ui.fragment;


import android.widget.ImageView;
import android.widget.TextView;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;

import butterknife.BindView;

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
}
