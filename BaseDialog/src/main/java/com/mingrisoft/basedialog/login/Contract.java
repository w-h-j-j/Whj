package com.mingrisoft.basedialog.login;

public class Contract {

    interface ILogin{
        void onLogin(String username,String password,ICallback callback);
    }

    public interface ICallback{
        void onSuccess();
        void onFailure(String message);
    }
}
