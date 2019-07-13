package com.xundatianxia.lotmoney.ui.fragment;

import android.os.Bundle;

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
 * Created by zt on 2019/7/3.
 * 等待收货
 */
public class WaitForDeliveryFragment extends BaseFragment {

    @BindView(R.id.rv_all_order)
    RecyclerView rvAllOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private AllOrderAdapter allOrderAdapter;
    private List<AllOrderBean> allOrderBeans = new ArrayList<>();

    public static WaitForDeliveryFragment newInstance(ArrayList<AllOrderBean> allOrderBeans, int i) {
        Bundle args = new Bundle();
        WaitForDeliveryFragment fragment = new WaitForDeliveryFragment();
        args.putParcelableArrayList("WFDORDER", allOrderBeans);
        args.putInt("type", i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            allOrderBeans = getArguments().getParcelableArrayList("WFDORDER");
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_all_order;
    }

    @Override
    protected void initView() {
        List<AllOrderBean> list = new ArrayList<>();
        for (int i = 0; i < allOrderBeans.size(); i++) {
            if (allOrderBeans.get(i).getType() == 3) {
                list.add(allOrderBeans.get(i));
            }
        }
        allOrderAdapter = new AllOrderAdapter(R.layout.adapter_all_order_item, list);
        rvAllOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setEnableAutoLoadmore(true);
        rvAllOrder.setAdapter(allOrderAdapter);


    }

    @Override
    public void initData() {

    }
}
