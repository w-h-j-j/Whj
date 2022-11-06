package com.huijian.tui;

/**创建单例模式*/
public class RetrofitClient {

    private static RetrofitClient instance;

    private RetrofitClient(){

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


}
