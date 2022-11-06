package com.hiujian.xui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hiujian.xui.R;

public class MainActivity extends BaseActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        TextView textView=findViewById(R.id.tv_main);
        Button button=findViewById(R.id.btn_main);
        button.setOnClickListener((view -> {
            Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
            textView.setText("认真敲代码，你会有收获！");
        }));
    }
}