package com.mingrisoft.whj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.mingrisoft.whj.broadcast.BatteryListener;
import com.mingrisoft.whj.broadcast.IBatteryStateListener;
import com.mingrisoft.whj.util.LanguageUtil;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

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
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private BatteryListener listener;
    private MMKV mmkv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        init();
    }

    private void init() {
       // LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(200,200);

        //v.setLayoutParams(params);
        findViewById(R.id.b1).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity1.class));});
        findViewById(R.id.b2).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity2.class));});
        findViewById(R.id.b3).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity3.class));});
        findViewById(R.id.b4).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity4.class)); });
        findViewById(R.id.b5).setOnClickListener((view)->{
            //startActivity(new Intent(MainActivity.this, MainActivity5.class));
            Locale locale = Locale.ENGLISH;
            mmkv.encode("LANGUAGE",locale.getLanguage());
            updateLanguage(locale);
        });
        findViewById(R.id.b6).setOnClickListener((view)->{
            //startActivity(new Intent(MainActivity.this, MainActivity6.class));
            Locale locale = Locale.CHINA;
            mmkv.encode("LANGUAGE",locale.getLanguage());
            updateLanguage(locale);
        });
        findViewById(R.id.b7).setOnClickListener((view)->{

        });
        findViewById(R.id.b8).setOnClickListener((view)->{startActivity(new Intent(MainActivity.this, MainActivity8.class));});
        findViewById(R.id.b9).setOnClickListener((view)->{ startActivity(new Intent(MainActivity.this, MainActivity9.class));});
    }

    private void testImageSelector() {
        Matisse.from(this)
                .choose(MimeType.ofAll())
                .countable(true)
                .maxSelectable(1)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .showPreview(false)

                //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                .capture(true)
                .captureStrategy(new CaptureStrategy(true,"PhotoPicker"))
                // .theme(R.style.Matisse_Zhihu)     //设置主题
                .imageEngine(new GlideEngine())      //Glide加载方式
                .forResult(111);
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
        mmkv=MMKV.defaultMMKV();
        mmkv.encode("Apple","School");
        String language = mmkv.decodeString("LANGUAGE","w");
        super.attachBaseContext(LanguageUtil.createResources(newBase,language));
    }
}