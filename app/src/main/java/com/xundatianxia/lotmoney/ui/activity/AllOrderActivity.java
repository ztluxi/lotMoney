package com.xundatianxia.lotmoney.ui.activity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseActivity;
import com.xundatianxia.lotmoney.common.adapter.ViewPagerAdapter;
import com.xundatianxia.lotmoney.common.bean.AllOrderBean;
import com.xundatianxia.lotmoney.common.view.ScaleTransitionPagerTitleView;
import com.xundatianxia.lotmoney.ui.fragment.AllOrderFragment;
import com.xundatianxia.lotmoney.ui.fragment.PendingReceiptFragment;
import com.xundatianxia.lotmoney.ui.fragment.PrePaymentFragment;
import com.xundatianxia.lotmoney.ui.fragment.WaitForDeliveryFragment;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by zt on 2019/7/2.
 */
public class AllOrderActivity extends BaseActivity {

    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.image_title_left)
    ImageView imageTitleLeft;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<AllOrderBean> allOrderBeans = new ArrayList<>();
    private List<String> mTitleDataList = new ArrayList<>();
    private ViewPagerAdapter pagerAdapter;
    private FragmentContainerHelper mFramentContainerHelper;
    private int type;//指定页面

    @Override
    public int getLayout() {
        return R.layout.avtivity_all_order;
    }

    @Override
    public void initView() {
        textTitle.setText(getString(R.string.my_all_orders));
        mTitleDataList.add(getString(R.string.my_all));
        mTitleDataList.add(getString(R.string.my_obligation));
        mTitleDataList.add(getString(R.string.my_send_the_goods));
        mTitleDataList.add(getString(R.string.my_wait_for_receiving));
        imageTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        type = getIntent().getIntExtra("type", -1);


        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        intOrderData();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(AllOrderFragment.newInstance(allOrderBeans, 0));
        fragments.add(PrePaymentFragment.newInstance(allOrderBeans, 1));
        fragments.add(PendingReceiptFragment.newInstance(allOrderBeans, 2));
        fragments.add(WaitForDeliveryFragment.newInstance(allOrderBeans, 3));
        pagerAdapter.setItems(fragments, mTitleDataList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(mTitleDataList.size());

        initMagicIndicator();
        //指定进入fragment
        viewPager.setCurrentItem(type);
        mFramentContainerHelper = new FragmentContainerHelper(magicIndicator);
        mFramentContainerHelper.handlePageSelected(type);



    }

    private void intOrderData() {
        for (int i = 0; i < 5; i++) {
            AllOrderBean orderBean = new AllOrderBean();
            orderBean.setOrderTitle("" + i);
            orderBean.setOrderNewPrice("26" + i);
            orderBean.setOrderOldPrice("36" + i);
            orderBean.setOrderOldPrice("36" + i);
            if (i == 2) {
                orderBean.setType(2);
                orderBean.setOrderTitle("待发货");
            } else if (i == 3) {
                orderBean.setType(3);
                orderBean.setOrderTitle("待收货");
            } else if (i == 1) {
                orderBean.setType(1);
                orderBean.setOrderTitle("待付款");
            }
            allOrderBeans.add(orderBean);
        }

    }

    //初始化滑动条
    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.color_4a4a4a));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.color_base_red));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                indicator.setColors(getResources().getColor(R.color.color_base_red));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                if (index == 0) {
                    return 2.0f;
                } else if (index == 1) {
                    return 1.5f;
                } else {
                    return 1.5f;
                }
            }

        });

        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);


    }


    @Override
    public void initData() {

    }

}
