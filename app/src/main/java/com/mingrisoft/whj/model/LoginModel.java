package com.mingrisoft.whj.model;

import android.os.Handler;
import android.os.Looper;

public class LoginModel implements ILoginModel {
    @Override
    public void login(User user, CallBack callBack) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user.getUsername().equals("111") && user.getPassword().equals("222")){
                    callBack.onLoginSuccess(user);
                }else {
                    callBack.onLoginError("你输入的信息有误");
                }
            }
        },2000);
    }
}
