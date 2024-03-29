package com.xundatianxia.lotmoney.ui.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.renderscript.AllocationAdapter;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.common.adapter.AllOrderAdapter;
import com.xundatianxia.lotmoney.common.bean.AllOrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zt on 2019/7/2.
 */
public class AllOrderFragment extends BaseFragment {
    @BindView(R.id.rv_all_order)
    RecyclerView rvAllOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private AllOrderAdapter allOrderAdapter;
    private List<AllOrderBean> allOrderBeans = new ArrayList<>();
    private int type;

    public static AllOrderFragment newInstance(ArrayList<AllOrderBean> allOrderBeans, int i) {
        AllOrderFragment fragment = new AllOrderFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("myOrder", allOrderBeans);
        args.putInt("type", i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all_order;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allOrderBeans = getArguments().getParcelableArrayList("myOrder");
            type = getArguments().getInt("type");
        }
    }

    @Override
    protected void initView() {
        allOrderAdapter = new AllOrderAdapter(R.layout.adapter_all_order_item, allOrderBeans);
        rvAllOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setEnableAutoLoadmore(true);
        rvAllOrder.setAdapter(allOrderAdapter);
    }

    @Override
    public void initData() {


    }
}
