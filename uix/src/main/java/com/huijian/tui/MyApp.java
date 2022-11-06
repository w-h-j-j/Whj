package com.huijian.tui;

import android.app.Application;

import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;
import com.tencent.mmkv.MMKV;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TouchEffectsManager.build(TouchEffectsWholeType.SCALE)      //设置全局使用哪种效果
                .addViewType(TouchEffectsViewType.ALL)              //添加哪些View支持这个效果
                .setListWholeType(TouchEffectsWholeType.RIPPLE);    //为父控件为列表的情况下，设置特定效果

        MMKV.initialize(this);

    }
}
