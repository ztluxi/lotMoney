package com.xundatianxia.lotmoney.common.persenter;


import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xundatianxia.lotmoney.common.bean.BaseBean;
import com.xundatianxia.lotmoney.common.network.MyBaseSubscriber;
import com.xundatianxia.lotmoney.common.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {
  private Context mContext;

  public HomePresenter(Context context, HomeView homeView) {
    super(homeView);
    this.mContext = context;
  }
  public void getMineList() {
    addSubscription(mApiService.getNewsDetail("http://m.toutiao.com/i6705887875747021315/info/"), new MyBaseSubscriber<BaseBean>(mContext) {
      @Override
      protected void onSuccess(BaseBean baseBean) {
        Logger.d("Tamic", baseBean.getData());
        mView.success(baseBean.getData().toString());
      }

      @Override
      protected void onFailed(Throwable e, String errorMsg) {
        Logger.d("Tamic", errorMsg);
      }
    });
  }

}
