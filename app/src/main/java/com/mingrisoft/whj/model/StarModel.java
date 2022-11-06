package com.mingrisoft.whj.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarModel implements IStarModel {
    @Override
    public void getUserList(ICallback callback) {
        if(getRandom()>5){
            callback.onSuccess(getData());
        }else {
            callback.onError("产生的随机数小于或等于5，生成的随机数是："+getRandom());
        }
    }

    private List<User> getData(){
        List<User> list=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new User("明星"+i,"密码"+i*2));
        }
        return list;
    }

    private int getRandom(){
        /*Random random=new Random();
        int value=random.nextInt(10);*/
        return 10;
    }
}
