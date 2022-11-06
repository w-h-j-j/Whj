package com.mingrisoft.whj;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;

import io.reactivex.disposables.Disposable;


public class MainActivity1 extends AppCompatActivity {

    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);

        testRecyclerView(findViewById(R.id.recyclerView_main1));

        findViewById(R.id.btn_11).setOnClickListener((view -> {adapter.selectAll();}));
        findViewById(R.id.btn_22).setOnClickListener((view -> {adapter.cancelSelectAll();}));
        findViewById(R.id.btn_33).setOnClickListener((view -> {adapter.chooseAll();}));
        findViewById(R.id.btn_44).setOnClickListener((view -> { adapter.cancelChooseAll(); }));


    }

    /**调用的第三方库，好像有12种不同类型的对话框*/
    private void testSweetalertDialog(ListView listView) {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) { list.add("第"+i); }
        listView.setAdapter(new ArrayAdapter<String>(this, com.liys.view.R.layout.support_simple_spinner_dropdown_item,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogForPosition(i);
            }
        });
    }
    private void showDialogForPosition(int position){
        switch (position){
            case 0:
                SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(true);
                pDialog.show();
                break;
            case 1:
                /*new SweetAlertDialog(this)
                        .setTitleText("Here's a message!")
                        .show();*/
                final SweetAlertDialog pDialogs = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Loading");
                pDialogs.show();
                pDialogs.setCancelable(false);
                new CountDownTimer(800 * 7, 800) {
                    int i=0;
                    public void onTick(long millisUntilFinished) {
                        // you can change the progress bar color by ProgressHelper every 800 millis

                        i++;
                        switch (i){
                            case 0:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.blue_btn_bg_color));
                                break;
                            case 1:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.material_deep_teal_50));
                                break;
                            case 2:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.success_stroke_color));
                                break;
                            case 3:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.material_deep_teal_20));
                                break;
                            case 4:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.material_blue_grey_80));
                                break;
                            case 5:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.warning_stroke_color));
                                break;
                            case 6:
                                pDialogs.getProgressHelper().setBarColor(getResources().getColor(cn.pedant.SweetAlert.R.color.success_stroke_color));
                                break;
                        }
                    }

                    public void onFinish() {
                        i = -1;
                        pDialogs.setTitleText("Success!")
                                .setConfirmText("OK")
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }.start();
                break;
            case 2:

                new SweetAlertDialog(this)
                        .setTitleText("Here's a message!")
                        .setContentText("It's pretty, isn't it?")
                        .show();
                break;
            case 3:
                //显示异常的对话框
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .setCancelText("Cancel")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Toast.makeText(MainActivity1.this, "点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case 4:
                //显示警告
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .show();
                break;
            case 5:
                //显示成功完成
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("You clicked the button!")
                        .show();
                break;
            case 6:
                //自定义头部头像
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("Sweet!")
                        .setContentText("Here's a custom image.")
                        .setCustomImage(R.mipmap.ic_launcher)
                        .show();
                break;
            case 7:
                //确认事件绑定
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
                break;
            case 8:
                //显示取消按钮事件绑定
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setCancelText("No,cancel plx!")
                        .setConfirmText("Yes,delete it!")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
                break;
            case 9:
                //确认后切换对话框样式
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
                break;
        }
    }

    private void testFloatingActionsMenu(FloatingActionButton button) {
        button.setOnClickListener((view -> {
            Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();
        }));

    }

    private void testRecyclerView(RecyclerView recyclerView) {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add("第"+i+"名");
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager manager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);

        adapter=new MyRecyclerViewAdapter(this, list, new MyRecyclerViewAdapter.IOnBindView() {
            @Override
            public void onBindData(MyRecyclerViewAdapter.MyViewHolder holder, int position) {
                holder.textView.setText(list.get(position));
                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox checkBox=(CheckBox)v;
                        if (checkBox.isChecked()){
                            adapter.hashMap.put(position,true);
                        }else {
                            adapter.hashMap.put(position,false);
                        }
                    }
                });
                holder.checkBox.setChecked(adapter.hashMap.get(position));
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerItemClickListener(new MyRecyclerViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Toast.makeText(MainActivity1.this, "点击了"+position, Toast.LENGTH_SHORT).show();
                List<String> listBool=new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    boolean b=adapter.hashMap.get(i);
                    if (b==true){
                        listBool.add(String.valueOf(i));
                    }
                }
                System.out.println("选中的值："+listBool);

            }
        });

        //添加滚动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case 0:
                        System.out.println("停止滚动");
                        break;
                    case 1:
                        System.out.println("正在拖拽滚动");
                        break;
                    case 2:
                        System.out.println("惯性滚动");
                        break;
                }
            }
        });

        //获取可以看见的条目索引
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
                }
            });
        }

    }

    //有将近60种动画
    private void testYoYoAnim(View view) {
        try {
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(1)
                    .playOn(view);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ls" + e);
        }
    }


}