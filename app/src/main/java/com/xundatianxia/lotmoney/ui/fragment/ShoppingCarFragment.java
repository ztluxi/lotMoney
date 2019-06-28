package com.xundatianxia.lotmoney.ui.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.common.adapter.ShoppingCarListAdapter;
import com.xundatianxia.lotmoney.common.bean.ShoppingCarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingCarFragment extends BaseFragment {


    private ShoppingCarListAdapter adapter;
    private List<ShoppingCarBean> shoppingCarBeans = new ArrayList<>();
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_shopping_car)
    RecyclerView rvShoppingCar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.cb_all_choose)
    CheckBox cbAllChoose;
    @BindView(R.id.ll_all_choose)
    LinearLayout llAllChoose;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;


    @Override
    protected int getLayout() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    protected void initView() {
        initTitle(getString(R.string.main_tab_shopping_car));
        tvTitleRight.setText(getText(R.string.shopping_car_clear));
        tvTitleRight.setTextColor(getResources().getColor(R.color.color_base_red));
        tvTitleRight.setVisibility(View.VISIBLE);

        adapter = new ShoppingCarListAdapter(R.layout.fragment_shopping_car_item, shoppingCarBeans);
        rvShoppingCar.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvShoppingCar.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                ShoppingCarBean bean = shoppingCarBeans.get(position);
                int number = Integer.parseInt(bean.getShoppingCarNumber());
                switch (itemViewId) {
                    case R.id.iv_shopping_car_delete:
                        adapter.remove(position);
                        adapter.notifyItemChanged(position);
                        break;

                    case R.id.ll_shopping_car_subtract:
                        bean.setShoppingCarNumber(String.valueOf(number - 1));
                        adapter.notifyItemChanged(position);
                        break;

                    case R.id.ll_shopping_car_add:
                        bean.setShoppingCarNumber(String.valueOf(number + 1));

                        adapter.notifyItemChanged(position);
                        break;
                }
            }
        });

    }

    @Override
    public void initData() {
        for (int i = 0; i < 5; i++) {
            ShoppingCarBean bean = new ShoppingCarBean();
            bean.setShoppingCarNumber(10 + i + "");
            bean.setShoppingCarPrice(31 + i + "");
            bean.setShoppingCarRepertory(800 + i + "");
//            bean.setShoppingCarStore(100+i+"");
            bean.setShoppingSaleOut(60 + i + "");
//            bean.setShoppingCarTitle(60+i+"");
//            bean.setShoppingCarUrl("");
            shoppingCarBeans.add(bean);
        }
        adapter.addData(shoppingCarBeans);
        adapter.notifyDataSetChanged();

    }

    @OnClick({R.id.tv_title_right, R.id.ll_all_choose, R.id.tv_all_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                break;
            case R.id.ll_all_choose:
                break;
            case R.id.tv_all_money:
                break;
        }
    }
}
