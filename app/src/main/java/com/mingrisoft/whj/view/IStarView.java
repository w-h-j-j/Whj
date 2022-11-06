package com.mingrisoft.whj.view;

import com.mingrisoft.whj.model.User;

import java.util.List;

public interface IStarView {
    void onSuccess(List<User> userList);
    void onError(String error);
}
