package com.xundatianxia.lotmoney.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xundatianxia.lotmoney.R;
import com.xundatianxia.lotmoney.base.BaseActivity;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class AddNewAddressActivity extends BaseActivity {
    private List<HotCity> hotCities;
    private int anim;
    private int theme;
    private boolean enable;
    @BindView(R.id.image_title_left)
    ImageView imageTitleLeft;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_add_new_address_name)
    EditText etAddNewAddressName;
    @BindView(R.id.et_add_new_address_phone)
    EditText etAddNewAddressPhone;
    @BindView(R.id.tv_add_new_address_area)
    TextView tvAddNewAddressArea;
    @BindView(R.id.et_add_new_address_street)
    TextView etAddNewAddressStreet;
    @BindView(R.id.et_add_new_address_detail)
    EditText etAddNewAddressDetail;

    @Override
    public int getLayout() {
        return R.layout.avtivity_add_new_shopping_address;
    }

    @Override
    public void initView() {
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();//解决状态栏透明时改变字体颜色
        initTitle(getString(R.string.my_add_new_address_title));
        imageTitleLeft.setVisibility(View.VISIBLE);
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText(getText(R.string.my_save));
        tvTitleRight.setTextSize(18);
        tvTitleRight.getPaint().setFakeBoldText(true);
        tvTitleRight.setTextColor(getResources().getColor(R.color.color_dc2726));

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.et_add_new_address_name, R.id.ck_add_new_address_default, R.id.et_add_new_address_phone, R.id.tv_add_new_address_area, R.id.et_add_new_address_street})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_add_new_address_name:
                break;
            case R.id.et_add_new_address_phone:
                break;
            case R.id.tv_add_new_address_area:
                CityPicker.from(AddNewAddressActivity.this)
                        .enableAnimation(enable)
                        .setAnimationStyle(anim)
                        .setLocatedCity(null)
                        .setHotCities(hotCities)
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                tvAddNewAddressArea.setText(String.format("%s，%s", data.getProvince(), data.getName()));
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLocate() {
                                //开始定位，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        CityPicker.from(AddNewAddressActivity.this).locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
                                    }
                                }, 3000);
                            }
                        })
                        .show();

                break;
            case R.id.et_add_new_address_street:
                break;
        }
    }
}
