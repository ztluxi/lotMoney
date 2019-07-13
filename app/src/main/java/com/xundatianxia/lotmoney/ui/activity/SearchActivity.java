package com.xundatianxia.lotmoney.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseActivity;
import com.xundatianxia.lotmoney.common.adapter.ViewPagerAdapter;
import com.xundatianxia.lotmoney.common.bean.HomeListBean;
import com.xundatianxia.lotmoney.common.view.ScaleTransitionPagerTitleView;
import com.xundatianxia.lotmoney.ui.fragment.NewProductFragment;
import com.xundatianxia.lotmoney.ui.fragment.SalesVolumeFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zt on 2019/7/1.
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_tagFlowLayout)
    LinearLayout llTagFlowLayout;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    private LayoutInflater mInflater;
    private List<String> stringList = new ArrayList<>();
    private String searchText = "";
    private List<String> mTitleDataList = new ArrayList<>();
    private ArrayList<HomeListBean> homeListBeans = new ArrayList<>();
    private ViewPagerAdapter pagerAdapter;


    @Override
    public int getLayout() {
        return R.layout.avtivity_search;
    }

    @Override
    public void initView() {
        stringList.add("运动鞋");
        stringList.add("资生堂可悠然美肌");
        stringList.add("余文乐潮牌");
        stringList.add("出浴素颜霜");
        stringList.add("呢子女大衣");
        stringList.add("剪刀 厨房");


        mTitleDataList.add(getString(R.string.home_search_sales_volume));
        mTitleDataList.add(getString(R.string.home_search_new_product));

        mInflater = LayoutInflater.from(SearchActivity.this);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (position < stringList.size()) {
                    searchText = stringList.get(position);
//                    doSearch();
                    llContent.setVisibility(View.VISIBLE);
                    llTagFlowLayout.setVisibility(View.GONE);
                }
                return true;
            }
        });

        flowLayout.setAdapter(new TagAdapter<String>(stringList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.adapter_search_flow_layout_item,
                        flowLayout, false);
                tv.setText(stringList.get(position));
                return tv;
            }
        });

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        intProductData();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SalesVolumeFragment.newInstance(homeListBeans));
        fragments.add(NewProductFragment.newInstance(homeListBeans));
        pagerAdapter.setItems(fragments, mTitleDataList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(mTitleDataList.size());
        initMagicIndicator();

        SearchContent();
    }

    void SearchContent() {
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (count > 0) {
                    tv_cancel.setText(getString(R.string.home_search));
                } else if (count == 0) {
                    tv_cancel.setText(getString(R.string.home_cancel));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideSoftKeyboard(et_search);
                    //do something
                    doSearch();
//                    ToastManager.showShort(SearchActivity.this, "点击了软键盘的搜索按钮");
                    return true;
                }
                return false;
            }
        });


    }


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
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
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

    private void doSearch() {
        llContent.setVisibility(View.VISIBLE);
        llTagFlowLayout.setVisibility(View.GONE);
    }

    private void intProductData() {
        for (int i = 0; i < 10; i++) {
            HomeListBean bean = new HomeListBean();
            bean.setItemType(2);
            bean.setProductStore("" + 100 + i);
            bean.setProductOldPrice("" + 300 + i);
            bean.setProductNewPrice("" + 200 + i);
            bean.setProductSell("已售" + 12 + i);
            bean.setProductTitle("三只松鼠-夏至春草240g抹\n" +
                    "茶零食】超级好吃哦");
            bean.setProductType("2131");
            bean.setProductUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561437716069&di=f4530be9880e4c863e093ef52b6f2055&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181013%2F2dc91e45e69949e58744644fe19225fa.jpeg");
            homeListBeans.add(bean);
        }

    }

    @OnClick(R.id.flowLayout)
    public void onViewClicked() {

    }

    @OnClick(R.id.tv_cancel)
    public void onCancel() {
        String cancel = tv_cancel.getText().toString();
        if (cancel.equals("取消")) {
            finish();
        } else {
            doSearch();
        }
    }

}
