package com.mingrisoft.basedialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LoadingDialog extends Dialog {
    private final Context context;
    private ImageView imageView;
    private String message;

    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.dialog_theme);
        this.context=context;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=View.inflate(context, R.layout.view_dialog_loading,null);
        setContentView(view);
        imageView=view.findViewById(R.id.img_rotate_loading);
        TextView textView = view.findViewById(R.id.tv_message_loading);
        textView.setText(message);
        setImageAnim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //设置动画
    private void setImageAnim(){
        imageView.setImageResource(R.drawable.img);
        @SuppressLint("ResourceType") Animation animation= AnimationUtils.loadAnimation(getContext(),R.drawable.rotate_anim);
        imageView.startAnimation(animation);
        imageView.setBackgroundResource(R.drawable.img);
    }
}
