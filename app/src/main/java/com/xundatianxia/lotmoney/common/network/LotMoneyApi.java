package com.xundatianxia.lotmoney.common.network;


import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by ${zhoutao} on 2019/6/24 .
 */

public interface LotMoneyApi {

    String GET_ARTICLE_LIST = "api/news/feed/v62/?refer=1&count=20&loc_mode=4&device_id=34960436458&iid=13136511752";
//    @GET("/api/Extra/GetJmtime")
//    Observable<InviteTimeBean> getInviteTime();
//
//
//    /**
//     * 微信登录
//     */
//    @FormUrlEncoded
//    @POST("/api/Login/Wechat")
//    Observable<LoginBean> Login(@Field("wx_code") String wx_code,
//                                @Field("invite_code") String invite_code,
//                                @Field("sys_type") String sys_type,
//                                @Field("sys_imei") String sys_imei,
//                                @Field("wx_type") String wx_type);

    /**
     * 获取新闻列表
     *
     * @param category 频道
     * @return
     */
    @GET(GET_ARTICLE_LIST)
    Observable<String> getNewsList(@Query("category") String category,
                                   @Query("min_behot_time") long lastTime,
                                   @Query("last_refresh_sub_entrance_interval") long currentTime);

    /**
     * 获取新闻详情
     */
    @GET
    Observable<String> getNewsDetail(@Url String url);
//    /**
//     * 上传图片
//     */
//    @Multipart
//    @POST("api/upload/file")
//    Observable<BaseBean> uploadImage(@Part List<MultipartBody.Part> partLis);




}
