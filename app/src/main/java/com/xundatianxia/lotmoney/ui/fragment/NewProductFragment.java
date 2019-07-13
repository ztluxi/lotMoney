package com.xundatianxia.lotmoney.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.common.adapter.HomeListAdapter;
import com.xundatianxia.lotmoney.common.bean.AllOrderBean;
import com.xundatianxia.lotmoney.common.bean.HomeListBean;
import com.xundatianxia.lotmoney.common.utils.GridSpacingItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zt on 2019/7/4.
 * 最新产品
 */
public class NewProductFragment extends BaseFragment {

    @BindView(R.id.rv_all_order)
    RecyclerView rvAllOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<HomeListBean> homeListBeans = new ArrayList<>();
    private HomeListAdapter adapter;

    public static NewProductFragment newInstance(ArrayList<HomeListBean> homeListBeans) {
        Bundle args = new Bundle();
        NewProductFragment fragment = new NewProductFragment();
        args.putParcelableArrayList("newP", homeListBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            homeListBeans = getArguments().getParcelableArrayList("newP");
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all_order;
    }

    @Override
    protected void initView() {
        adapter = new HomeListAdapter(homeListBeans, getActivity());
        rvAllOrder.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvAllOrder.addItemDecoration(new GridSpacingItemDecoration(2, 10, true));
        rvAllOrder.setAdapter(adapter);
    }


    @Override
    public void initData() {

    }
}
