package com.mingrisoft.whj.view;

import com.mingrisoft.whj.model.User;

public interface ILoginView {
    void showProgress();
    void hindProgress();
    void loginSuccess(User user);  //通知登陆的结果去进行UI更行
    void loginError(String msg);
}
