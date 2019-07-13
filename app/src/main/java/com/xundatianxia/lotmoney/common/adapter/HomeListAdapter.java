package com.xundatianxia.lotmoney.common.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.MyApplication;
import com.xundatianxia.lotmoney.common.bean.HomeListBean;
import com.xundatianxia.lotmoney.common.utils.GlideUtils;

import java.util.List;

public class HomeListAdapter extends BaseMultiItemQuickAdapter<HomeListBean, BaseViewHolder> {


    private Context mContext;

    public HomeListAdapter(List<HomeListBean> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(HomeListBean.TOP, R.layout.fragment_home_item_top);
        addItemType(HomeListBean.IMG, R.layout.adapter_home_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListBean homeListBean) {
        switch (helper.getItemViewType()) {
            case HomeListBean.TOP:
//                images.add(homeListBean.getProductUrl());
//                Banner banner = helper.getView(R.id.banner);
//                RecyclerView recyclerView = helper.getView(R.id.ry_home_item);
//                LinearLayoutManager ms= new LinearLayoutManager(mContext);
//                ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
//                recyclerView.setLayoutManager(ms);
//                banner.setImages(images)
//                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
//                        .isAutoPlay(true)
//                        .setImageLoader(new GlideImageLoader())
//                        .start();
                break;
            case HomeListBean.IMG:
                helper.setText(R.id.tv_product_title, homeListBean.getProductTitle());
                helper.setText(R.id.tv_product_store, homeListBean.getProductStore());
                helper.setText(R.id.tv_product_old_price, homeListBean.getProductOldPrice());
                helper.setText(R.id.tv_product_sell, homeListBean.getProductSell());
                helper.setText(R.id.tv_product_new_price, homeListBean.getProductNewPrice());
                ImageView imageView = helper.getView(R.id.iv_product_url);
                GlideUtils.getInstance().loadUserImage(MyApplication.getContext(), homeListBean.getProductUrl(), imageView, R.drawable.ic_baoyou);
                break;
        }
    }

}
