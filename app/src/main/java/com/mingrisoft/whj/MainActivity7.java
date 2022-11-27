package com.mingrisoft.whj;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.toast.ToastUtils;
import com.xuexiang.xui.utils.SnackbarUtils;


public class MainActivity7 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        findViewById(R.id.btn7).setOnClickListener((v ->
        {
            SnackbarUtils.Indefinite(v,"China")
                    .info()
                    .backColor(Color.RED)
                    .actionColor(Color.GREEN)
                    .setAction("确定", v1 -> { ToastUtils.show("弟弟"); })
                    .show();
        }));

    }

    private void testFont(TextView textView) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/kaiti.ttf");
        textView.setTypeface(typeface);
        textView.setText("有时候因为一些项目上的需要，我们要导入自己的字体，这里总结一些自己导入时候的操作（使用开发工具是Android studio）：\n" + "向Android studio里面加入.ttf字体库，在项目的APP中创建assets文件夹，往里面放入ttf字体库，步骤如下：");
        textView.setTextSize(30);
        textView.setTextColor(Color.BLACK);
    }
}