package com.mingrisoft.whj.presenter;


import com.mingrisoft.whj.model.User;

/*
* 这个接口要能接收到V层的动作,也就是用户的操作
* 之后需要去写一个实现类,此类需要实现这里的接口IPresenter
*/
public interface IPresenter {
    void login(User user);
}
