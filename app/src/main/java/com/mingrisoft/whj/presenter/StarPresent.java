package com.mingrisoft.whj.presenter;

import com.mingrisoft.whj.model.IStarModel;
import com.mingrisoft.whj.model.StarModel;
import com.mingrisoft.whj.model.User;
import com.mingrisoft.whj.view.IStarView;

import java.util.List;

public class StarPresent {
    private StarModel starModel;
    private IStarView iStarView;

    public StarPresent(IStarView iStarView){
        starModel=new StarModel();
        this.iStarView=iStarView;
    }

    public void getStarList(){
        starModel.getUserList(new IStarModel.ICallback() {
            @Override
            public void onSuccess(List<User> userList) {
                iStarView.onSuccess(userList);
            }

            @Override
            public void onError(String errorMsg) {
                iStarView.onError(errorMsg);
            }
        });
    }
}
