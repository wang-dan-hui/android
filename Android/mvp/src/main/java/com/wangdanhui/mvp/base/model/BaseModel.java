package com.wangdanhui.mvp.base.model;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//M  数据 本地 网络 算法 数据结构
public class BaseModel {

//    数据请求
//    protected ApiServer mApiServer;
    protected Retrofit mRetrofit;
    private String mBaseUrl;

    //额外 链接超时的情况 或者 添加拦截器 okHttp客户端
    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build();

    public BaseModel() {
        //Retrofit客户端
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
//                 //把拿到的字符串转换成可以使用的对象
                .addConverterFactory(GsonConverterFactory.create())
                //能够结合RXJava在线程之间切换 Rxjava-1
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //加入接口
//        mApiServer = mRetrofit.create(ApiServer.class);
    }

    public void setBaseUrl(String baseUrl){

        baseUrl=mBaseUrl;

    }
}
