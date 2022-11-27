package com.mingrisoft.whj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mingrisoft.whj.appintro.AppIntroActivity;
import com.mingrisoft.whj.broadcast.BatteryListener;
import com.mingrisoft.whj.broadcast.IBatteryStateListener;
import com.mingrisoft.whj.db.mmkv.MMKVUtil;
import com.mingrisoft.whj.model.User;
import com.mingrisoft.whj.service.OneService;
import com.mingrisoft.whj.util.LanguageUtil;
import com.mingrisoft.whj.util.PermissionPageManagement;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.yanzhenjie.permission.AndPermission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.rosuh.filepicker.config.FilePickerManager;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private BatteryListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstInto = MMKVUtil.getInstance().getDecodeBool("isFirstInto");
        if (!isFirstInto){
            Intent intent = new Intent(this, AppIntroActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        //启动后台服务
        startMyService();

        init();
    }

    private void startMyService() {
//        ServiceConnection connection=new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                OneService.MyBinder binder = (OneService.MyBinder) service;
//                binder.print();
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        };
//        bindService(new Intent(this,MainActivity.class),connection,BIND_AUTO_CREATE);
        startService(new Intent(this,MainActivity.class));
    }

    private void init() {
        EventBus.getDefault().postSticky(new User("中国","9833"));
        findViewById(R.id.b1).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity1.class));});
        findViewById(R.id.b2).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity2.class));});
        findViewById(R.id.b3).setOnClickListener((view)->{ ARouter.getInstance().build("/app/MainActivity3").withString("kkk","花有重开日,人无在少年").navigation(); });
        findViewById(R.id.b4).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity4.class)); });
        findViewById(R.id.b5).setOnClickListener((view)->{
            //changeLanguage(Locale.ENGLISH);
            ARouter.getInstance().build("/app/MainActivity5").navigation();
        });
        findViewById(R.id.b6).setOnClickListener((view)->{
            //ARouter.getInstance().build("/app/MainActivity6").navigation();
            changeLanguage(Locale.CHINA);
        });
        findViewById(R.id.b7).setOnClickListener((view)->{ startActivity(new Intent(this,MainActivity7.class)); });
        findViewById(R.id.b8).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity8.class));});
        findViewById(R.id.b9).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity9.class));});
    }

    private void changeLanguage(Locale locale) {
        MMKVUtil.getInstance().setEncode("LANGUAGE",locale.getLanguage());
        updateLanguage(locale);
    }

    private void testImageSelector() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (AndPermission.hasPermissions(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                //执行业务
                Matisse.from(MainActivity.this)
                        .choose(MimeType.ofAll())
                        .countable(true)
                        .maxSelectable(9)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .showPreview(false) // Default is `true`
                        .forResult(123);
            }else {
                //申请权限
                AndPermission.with(this)
                        .runtime()
                        .permission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})
                        .onGranted(data -> { System.out.println("权限"+data.toString()+"获取成功!"); })
                        .onDenied(data -> { System.out.println("权限"+data.toString()+"获取失败!");
                            PermissionPageManagement.goToSetting(this); })
                        .start();
            }
        }else {
            //直接执行业务
            Matisse.from(MainActivity.this)
                    .choose(MimeType.ofAll())
                    .countable(true)
                    .maxSelectable(9)
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine())
                    .showPreview(false) // Default is `true`
                    .forResult(123);
        }
        requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},12);
    }

    private void testLottieAnim() {
        View v=View.inflate(this,R.layout.view_lottie,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setPositiveButton("确认", (dialogInterface, i) -> {
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void testMyBroadcastReceiver() {
        BatteryListener listener=new BatteryListener(this);
        listener.register(new IBatteryStateListener() {
            @Override
            public void onStateChanged() {
                Toast.makeText(MainActivity.this, "电量发生改变", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStateLow() {
                Toast.makeText(MainActivity.this, "电量低", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStateOkay() {
                Toast.makeText(MainActivity.this, "电量充满", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatePowerConnected() {
                Toast.makeText(MainActivity.this, "接入电源", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatePowerDisconnected() {
                Toast.makeText(MainActivity.this, "断开电源", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStateHeadsetPlug() {
                Toast.makeText(MainActivity.this, "插入耳机", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStateHeadsetUnPlug() {
                Toast.makeText(MainActivity.this, "拔出耳机", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void testOkHttp(){
        //缓存
        Cache cache=new Cache(new File(Environment.getExternalStorageDirectory().getPath() + "/A2L文件"),100 * 1024 * 1024);

        OkHttpClient client=new OkHttpClient.Builder().cache(cache).build();
        Request request=new Request.Builder().url("http://tenapi.cn/wether/?city=武汉").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                System.out.println("请求成功");
            }
        });
    }

    private void testRxJava(){
        Observable.just("ddd")
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        OkHttpClient client=new OkHttpClient();
                        Request request=new Request.Builder().url(s).build();
                        Response response = client.newCall(request).execute();
                        InputStream inputStream = response.body().byteStream();
                        return BitmapFactory.decodeStream(inputStream);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void updateLanguage(Locale newLanguage){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.N){
            LanguageUtil.changeAppLanguage(this,newLanguage);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == RESULT_OK) {
            //图片路径 同样视频地址也是这个
            List<String> pathList = Matisse.obtainPathResult(data);
            //Uri 格式的
            List<Uri> pathList1 = Matisse.obtainResult(data);
//            for (String path:pathList) {
//                Logger.t("MM------>pathList").d(path);
//
//            }

            for (Uri u:pathList1) {
                Logger.t("MM------>Uri").d(u.getPath());
//                ImageView imageView=findViewById(R.id.imd_mian);
//                imageView.setImageURI(u);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listener!=null){listener.unRegister();}

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void attachBaseContext(Context newBase) {
        String language = MMKVUtil.getInstance().getDecodeString("LANGUAGE");
        super.attachBaseContext(LanguageUtil.createResources(newBase,language));
    }
}