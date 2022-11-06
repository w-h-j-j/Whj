package com.mingrisoft.whj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;

import com.mingrisoft.whj.jectpck.NameViewModel;
import com.mingrisoft.whj.model.User;
import com.mingrisoft.whj.presenter.LoginPresenter;
import com.mingrisoft.whj.presenter.StarPresent;
import com.mingrisoft.whj.retrofits.IRetrofitWeatherService;
import com.mingrisoft.whj.retrofits.RetrofitClient;
import com.mingrisoft.whj.retrofits.bean.CityWeatherBean;
import com.mingrisoft.whj.util.MyTool;
import com.mingrisoft.whj.view.IStarView;
import com.mingrisoft.whj.view.ILoginView;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.z.fileselectorlib.FileSelectorSettings;
import com.z.fileselectorlib.Objects.BasicParams;
import com.z.fileselectorlib.Objects.FileInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity3 extends AppCompatActivity {

    private final String TAG="测试模块MainActivity3";
    private final String PATH="https://img2.baidu.com/it/u=3395582942,4228440123&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).init();
        testJectpckForLiveData();
        findViewById(R.id.btn_rxjava).setOnClickListener((view -> {
            testNotification();
        }));

    }

    private void testJectpckForLiveData() {
        NameViewModel model= ViewModelProviders.of(this).get(NameViewModel.class);
        model.getLiveData().observe(this, new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i(TAG,s);
                Button btn = findViewById(R.id.btn_rxjava);
                btn.setText(s);
            }
        });

    }

    private void print(){
        System.out.println("你好");
        System.out.println("你好");
    }

    private void testImageSelector() {

    }

    /**腾讯XUI框架的基本使用*/
    private void testXUI() {
        List<String> sexList=new ArrayList<>();
        sexList.add("男");
        sexList.add("女");

        //XUI的对话框
        /*new MaterialDialog.Builder(MainActivity3.this)
                .content("xxxxx")
                .positiveText("是")
                .onPositive((a,b)->{ToastUtils.show("点击了是");})
                .negativeText("否")
                .onNegative((a,b)->{ToastUtils.show("点击了否");})
                .show();*/

        //XUI的性别选择器
        /*OptionsPickerView optionsPickerView=new OptionsPickerBuilder(this, (view, options1, options2, options3) -> {
            Logger.d(sexList.get(options1));
            return false;
        })
                .setTitleText("性别选择")        //设置标题
                .build();
        optionsPickerView.setPicker(sexList);  //设置要选择额列表
        optionsPickerView.show();*/

        //XUI的日期选择器
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        TimePickerView timePickerView=new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                ToastUtils.show(format.format(date));
            }
        })
                .setTimeSelectChangeListener(date -> System.out.println("当前日期："+format.format(date)))
                .setTitleText("日期选择")
                .build();
        timePickerView.show();
    }

    private void testMyAnim(View v) {
        SeekBar seekBar=findViewById(R.id.seekbar);
        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MyTool.myRotateAnim(v,i*100*2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**通过RxJava观察者模式获取网页上的图片*/
    public void testRxJava(Button button,ImageView imageView){
        button.setOnClickListener((view)->{
            Observable.just(PATH)
                    .map(new Function<String, Bitmap>() {
                        @SuppressLint("CheckResult")
                        @Override
                        public Bitmap apply(String path) throws Exception {
                            try {
                                URL url=new URL(path);
                                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                                httpURLConnection.setConnectTimeout(5000);
                                int responseCode = httpURLConnection.getResponseCode();
                                if (responseCode == HttpURLConnection.HTTP_OK){
                                    InputStream inputStream= httpURLConnection.getInputStream();
                                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                                    return bitmap;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.i(TAG, "apply: 加载失败原因："+e);
                            }
                            return null;
                        }
                    })

                    //添加需求   查看日志、添加水印  在这里只做查看日志
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(Bitmap bitmap) throws Exception {
                            Log.i(TAG, "apply: 处理的时间"+System.currentTimeMillis());
                            return bitmap;
                        }
                    })

                    //继续添加需求

                    //分配一部线程
                    .subscribeOn(Schedulers.io())

                    //分配Android主线程
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Observer<Bitmap>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            progressDialog=new ProgressDialog(MainActivity3.this);
                            progressDialog.setMessage("正在加载中...");
                            progressDialog.show();
                        }

                        @Override
                        public void onNext(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity3.this, "加载图片失败"+e, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            if (progressDialog!=null){progressDialog.dismiss();}
                        }
                    });
        });
    }

    /**设计mvp架构，模拟编写登录接口*/
    private void testMVP_A(){
        LoginPresenter presenter=new LoginPresenter(new ILoginView() {
            @Override
            public void showProgress() {
                Toast.makeText(MainActivity3.this, "A", Toast.LENGTH_SHORT).show();
                progressDialog=new ProgressDialog(MainActivity3.this);
                progressDialog.setMessage("正在登录中...");
                progressDialog.show();
            }

            @Override
            public void hindProgress() {
                if (progressDialog!=null){progressDialog.dismiss();}
            }

            @Override
            public void loginSuccess(User user) {
                Toast.makeText(MainActivity3.this, "登录成功"+user.getUsername()+"  "+user.getPassword(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginError(String msg) {
                Toast.makeText(MainActivity3.this, "登录失败"+msg, Toast.LENGTH_SHORT).show();
            }
        });
        presenter.login(new User("111","222"));
    }

    /**设计mvp架构，模拟编写从服务器上获取数据接口*/
    private void testMVP_B(){
        StarPresent present=new StarPresent(new IStarView() {
            @Override
            public void onSuccess(List<User> userList) {
                for (User user:userList) {
                    Log.i(TAG, "onSuccess: 得到的明星 "+user.getUsername()+" "+user.getPassword());
                }
            }

            @Override
            public void onError(String error) {
                Log.i(TAG, "onError: "+error);
            }
        });
        present.getStarList();
    }

    /**一般而言，Get请求,获取服务器的数据*/
    private void testOkHttp_Get(String url){
        //同步   耗时操作需要创建子线程去操作它
        new Thread(() -> {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                String result=response.body().string();
                Log.i(TAG, "testOkHttp_Get: 同步"+result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //异步   耗时操作不需要创建子线程去操作它
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: 请求数据失败", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String result=response.body().string();
                    Log.i(TAG, "onResponse: 异步"+result);
                    //这里如果要去更改UI需要回到主线程
                }
            }
        });
    }

    /**一般而言，Post请求,是将提交数据给服务器，再得到相应的数据
     *  当然这里需要相应的API网站
     *  举例：
     *  url="http://www.kuaidi100.com/china"
     *  在FormBody里提交的表单  .add("china","我的世界")
     *  "我的世界会替换调网站的china"
     *  所以最终提交的网站是"http://www.kuaidi100.com/我的世界"
     * */

    private void testOkHttp_Post(String url){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("china","我的世界")
                .build();
        Request request=new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: 请求数据失败", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String result=response.body().string();
                    Log.i(TAG, "onResponse: "+result);
                    //这里如果要去更改UI需要回到主线程
                }
            }
        });
    }

    /**测试通过OkHttp上传文件到服务器*/
    private void uploadFile(String path){
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody body= MultipartBody.create(MediaType.parse("multipart/form-data"),new File(path));
        Request request=new Request.Builder()
                .url("http://www.kuaidi100.com/query?type=")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
    }

    /**通过Retrofit+RxJava获取天气信息   http://tenapi.cn/wether/?city=武汉*/
    private void testRetrofit(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://tenapi.cn/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))    //数据转换器 ：服务器返回的数据各种各样，retrofit为我们封装了各类数据的转换器，将返回数据解析成我们需要的数据类型；
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //通过 网络请求适配器 将 网络请求对象 进行平台适配
                .build();
        IRetrofitWeatherService service=retrofit.create(IRetrofitWeatherService.class);
        retrofit2.Call<CityWeatherBean> call = service.getWeatherInfo("深圳");
        call.enqueue(new retrofit2.Callback<CityWeatherBean>() {
            @Override
            public void onResponse(retrofit2.Call<CityWeatherBean> call, retrofit2.Response<CityWeatherBean> response) {
                if (response.isSuccessful() && response.code()==200){
                    if (response.body().getCode().equals("201")){
                        Logger.t(TAG).d(response.body().getMsg());
                        ToastUtils.show(response.body().getMsg());
                    }else {
                        Logger.t(TAG).d("onResponse: 成功");
                        ToastUtils.show("深圳昨天的温度："+response.body().getData().getYesterday().getHigh());
                    }
                }else {
                    Logger.t(TAG).d("onResponse: 失败");
                    ToastUtils.show("也许是个意外,我没有找到你要的数据");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CityWeatherBean> call, Throwable t) {
                Log.e(TAG, "onResponse: 请求天气数据失败：可能你的手机网络状况不是很好,请稍后重试。"+t.getMessage());
                ToastUtils.show("请求天气数据失败：可能你的手机网络状况不是很好,请稍后重试。");
            }
        });
        /*Observable<CityWeatherBean> observable = service.getWeatherInfo("深圳");
        observable.subscribeOn(Schedulers.io())   //请求数据在子线程中进行
                    .observeOn(AndroidSchedulers.mainThread())    //请求完成后在主线程中更新UI
                    .subscribe(new Observer<CityWeatherBean>() {  //订阅
                        @Override
                        public void onSubscribe(Disposable d) {
                            progressDialog=new ProgressDialog(MainActivity3.this);
                            progressDialog.setMessage("加载中...");
                            progressDialog.show();
                        }

                        @Override
                        public void onNext(CityWeatherBean cityWeatherBean) {
                            if (progressDialog!=null){progressDialog.dismiss();}
                            System.out.println(TAG+cityWeatherBean.getData().getYesterday().getHigh());
                            new SweetAlertDialog(MainActivity3.this)
                                    .setContentText("深圳昨天的温度："+cityWeatherBean.getData().getYesterday().getHigh())
                                    .setConfirmText("确认")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismiss();
                                        }
                                    })
                                    .show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(TAG+"失败");
                        }

                        @Override
                        public void onComplete() {
                            if (progressDialog!=null){progressDialog.dismiss();}
                            System.out.println(TAG+"完成");
                            ToastUtils.show("加载完成");
                        }
                    });*/
    }

    private void testLogger(){

        String jsonData="{\n" +
                "                \"date\":\"9日星期四\",\n" +
                "                \"high\":\"高温 24℃\",\n" +
                "                \"fengli\":\"<![CDATA[2级]]>\",\n" +
                "                \"low\":\"低温 21℃\",\n" +
                "                \"fengxiang\":\"东南风\",\n" +
                "                \"type\":\"中雨\"\n" +
                "            }";
        Map<String,String> map=new HashMap<>();
        map.put("名字","老王");
        map.put("性别","男");
        map.put("年龄","45");
        map.put("爱好","打球");
        Logger.d(map);
        Logger.json(jsonData);
    }

    private void testOkHttpForInterceptor(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();
        Request request = new Request.Builder().url("http://tenapi.cn/wether/?city=武汉").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.t(TAG).d("请求异常");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    Logger.t(TAG).d("请求成功");
                }else {
                    Logger.t(TAG).d("请求中途出现了意外");
                }
                Logger.t(TAG).d(response.body().string());
            }
        });
    }

    private void testNotification(){

    }

    //测试天气API接口
    public static void getWeatherInfo(TextView textView){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://eolink.o.apispace.com/456456/weather/v001/now?areacode=101010100")
                .addHeader("X-APISpace-Token","5rymyjphkvxmfnta60yl5kzbedpakhmz")
                .addHeader("Authorization-Type","apikey")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("城市天气请求失败");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response){
                if (response.isSuccessful()){
                    System.out.println("城市天气请求成功");
                    try {

                        String value=response.body().string();
                        textView.post(() -> textView.setText(value));
                        Logger.json(value);
                        Logger.t("测试模块MainActivity3").d(value);
                    } catch (IOException e) {
                        Logger.d(e);
                        Logger.t("测试模块MainActivity3").d(e);
                    }
                }else {
                    System.out.println("城市天气请求异常");
                    try {
                        String value=response.body().string();
                        textView.post(() -> textView.setText(value));
                        Logger.t("测试模块MainActivity3").json(value);
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(User user){
        ToastUtils.show(user.getUsername());
    }

    private void testRxJavaOperate(){
        Observable.create(new ObservableOnSubscribe<User>() {
            @SuppressLint("CheckResult")
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                e.onNext(new User("d","s"));
            }
        }).subscribe(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                ToastUtils.show(user.getUsername()+user.getPassword());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode==1 && resultCode==Activity.RESULT_OK && data!=null){
            Uri uri=data.getData();
            String[] filePathColumns={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(uri,filePathColumns,null,null,null);
            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filePathColumns[0]);
            String imagePath=cursor.getString(columnIndex);

            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

    class MyInterceptor implements Interceptor{

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            switch (response.code()){
                case 400:
                    Logger.t(TAG).d("Show 400:Bad Request Error Message");
                    break;
                case 401:
                    Logger.t(TAG).d("Show 401:UnauthorizedError Message");
                    break;
                case 403:
                    Logger.t(TAG).d("Show 403:Forbidden Message");
                    break;
                case 404:
                    Logger.t(TAG).d("Show 404:NotFound Message");
                    break;
                default:
                    Logger.t(TAG).d("Show Normal Message");
                    break;
            }
            return response;
        }
    }
}