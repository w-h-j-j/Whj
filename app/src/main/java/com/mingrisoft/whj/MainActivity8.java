package com.mingrisoft.whj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lky.toucheffectsmodule.TouchEffectsManager;
import com.lky.toucheffectsmodule.factory.TouchEffectsFactory;
import com.lky.toucheffectsmodule.types.TouchEffectsViewType;
import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

public class MainActivity8 extends AppCompatActivity{

    private String TAG="MainActivity8测试";
    
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        TitleBar titleBar= findViewById(R.id.title_bar);
        ImmersionBar.with(this).titleBar(titleBar).statusBarColor(R.color.white).statusBarDarkFont(true).init();
        titleBar.setTitle("这是主页");
        titleBar.setBackgroundColor(Color.parseColor("#ffffff"));
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                Log.i(TAG, "onLeftClick: ");
                finish();
            }

            @Override
            public void onTitleClick(TitleBar titleBar) {
                Log.i(TAG, "onTitleClick: ");
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                Log.i(TAG, "onRightClick: ");
            }
        });


        findViewById(R.id.btn_scale).setOnClickListener(view -> {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.Group.STORAGE)
                    .onGranted(a->{
                        Log.i(TAG, "以获取到权限");
                    })
                    .onDenied(a->{
                        Log.e(TAG, "未获取到权限 ");
                    })
                    .start();
        });
    }

}