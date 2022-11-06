package com.mingrisoft.whj.model;

public interface ILoginModel {
    void login(User user,CallBack callBack);

    interface CallBack{
        void onLoginSuccess(User user);
        void onLoginError(String msg);
    }
}
