package com.mingrisoft.whj;

import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import com.mingrisoft.whj.db.room.Student;
import com.mingrisoft.whj.db.room.StudentViewModel;
import com.mingrisoft.whj.model.User;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.List;


public class MainActivity8 extends RxAppCompatActivity {

    private String TAG="MainActivity8测试";
    
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        init();
    }

    private void init() {
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
        readRawDatabase();
    }

    //读取db数据库文件里的数据
    @SuppressLint({"Range", "WrongConstant"})
    private void readRawDatabase() {
        if (AndPermission.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            List<User> list=new ArrayList<>();
            String f= Environment.getExternalStorageDirectory().getPath()+"/C800Pro截图/World.db3";
            SQLiteDatabase database=SQLiteDatabase.openDatabase(f,null,SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING);
            Cursor cursor = database.query("City",null,null, null,null,null,null); //表名-为当前数据表名
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                int count=cursor.getCount();
                for (int a=0;a<count;a++){
                    cursor.moveToPosition(a);
                    User user=new User();
                    String name = cursor.getString(cursor.getColumnIndex("Name"));
                    user.setUsername(name);
                    String psw = cursor.getString(cursor.getColumnIndex("CountryCode"));
                    user.setPassword(psw);
                    list.add(user);
                    System.out.println(name+psw);
                }
            }
        }else {
            AndPermission.with(this)
                    .runtime()
                    .permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .onDenied(data -> { })
                    .onGranted(data -> { })
                    .start();
        }
    }

    //测试Room数据库
    private void testRoom() {
        StudentViewModel model = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(StudentViewModel.class);
        model.queryAllData().observe(this, students -> {
            for (Student s:students) {
                System.out.println(s.toString());
            }
        });
    }

}