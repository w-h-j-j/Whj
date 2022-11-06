package com.huijian.tui.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.huijian.tui.util.LanguageUtil;
import com.huijian.tui.util.MMKVUtil;
import com.lky.toucheffectsmodule.factory.TouchEffectsFactory;
import com.xuexiang.xui.XUI;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(com.xuexiang.xui.R.style.XUITheme_Phone);
        TouchEffectsFactory.initTouchEffects(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        XUI.init(getApplication());
    }

    //加载上次切换的语言
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void attachBaseContext(Context newBase) {
        String language = MMKVUtil.getInstance().getDecodeString("Language");
        super.attachBaseContext(LanguageUtil.createResources(newBase,language));
    }

    //App字体不受系统设置字体大小的影响
    @Override
    public Resources getResources() {
        Resources resources=super.getResources();
        Configuration configuration=new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return resources;
    }
}
