package com.xundatianxia.lotmoney.base;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTabHost;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.common.utils.ToastManager;
import com.xundatianxia.lotmoney.ui.fragment.HomeFragment;
import com.xundatianxia.lotmoney.ui.fragment.ShoppingCarFragment;
import com.xundatianxia.lotmoney.ui.fragment.UserCenterFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    private static final String TAB_HOME = "TAB_HOME";
    private static final String TAB_SHOOPING_CAT = "TAB_SHOOPING_CAT";
    private static final String TAB_USER_CENTER = "TAB_USER_CENTER";

    private enum BOTTOM_ITEM {
        HOME, SHOPPING_CAT, USER_CENTER
    }

    private long backTime; // 记录用户点击的时间
    private BOTTOM_ITEM curItem = BOTTOM_ITEM.HOME;

    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;
    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    @BindView(R.id.tv_tabhost_home)
    TextView tv_tabhost_home;
    @BindView(R.id.tv_tabhost_shooping_car)
    TextView tv_tabhost_shooping_car;
    @BindView(R.id.tv_tabhost_user)
    TextView tv_tabhost_user;

    @OnClick(R.id.tv_tabhost_home)
    void clickHome() {
        curItem = BOTTOM_ITEM.HOME;
        setBottom();
    }

    @OnClick(R.id.tv_tabhost_shooping_car)
    void clickShoppingCar() {
        curItem = BOTTOM_ITEM.SHOPPING_CAT;
        setBottom();
    }

    @OnClick(R.id.tv_tabhost_user)
    void clickUserCenter() {
        curItem = BOTTOM_ITEM.USER_CENTER;
        setBottom();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabhost.getTabWidget().setVisibility(View.GONE);
        tabhost.setCurrentTabByTag(TAB_HOME);
        tv_tabhost_home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_home_sel), null, null);
        tv_tabhost_home.setTextColor(getResources().getColor(R.color.color_base_red));
        tabHostAddTab();
        initData();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setBottom();
    }

    private void tabHostAddTab() {
        tabhost.addTab(tabhost.newTabSpec(TAB_HOME).setIndicator(TAB_HOME), HomeFragment.class, null);
        tabhost.addTab(tabhost.newTabSpec(TAB_SHOOPING_CAT).setIndicator(TAB_SHOOPING_CAT), ShoppingCarFragment.class, null);
        tabhost.addTab(tabhost.newTabSpec(TAB_USER_CENTER).setIndicator(TAB_USER_CENTER), UserCenterFragment.class, null);
    }

    private void setBottom() {
        unCheckAllBottom();
        switch (curItem) {
            case HOME:
                tv_tabhost_home.setTextColor(getResources().getColor(R.color.color_base_red));
                tv_tabhost_home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_home_sel), null, null);
                tabhost.setCurrentTabByTag(TAB_HOME);
                break;
            case SHOPPING_CAT:
                tv_tabhost_shooping_car.setTextColor(getResources().getColor(R.color.color_base_red));
                tv_tabhost_shooping_car.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_car_sel), null, null);
                tabhost.setCurrentTabByTag(TAB_SHOOPING_CAT);
                break;
            case USER_CENTER:
                tv_tabhost_user.setTextColor(getResources().getColor(R.color.color_base_red));
                tv_tabhost_user.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_user_sel), null, null);
                tabhost.setCurrentTabByTag(TAB_USER_CENTER);
                break;
        }
    }

    private void unCheckAllBottom() {
        tv_tabhost_home.setTextColor(getResources().getColor(R.color.color_999999));
        tv_tabhost_home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_index), null, null);

        tv_tabhost_shooping_car.setTextColor(getResources().getColor(R.color.color_999999));
        tv_tabhost_shooping_car.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_cart), null, null);

        tv_tabhost_user.setTextColor(getResources().getColor(R.color.color_999999));
        tv_tabhost_user.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.drawable.ic_user), null, null);

    }


    // 添加系统监听的返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - backTime) > 2000) {
                backTime = System.currentTimeMillis();
                ToastManager.showShort(this, getString(R.string.again_exit));
            } else if ((System.currentTimeMillis() - backTime) < 2000) {
                backTime = System.currentTimeMillis();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
