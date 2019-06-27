package com.xundatianxia.lotmoney.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.xundatianxia.lotmoney.R;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected RelativeLayout rl_base_layout;
    protected View view_status_bar;
    protected TextView tv_title_left;
    protected ImageView image_title_left;
    protected TextView tv_title_right;
    protected ImageView image_title_right;
    protected TextView text_title;
    protected ImmersionBar mImmersionBar;
    protected InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setContentView(getLayout());
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(android.R.color.transparent).init();   //所有子类都将继承这些相同的属性

        ButterKnife.bind(this);

        initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 100);
    }

    protected void hideSoftKeyboard(EditText editText) {
        if (inputMethodManager != null) {
            // 隐藏软键盘
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    protected void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * 动态设置状态栏的高度
     *
     * @param viewGroup
     */
    protected void setTitlePadding(ViewGroup viewGroup) {
        viewGroup.setPadding(0, getStatusBarHeight(), 0, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

    protected void initTitle(String titleStr) {
        rl_base_layout = findViewById(R.id.rl_base_layout);
        view_status_bar = findViewById(R.id.view_status_bar);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        view_status_bar.setLayoutParams(params);
        tv_title_left = findViewById(R.id.tv_title_left);
        image_title_left = findViewById(R.id.image_title_left);
        tv_title_right = findViewById(R.id.tv_title_right);
        image_title_right = findViewById(R.id.image_title_right);
        text_title = findViewById(R.id.text_title);
        text_title.setText(titleStr);
    }


    // 通过反射机制获取手机状态栏高度
    protected int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

}
