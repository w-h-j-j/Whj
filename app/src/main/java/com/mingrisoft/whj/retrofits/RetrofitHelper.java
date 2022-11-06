package com.mingrisoft.whj.retrofits;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private Context mContext;
    OkHttpClient client=new OkHttpClient();
    GsonConverterFactory gsonConverterFactory=GsonConverterFactory.create(new GsonBuilder().create());

    private static RetrofitHelper instance=null;
    private Retrofit retrofit=null;
    public static RetrofitHelper getInstance(Context context){
        if (instance==null){
            instance=new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context context){
        mContext=context;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        retrofit=new Retrofit.Builder()
                .baseUrl("")
                .client(client)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public IRetrofitWeatherService getServer(){
        return retrofit.create(IRetrofitWeatherService.class);
    }

}
