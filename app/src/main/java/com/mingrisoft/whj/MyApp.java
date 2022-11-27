package com.mingrisoft.whj;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import com.orm.SugarContext;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.tencent.mmkv.MMKV;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.xuexiang.xui.XUI;

import java.util.HashMap;

public class MyApp extends Application {

    private final String TAG="Application里的初始化";

    @Override
    public void onCreate() {
        setTheme(R.style.ThemeStart);
        super.onCreate();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        XUI.init(this);          //初始化XUI
        XUI.debug(true);         //初始化调试日志
        ToastUtils.init(this);

        Logger.addLogAdapter(new AndroidLogAdapter());

        QbSdk.PreInitCallback preInitCallback=new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                System.out.println("腾讯X5 WebView初始化状态："+b);     //ture:加载成功   false:加载失败
            }
        };
        QbSdk.initX5Environment(this,preInitCallback);
        // 在调用TBS初始化、创建WebView之前进行如下配置
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);

        //初始化MMKV
        //String rootDir = MMKV.initialize(this);
        //System.out.println(TAG+"  mmkv root："+rootDir);
        MMKV.initialize(this);

        //初始化Sugar
        SugarContext.init(this);

        //初始化ARouter
        if (true) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        //初始化DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        //设置日志显示
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
        ARouter.getInstance().destroy();
    }
}
