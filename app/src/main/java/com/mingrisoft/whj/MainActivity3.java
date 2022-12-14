package com.mingrisoft.whj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.GsonBuilder;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;

import com.mingrisoft.whj.jectpck.NameViewModel;
import com.mingrisoft.whj.model.User;
import com.mingrisoft.whj.presenter.LoginPresenter;
import com.mingrisoft.whj.presenter.StarPresent;
import com.mingrisoft.whj.retrofits.IRetrofitWeatherService;
import com.mingrisoft.whj.retrofits.bean.CityWeatherBean;
import com.mingrisoft.whj.util.MyTool;
import com.mingrisoft.whj.util.PermissionPageManagement;
import com.mingrisoft.whj.view.IStarView;
import com.mingrisoft.whj.view.ILoginView;
import com.orhanobut.logger.Logger;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.rosuh.filepicker.config.FilePickerManager;
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

@Route(path = "/app/MainActivity3")
public class MainActivity3 extends AppCompatActivity {

    @Autowired()
    String kkk;   //kkk????????????????????????????????????????????????
    private final String TAG="????????????MainActivity3";
    private final String PATH="https://img2.baidu.com/it/u=3395582942,4228440123&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EventBus.getDefault().register(this);
        ARouter.getInstance().inject(this);
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).init();
        testJectpckForLiveData();

        //??????
        findViewById(R.id.btn_rxjava).setOnClickListener((view -> {

        }));

        //??????
        findViewById(R.id.btn_rxjava).setOnLongClickListener((v -> {

            return true;
        }));
    }

    //???????????????????????????????????????
    @SuppressLint("ObsoleteSdkInt")
    private void testFilePicker() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (AndPermission.hasPermissions(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                //????????????
                FilePickerManager
                        .from(this)
                        .setCustomRootPath(Environment.getExternalStorageDirectory().getPath()+"/??????????????????")
                        .forResult(FilePickerManager.REQUEST_CODE);
            }else {
                //????????????
                AndPermission.with(this)
                        .runtime()
                        .permission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE})
                        .onGranted(data -> { System.out.println("??????"+data.toString()+"????????????!"); })
                        .onDenied(data -> { System.out.println("??????"+data.toString()+"????????????!");PermissionPageManagement.goToSetting(this); })
                        .start();
            }
        }else {
            //??????????????????
            FilePickerManager
                    .from(this)
                    .setCustomRootPath(Environment.getExternalStorageDirectory().getPath()+"/??????????????????")
                    .forResult(FilePickerManager.REQUEST_CODE);
        }
    }

    //????????????
    private Button addButton(String name){
        Button button = new Button(this);
        button.setText(name);
        return button;
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
        System.out.println("??????");
        System.out.println("??????");
    }

    private void testImageSelector() {

    }

    /**??????XUI?????????????????????*/
    private void testXUI() {
        List<String> sexList=new ArrayList<>();
        sexList.add("???");
        sexList.add("???");

        //XUI????????????
        /*new MaterialDialog.Builder(MainActivity3.this)
                .content("xxxxx")
                .positiveText("???")
                .onPositive((a,b)->{ToastUtils.show("????????????");})
                .negativeText("???")
                .onNegative((a,b)->{ToastUtils.show("????????????");})
                .show();*/

        //XUI??????????????????
        /*OptionsPickerView optionsPickerView=new OptionsPickerBuilder(this, (view, options1, options2, options3) -> {
            Logger.d(sexList.get(options1));
            return false;
        })
                .setTitleText("????????????")        //????????????
                .build();
        optionsPickerView.setPicker(sexList);  //????????????????????????
        optionsPickerView.show();*/

        //XUI??????????????????
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        TimePickerView timePickerView=new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelected(Date date, View v) {
                ToastUtils.show(format.format(date));
            }
        })
                .setTimeSelectChangeListener(date -> System.out.println("???????????????"+format.format(date)))
                .setTitleText("????????????")
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

    /**??????RxJava???????????????????????????????????????*/
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
                                Log.i(TAG, "apply: ?????????????????????"+e);
                            }
                            return null;
                        }
                    })

                    //????????????   ???????????????????????????  ???????????????????????????
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(Bitmap bitmap) throws Exception {
                            Log.i(TAG, "apply: ???????????????"+System.currentTimeMillis());
                            return bitmap;
                        }
                    })

                    //??????????????????

                    //??????????????????
                    .subscribeOn(Schedulers.io())

                    //??????Android?????????
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Observer<Bitmap>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            progressDialog=new ProgressDialog(MainActivity3.this);
                            progressDialog.setMessage("???????????????...");
                            progressDialog.show();
                        }

                        @Override
                        public void onNext(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onError(Throwable e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity3.this, "??????????????????"+e, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            if (progressDialog!=null){progressDialog.dismiss();}
                        }
                    });
        });
    }

    /**??????mvp?????????????????????????????????*/
    private void testMVP_A(){
        LoginPresenter presenter=new LoginPresenter(new ILoginView() {
            @Override
            public void showProgress() {
                Toast.makeText(MainActivity3.this, "A", Toast.LENGTH_SHORT).show();
                progressDialog=new ProgressDialog(MainActivity3.this);
                progressDialog.setMessage("???????????????...");
                progressDialog.show();
            }

            @Override
            public void hindProgress() {
                if (progressDialog!=null){progressDialog.dismiss();}
            }

            @Override
            public void loginSuccess(User user) {
                Toast.makeText(MainActivity3.this, "????????????"+user.getUsername()+"  "+user.getPassword(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void loginError(String msg) {
                Toast.makeText(MainActivity3.this, "????????????"+msg, Toast.LENGTH_SHORT).show();
            }
        });
        presenter.login(new User("111","222"));
    }

    /**??????mvp??????????????????????????????????????????????????????*/
    private void testMVP_B(){
        StarPresent present=new StarPresent(new IStarView() {
            @Override
            public void onSuccess(List<User> userList) {
                for (User user:userList) {
                    Log.i(TAG, "onSuccess: ??????????????? "+user.getUsername()+" "+user.getPassword());
                }
            }

            @Override
            public void onError(String error) {
                Log.i(TAG, "onError: "+error);
            }
        });
        present.getStarList();
    }

    /**???????????????Get??????,????????????????????????*/
    private void testOkHttp_Get(String url){
        //??????   ?????????????????????????????????????????????
        new Thread(() -> {
            OkHttpClient okHttpClient=new OkHttpClient();
            Request request=new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = okHttpClient.newCall(request).execute();
                String result=response.body().string();
                Log.i(TAG, "testOkHttp_Get: ??????"+result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //??????   ????????????????????????????????????????????????
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: ??????????????????", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String result=response.body().string();
                    Log.i(TAG, "onResponse: ??????"+result);
                    //????????????????????????UI?????????????????????
                }
            }
        });
    }

    /**???????????????Post??????,?????????????????????????????????????????????????????????
     *  ???????????????????????????API??????
     *  ?????????
     *  url="http://www.kuaidi100.com/china"
     *  ???FormBody??????????????????  .add("china","????????????")
     *  "?????????????????????????????????china"
     *  ??????????????????????????????"http://www.kuaidi100.com/????????????"
     * */

    private void testOkHttp_Post(String url){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("china","????????????")
                .build();
        Request request=new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: ??????????????????", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String result=response.body().string();
                    Log.i(TAG, "onResponse: "+result);
                    //????????????????????????UI?????????????????????
                }
            }
        });
    }

    /**????????????OkHttp????????????????????????*/
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

    /**??????Retrofit+RxJava??????????????????   http://tenapi.cn/wether/?city=??????*/
    private void testRetrofit(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://tenapi.cn/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))    //??????????????? ??????????????????????????????????????????retrofit???????????????????????????????????????????????????????????????????????????????????????????????????
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //?????? ????????????????????? ??? ?????????????????? ??????????????????
                .build();
        IRetrofitWeatherService service=retrofit.create(IRetrofitWeatherService.class);
        retrofit2.Call<CityWeatherBean> call = service.getWeatherInfo("??????");
        call.enqueue(new retrofit2.Callback<CityWeatherBean>() {
            @Override
            public void onResponse(retrofit2.Call<CityWeatherBean> call, retrofit2.Response<CityWeatherBean> response) {
                if (response.isSuccessful() && response.code()==200){
                    if (response.body().getCode().equals("201")){
                        Logger.t(TAG).d(response.body().getMsg());
                        ToastUtils.show(response.body().getMsg());
                    }else {
                        Logger.t(TAG).d("onResponse: ??????");
                        ToastUtils.show("????????????????????????"+response.body().getData().getYesterday().getHigh());
                    }
                }else {
                    Logger.t(TAG).d("onResponse: ??????");
                    ToastUtils.show("??????????????????,??????????????????????????????");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CityWeatherBean> call, Throwable t) {
                Log.e(TAG, "onResponse: ?????????????????????????????????????????????????????????????????????,??????????????????"+t.getMessage());
                ToastUtils.show("?????????????????????????????????????????????????????????????????????,??????????????????");
            }
        });
        /*Observable<CityWeatherBean> observable = service.getWeatherInfo("??????");
        observable.subscribeOn(Schedulers.io())   //?????????????????????????????????
                    .observeOn(AndroidSchedulers.mainThread())    //????????????????????????????????????UI
                    .subscribe(new Observer<CityWeatherBean>() {  //??????
                        @Override
                        public void onSubscribe(Disposable d) {
                            progressDialog=new ProgressDialog(MainActivity3.this);
                            progressDialog.setMessage("?????????...");
                            progressDialog.show();
                        }

                        @Override
                        public void onNext(CityWeatherBean cityWeatherBean) {
                            if (progressDialog!=null){progressDialog.dismiss();}
                            System.out.println(TAG+cityWeatherBean.getData().getYesterday().getHigh());
                            new SweetAlertDialog(MainActivity3.this)
                                    .setContentText("????????????????????????"+cityWeatherBean.getData().getYesterday().getHigh())
                                    .setConfirmText("??????")
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
                            System.out.println(TAG+"??????");
                        }

                        @Override
                        public void onComplete() {
                            if (progressDialog!=null){progressDialog.dismiss();}
                            System.out.println(TAG+"??????");
                            ToastUtils.show("????????????");
                        }
                    });*/
    }

    private void testLogger(){

        String jsonData="{\n" +
                "                \"date\":\"9????????????\",\n" +
                "                \"high\":\"?????? 24???\",\n" +
                "                \"fengli\":\"<![CDATA[2???]]>\",\n" +
                "                \"low\":\"?????? 21???\",\n" +
                "                \"fengxiang\":\"?????????\",\n" +
                "                \"type\":\"??????\"\n" +
                "            }";
        Map<String,String> map=new HashMap<>();
        map.put("??????","??????");
        map.put("??????","???");
        map.put("??????","45");
        map.put("??????","??????");
        Logger.d(map);
        Logger.json(jsonData);
    }

    private void testOkHttpForInterceptor(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();
        Request request = new Request.Builder().url("http://tenapi.cn/wether/?city=??????").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.t(TAG).d("????????????");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    Logger.t(TAG).d("????????????");
                }else {
                    Logger.t(TAG).d("???????????????????????????");
                }
                Logger.t(TAG).d(response.body().string());
            }
        });
    }

    private void testNotification(){

    }

    //????????????API??????
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
                System.out.println("????????????????????????");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response){
                if (response.isSuccessful()){
                    System.out.println("????????????????????????");
                    try {

                        String value=response.body().string();
                        textView.post(() -> textView.setText(value));
                        Logger.json(value);
                        Logger.t("????????????MainActivity3").d(value);
                    } catch (IOException e) {
                        Logger.d(e);
                        Logger.t("????????????MainActivity3").d(e);
                    }
                }else {
                    System.out.println("????????????????????????");
                    try {
                        String value=response.body().string();
                        textView.post(() -> textView.setText(value));
                        Logger.t("????????????MainActivity3").json(value);
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(User user){
        ToastUtils.show(user.getPassword());
    }

    @SuppressLint("CheckResult")
    private void testRxJavaOperate(){
        Observable.create(new ObservableOnSubscribe<User>() {
            @SuppressLint("CheckResult")
            @Override
            public void subscribe(ObservableEmitter<User> e) throws Exception {
                e.onNext(new User("??????","???26???"));
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
        //??????????????????
        if (resultCode==Activity.RESULT_OK && data!=null){
            if (requestCode==1){
                Uri uri=data.getData();
                String[] filePathColumns={MediaStore.Images.Media.DATA};
                Cursor cursor=getContentResolver().query(uri,filePathColumns,null,null,null);
                cursor.moveToFirst();
                int columnIndex=cursor.getColumnIndex(filePathColumns[0]);
                String imagePath=cursor.getString(columnIndex);
                cursor.close();
            }else if (requestCode==FilePickerManager.REQUEST_CODE){
                List<String> list = FilePickerManager.obtainData();
                for (String file:list) {
                    Logger.d(file);
                }
            }
//            if (requestCode==ScanCodeConfig.QUESTCODE){
//                try {
//                    Bundle extras = data.getExtras();
//                    if(extras != null){
//                        String code = extras.getString(ScanCodeConfig.CODE_KEY);
//                        ToastUtils.show(code);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Logger.d(e.getMessage());
//                }
//            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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