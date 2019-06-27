package com.xundatianxia.lotmoney.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.xundatianxia.lotmoney.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        GlideUtils.getInstance().loadUserImage(context, String.valueOf(path), imageView, R.drawable.ic_launcher_background);
    }

}
