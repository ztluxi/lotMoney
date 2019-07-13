package com.xundatianxia.lotmoney.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseFragment;
import com.xundatianxia.lotmoney.common.adapter.ShoppingCarListAdapter;
import com.xundatianxia.lotmoney.common.bean.ShoppingCarBean;
import com.xundatianxia.lotmoney.common.utils.ToastManager;
import com.xundatianxia.lotmoney.ui.activity.CloseAnAccountActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by zt on 2019/7/3.
 * 购物车
 */
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
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_close_an_account)
    TextView tvCloseAnAccount;
    private Double allMoney = 0.0;//总价
    private int allNumber = 0;//总数量


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

        adapter = new ShoppingCarListAdapter(R.layout.adapter_shopping_car_item, shoppingCarBeans);
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
                        break;
                    case R.id.ll_shopping_car_subtract:
                        if (Integer.parseInt(bean.getShoppingCarNumber()) <= 1) {
                            ToastManager.showShort(getActivity(), "宝贝不能再减少了哦！");
                        } else {
                            bean.setShoppingCarNumber(String.valueOf(number - 1));
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.ll_shopping_car_add:
                        bean.setShoppingCarNumber(String.valueOf(number + 1));
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        initListen();
    }

    private void initListen() {
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 5; i++) {
                    ShoppingCarBean bean = new ShoppingCarBean();
                    bean.setShoppingCarNumber(10 + i + "");
                    bean.setShoppingCarPrice(1 + i + "");
                    bean.setShoppingCarRepertory(800 + i + "");
                    bean.setType(2);
                    bean.setShoppingSaleOut(60 + i + "");
                    bean.setShoppingCarTitle("2017夏季男士短袖T恤韩版翻领polo衫V领潮流上衣男装修" + i + "");
                    shoppingCarBeans.add(bean);
                }
                adapter.addData(shoppingCarBeans);
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
                refreshlayout.finishRefresh();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                shoppingCarBeans.clear();
                for (int i = 0; i < 5; i++) {
                    ShoppingCarBean bean = new ShoppingCarBean();
                    bean.setShoppingCarNumber(10 + i + "");
                    bean.setShoppingCarPrice(1 + i + "");
                    bean.setShoppingCarRepertory(800 + i + "");
                    bean.setType(2);
                    bean.setShoppingSaleOut(60 + i + "");
                    bean.setShoppingCarTitle("2017夏季男士短袖T恤韩版翻领polo衫V领潮流上衣男装修" + i + "");
                    shoppingCarBeans.add(bean);
                }
                adapter.addData(shoppingCarBeans);
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
                refreshlayout.finishRefresh();
            }
        });
    }

    @Override
    public void initData() {
        shoppingCarBeans.clear();
        for (int i = 0; i < 5; i++) {
            ShoppingCarBean bean = new ShoppingCarBean();
            bean.setShoppingCarNumber(10 + i + "");
            bean.setShoppingCarPrice(1 + i + "");
            bean.setShoppingCarRepertory(800 + i + "");
            bean.setType(2);
            bean.setShoppingSaleOut(60 + i + "");
            bean.setShoppingCarTitle("2017夏季男士短袖T恤韩版翻领polo衫V领潮流上衣男装修" + i + "");
//            bean.setShoppingCarUrl("");
//            bean.setShoppingCarStore(100+i+"");
            shoppingCarBeans.add(bean);
        }
        adapter.addData(shoppingCarBeans);
        adapter.notifyDataSetChanged();

    }

    @OnClick({R.id.tv_title_right, R.id.cb_all_choose, R.id.tv_all_money, R.id.tv_close_an_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_title_right:
                break;
            case R.id.cb_all_choose:

                for (int i = 0; i < shoppingCarBeans.size(); i++) {
                    allMoney += Double.parseDouble(shoppingCarBeans.get(i).getShoppingCarPrice());
                    allNumber += Integer.parseInt(shoppingCarBeans.get(i).getShoppingCarNumber());
                    ShoppingCarBean bean = new ShoppingCarBean();
                    if (cbAllChoose.isChecked()) {
                        bean.setType(1);
                    } else {
                        bean.setType(2);
                    }
                    bean.setShoppingCarNumber(adapter.getData().get(i).getShoppingCarNumber());
                    bean.setShoppingCarTitle(adapter.getData().get(i).getShoppingCarTitle());
                    bean.setShoppingCarStore(adapter.getData().get(i).getShoppingCarStore());
                    bean.setShoppingSaleOut(adapter.getData().get(i).getShoppingSaleOut());
                    bean.setShoppingCarPrice(adapter.getData().get(i).getShoppingCarPrice());
                    bean.setShoppingCarRepertory(adapter.getData().get(i).getShoppingCarRepertory());
                    shoppingCarBeans.set(i, bean);
                }
                if (cbAllChoose.isChecked()) {
                    tvAllMoney.setText("¥" + allMoney);
                    tvCloseAnAccount.setText(getText(R.string.shopping_car_close_an_account) + "(" + allNumber + ")");
                } else {
                    tvAllMoney.setText("¥" + 0.0);
                    tvCloseAnAccount.setText(getText(R.string.shopping_car_close_an_account));
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_all_money:


                break;
            case R.id.tv_close_an_account:
                startActivity(new Intent(getActivity(), CloseAnAccountActivity.class));
                break;
        }
    }
}
