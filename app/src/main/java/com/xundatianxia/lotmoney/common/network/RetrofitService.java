package com.xundatianxia.lotmoney.common.network;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xundatianxia.lotmoney.base.Constans;
import com.xundatianxia.lotmoney.base.MyApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
/**
 * Created by ${zhoutao} on 2018/6/1
 */

public class RetrofitService {
    private static LotMoneyApi financeApi;
    private static RetrofitService mApiRetrofit;

    public RetrofitService() {
        //cache url
        File httpCacheDirectory = new File(MyApplication.getContext().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置连接超时时间
                .writeTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .readTimeout(Constans.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .addInterceptor(mLogInterceptor)//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .addInterceptor(mHeaderInterceptor)//添加头部信息拦截器
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constans.BASE_SERVER_URL)
                .build();
        financeApi = retrofit.create(LotMoneyApi.class);
    }

    /**请求访问quest和response拦截器*/
    private Interceptor mLogInterceptor = chain -> {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Logger.d("----------Request Start----------------");
        Logger.d("| " + request.toString());
        Logger.json("| Response:" + content);
        Logger.e("----------Request End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    };

    /**增加头部信息的拦截器*/
    private Interceptor mHeaderInterceptor = chain -> {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.0.0.13547");
        builder.addHeader("Cache-Control", "max-age=0");
        builder.addHeader("Upgrade-Insecure-Requests", "1");
        builder.addHeader("X-Requested-With", "XMLHttpRequest");
        return chain.proceed(builder.build());
    };
    public static RetrofitService getInstance() {
        if (financeApi == null) {
            synchronized (Object.class) {
                if (financeApi == null) {
                    mApiRetrofit = new RetrofitService();
                }
            }
        }
        return mApiRetrofit;
    }

    public LotMoneyApi getApiService() {
        return financeApi;
    }

}
