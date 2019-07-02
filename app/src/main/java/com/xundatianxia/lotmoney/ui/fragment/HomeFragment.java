package com.xundatianxia.lotmoney.ui.fragment;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.common.adapter.HomeListAdapter;
import com.xundatianxia.lotmoney.common.bean.HomeListBean;
import com.xundatianxia.lotmoney.common.persenter.HomePresenter;
import com.xundatianxia.lotmoney.common.utils.GlideImageLoader;
import com.xundatianxia.lotmoney.common.utils.GridSpacingItemDecoration;
import com.xundatianxia.lotmoney.common.view.HomeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeView {
    private HomePresenter presenter;
    private HomeListAdapter adapter;
    private List<HomeListBean> listBeans = new ArrayList<>();
    @BindView(R.id.mv_notice)
    MarqueeView marqueeView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout relativeLayout;
    @BindView(R.id.rv)
    RecyclerView home_list;
    //    @BindView(R.id.banner)
//    Banner banner;
    String image1 = "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=c87e52ca1a38534393cf8121a312b01f/e1fe9925bc315c60c9fcca4987b1cb134954772f.jpg";
    String image2 = "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=92afee66fd36afc3110c39658318eb85/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg";
    List<String> images = new ArrayList<>();
    List<String> mesg = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        List<String> messages = new ArrayList<>();
        messages.add("每日上新，打牌显示特卖，优选新品任你挑！");
        messages.add("1. 大家好，我是孙福生。");
        marqueeView.startWithList(messages);
        adapter = new HomeListAdapter(listBeans, getActivity());
        home_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        home_list.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        home_list.setAdapter(adapter);
        relativeLayout.setEnableAutoLoadmore(true);
        relativeLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                listBeans.clear();
                for (int i = 0; i < 10; i++) {
                    HomeListBean bean = new HomeListBean();
                    bean.setItemType(2);
                    bean.setProductStore("" + 20 + i);
                    bean.setProductOldPrice("" + 5 + i);
                    bean.setProductNewPrice("" + 500 + i);
                    bean.setProductSell("已售" + 52 + i);
                    bean.setProductTitle("三只松鼠-夏至春草240g抹\n" +
                            "茶零食】超级好吃哦");
                    bean.setProductType("2131");
                    bean.setProductUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561437716069&di=f4530be9880e4c863e093ef52b6f2055&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181013%2F2dc91e45e69949e58744644fe19225fa.jpeg");
                    listBeans.add(bean);
                }
                relativeLayout.finishLoadmore();
                relativeLayout.finishRefresh();
                adapter.notifyDataSetChanged();
            }
        });
        relativeLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 10; i++) {
                    HomeListBean bean = new HomeListBean();
                    bean.setItemType(2);
                    bean.setProductStore("" + 20 + i);
                    bean.setProductOldPrice("" + 5 + i);
                    bean.setProductNewPrice("" + 500 + i);
                    bean.setProductSell("已售" + 52 + i);
                    bean.setProductTitle("三只松鼠-夏至春草240g抹\n" +
                            "茶零食】超级好吃哦");
                    bean.setProductType("2131");
                    bean.setProductUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561437716069&di=f4530be9880e4c863e093ef52b6f2055&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181013%2F2dc91e45e69949e58744644fe19225fa.jpeg");
                    listBeans.add(bean);
                }
                relativeLayout.finishLoadmore();
                relativeLayout.finishRefresh();
                adapter.notifyDataSetChanged();
            }
        });
        //添加头部
        adapter.addHeaderView(getHeaderView(1, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }), 0);
        relativeLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        presenter = new HomePresenter(getActivity(), this);
        presenter.getMineList();
    }

    //头部布局
    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.fragment_home_item_top, (ViewGroup) home_list.getParent(), false);
        if (type == 1) {
            Banner banner1 = view.findViewById(R.id.banner);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    images.clear();
                    images.add(image1);
                    images.add(image2);
                    //设置图片加载器
                    banner1.setImages(images)
                            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                            .isAutoPlay(true)
                            .setImageLoader(new GlideImageLoader())
                            .start();

                }
            }, 100);
        }
        view.setOnClickListener(listener);
        return view;
    }

    @Override
    public void initData() {
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
            listBeans.add(bean);
        }
        adapter.addData(listBeans);
        adapter.notifyDataSetChanged();
    }
//
//    @OnClick({R.id.tv_home})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_home:
//                presenter = new HomePresenter(getActivity(), this);
//                presenter.getMineList();
//                break;
//
//        }
//    }

    @Override
    public void success(String success) {
    }

    @Override
    public void fail(String fail) {
    }

}
