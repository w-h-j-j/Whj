package com.mingrisoft.whj.retrofits;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final Retrofit retrofit;
    private static RetrofitClient instance;
    private final String BASE_URL="http://tenapi.cn/";

    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getInstance(){
        if (instance==null){
            synchronized (RetrofitClient.class){
                if (instance==null){
                    instance=new RetrofitClient();
                }
            }
        }
        return instance;
    }


//    public IRetrofitWeatherService getIRetrofitWeatherService(){
//        retrofit=new Retrofit.Builder()
//                .baseUrl("http://tenapi.cn/")
//                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))    //数据转换器 ：服务器返回的数据各种各样，retrofit为我们封装了各类数据的转换器，将返回数据解析成我们需要的数据类型；
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //通过 网络请求适配器 将 网络请求对象 进行平台适配
//                .build();
//        return retrofit.create(IRetrofitWeatherService.class);
//    }

    //将上面创建Retrofit的方式进行了改装一下
    public <T> T getService(Class<T> cls){
        return retrofit.create(cls);
    }

//    private synchronized Retrofit getRetrofit(){
//        if (retrofit==null){
//            retrofit=new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
}
