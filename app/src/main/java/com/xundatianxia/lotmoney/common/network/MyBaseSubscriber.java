package com.xundatianxia.lotmoney.common.network;

/**
 * Created by ${zhoutao} on 2018/5/28 0028.
 */

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.NetworkUtils;
import com.orhanobut.logger.Logger;
import com.xundatianxia.lotmoney.common.bean.BaseBean;
import com.xundatianxia.lotmoney.common.bean.NotifyBean;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;

/**
 * Created by ${zhoutao} on 2018/5/28 .
 */
public abstract class MyBaseSubscriber<T> extends Subscriber<T> {
  private Context context;

  public MyBaseSubscriber(Context context) {
    this.context = context;

  }
  @Override
  public void onStart() {
    super.onStart();
    if (!NetworkUtils.isConnected()) {
      ToastUtils.show(context.getApplicationContext(),"请检查网络连接！");
    }else {
      onCompleted();
    }
  }

  @Override
  public void onError(Throwable e) {//服务器错误信息处理
    onFailed(e, RxExceptionUtil.exceptionHandler(e));
  }
  @Override
  public void onCompleted() {
  }


  @Override
  public void onNext(T response) {
    BaseBean baseBean = null;
    String object = JSON.toJSONString(response);
    try {
      baseBean = JSON.parseObject(object, BaseBean.class);
      onSuccess(response);
      if (baseBean.getCode() == 202) {
        //登录异常,删除账户
        NotifyBean bean = new NotifyBean();
        bean.setType(NotifyBean.TYPE.TYPE_LOGOUT);
        EventBus.getDefault().post(bean);
//                LitePal.deleteAll(LoginInfoBean.class);
        //跳转到登录页面
        ToastUtils.show(context, baseBean.getMsg());
//                Intent intent = new Intent(context, LoginActivity.class);
//                context.startActivity(intent);
      }
    } catch (Exception e) {
      onSuccess(response);
      e.printStackTrace();
    }
  }

  protected abstract void onSuccess(T t);

  protected abstract void onFailed(Throwable e,String errorMsg);


  public static class ToastUtils {
    static Toast toast = null;
    public static void show(Context context, String text) {
      try {
        if(toast!=null){
          toast.setText(text);
        }else{
          toast= Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.show();
      } catch (Exception e) {
        //解决在子线程中调用Toast的异常情况处理
        Looper.prepare();
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        Looper.loop();
      }
    }
  }
}