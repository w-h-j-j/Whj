package com.mingrisoft.basedialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class LottieAnimDialog extends Dialog {

    private final Context context;
    private String message;

    /**带有Lottie动画的等待加载框*/
    public LottieAnimDialog(@NonNull Context context) {
        super(context,R.style.dialog_theme);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context,R.layout.view_dialog_lottie,null);
        setContentView(view);
        TextView textView = view.findViewById(R.id.tv_dialog_lottie);
        textView.setText(message);
    }

    /**拿到要显示的消息*/
    public String getMessage() {
        return message;
    }

    /**设置要显示的消息*/
    public void setMessage(String message) {
        this.message = message;
    }
}
