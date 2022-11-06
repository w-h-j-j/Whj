package com.mingrisoft.basedialog.login;

import android.os.Handler;
import android.os.Looper;

public class LoginModel implements Contract.ILogin {
    @Override
    public void onLogin(String username, String password, Contract.ICallback callback) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (username.equals("whj")&&password.equals("123")){
                    callback.onSuccess();
                }else {
                    callback.onFailure("请检查你输入的信息  "+"账号:"+username+"   密码:"+password);
                }
            }
        },2000);
    }
}
