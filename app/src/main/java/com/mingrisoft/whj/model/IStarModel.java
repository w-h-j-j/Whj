package com.mingrisoft.whj.model;

import java.util.List;

public interface IStarModel {
    void getUserList(ICallback callback);
    interface ICallback{
        void onSuccess(List<User> userList);
        void onError(String errorMsg);
    }
}
