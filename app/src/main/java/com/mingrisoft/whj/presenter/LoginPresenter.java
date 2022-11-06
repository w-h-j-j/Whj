package com.mingrisoft.whj.presenter;

import com.mingrisoft.whj.model.ILoginModel;
import com.mingrisoft.whj.model.LoginModel;
import com.mingrisoft.whj.model.User;
import com.mingrisoft.whj.view.ILoginView;

/*
*  P层
*   V层需要持有P层
*   P层拿到用户操作之后，要去操作M层，所以P层需要持有M层
*   M层处理的结果要通知给P层，然后P层拿到了结果之后，就要去更新V层
* */
public class LoginPresenter implements IPresenter {


    private LoginModel loginModel;      //P层需要持有M层
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView mainView){             //在创建这里类的时候就能实例化M层
        loginModel=new LoginModel();
        this.iLoginView=mainView;
    }

    @Override
    public void login(User user) {
        iLoginView.showProgress();    //只要此方法一被调用，就显示对话框
        loginModel.login(user, new ILoginModel.CallBack() {
            @Override
            public void onLoginSuccess(User user) {
                iLoginView.loginSuccess(user);
                iLoginView.hindProgress();
            }

            @Override
            public void onLoginError(String msg) {
                iLoginView.loginError(msg);
                iLoginView.hindProgress();
            }
        });
    }

   /*public void login(User user){
        loginModel.login(user, new ILoginModel.CallBack() {
            @Override
            public void onLoginSuccess(User user) {
                iLoginView.loginSuccess(user);
            }

            @Override
            public void onLoginError(String msg) {
                iLoginView.loginError(msg);
            }
        });
   }*/

}
