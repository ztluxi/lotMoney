package com.xundatianxia.lotmoney.common.persenter;


import com.xundatianxia.lotmoney.common.network.LotMoneyApi;
import com.xundatianxia.lotmoney.common.network.RetrofitService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${zhoutao} on 2019/6/24.
 */

public abstract class BasePresenter<V> {
  protected LotMoneyApi mApiService = RetrofitService.getInstance().getApiService();
  protected V mView;
  private CompositeSubscription mCompositeSubscription;

  public BasePresenter(V view) {
    attachView(view);
  }

  public void attachView(V view) {
    mView = view;
  }

  public void detachView() {
    mView = null;
    onUnSubscribe();
  }

  public void addSubscription(Observable observable, Subscriber subscriber) {
    if (mCompositeSubscription == null) {
      mCompositeSubscription = new CompositeSubscription();
    }
    mCompositeSubscription.add(observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber));
  }

  //RXjava取消注册，以避免内存泄露
  public void onUnSubscribe() {
    if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
      mCompositeSubscription.unsubscribe();
    }
  }


}