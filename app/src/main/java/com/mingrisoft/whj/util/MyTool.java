package com.mingrisoft.whj.util;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyTool {
    /**旋转动画，取代了在Drawable资源文件夹里定义资源文件的操作了*/
    public static void myRotateAnim(View view,int duration){
        ValueAnimator animator=ValueAnimator.ofFloat(0,360);     //360度旋转
        animator.setDuration(duration);                              //转完一圈需小号的时间
        animator.setInterpolator(new LinearInterpolator());      //插值器 匀速旋转
        animator.setRepeatCount(-1);                              //-1表示一直旋转  1后向表示只旋转一圈
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value= (float) valueAnimator.getAnimatedValue();
                //System.out.println("动画："+value);
                //view.setRotationY(value);                        //围绕X轴旋转
                view.setRotation(value);                           //围绕平面中心旋转
            }
        });
        animator.start();
    }

    public static void getWeatherInfo(){
        String data=null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://eolink.o.apispace.com/456456/weather/v001/now?areacode=上海")
                .addHeader("X-APISpace-Token","5rymyjphkvxmfnta60yl5kzbedpakhmz")
                .addHeader("Authorization-Type","apikey")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("城市天气请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response){
                if (response.isSuccessful()){
                    System.out.println("城市天气请求成功");
                    try {
                        String a=response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("城市天气请求异常ggg"+e);
                    }
                }else {
                    System.out.println("城市天气请求异常");
                    try {
                        String a=response.body().string();
                        System.out.println("城市错误消息："+a);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
