package com.mingrisoft.basedialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageDialog extends Dialog {
    private TextView cancelTv,confirmTv;
    private TextView titleTv,messageTv;
    private String title,message;       //外界设置的文本和消息
    private String cancelStr,confirmStr;
    private OnCancelClickListener onCancelClickListener;
    private OnConfirmClickListener onConfirmClickListener;

    public MessageDialog(@NonNull Context context) {
        //int styleId=R.style.DialogTheme;
        super(context,R.style.dialog_theme);    //设置默认加载一个对话狂的主题
    }

    public MessageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MessageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dialog);

        //这是为对话框弹出时设置动画
        Window window=this.getWindow();
        if (window!=null){
            window.setWindowAnimations(R.style.dialog_anim);
        }

        titleTv=findViewById(R.id.tv_title);
        titleTv.setText(title);
        messageTv=findViewById(R.id.tv_msg);
        messageTv.setText(message);

        cancelTv=findViewById(R.id.tv_cancel);
        if (cancelStr!=null){
            cancelTv.setText(cancelStr);
        }
        cancelTv.setOnClickListener((view -> {
            if (onCancelClickListener!=null){
                onCancelClickListener.onClick();
            }

        }));

        confirmTv=findViewById(R.id.tv_confirm);
        if (confirmStr!=null){
            confirmTv.setText(confirmStr);
        }
        confirmTv.setOnClickListener((view -> {
            if (onConfirmClickListener!=null){
                onConfirmClickListener.onClick();
            }
        }));

    }

    public void setCancelClickListener(String name,OnCancelClickListener onCancelClickListener){
        if (name!=null){
            this.cancelStr=name;
        }
        this.onCancelClickListener=onCancelClickListener;
    }

    public void setCancelClickListener(OnCancelClickListener onCancelClickListener){
        this.onCancelClickListener=onCancelClickListener;
    }

    public void setConfirmClickListener(String name,OnConfirmClickListener onConfirmClickListener){
        if (name!=null){
            this.confirmStr=name;
        }
        this.onConfirmClickListener=onConfirmClickListener;
    }

    public void setConfirmClickListener(OnConfirmClickListener onConfirmClickListener){
        this.onConfirmClickListener=onConfirmClickListener;
    }



    //这一步可以省略，在设置按钮监听里已经做出了初始化       this.cancelStr=name;
    /*private String getCancelStr() {
        return cancelStr;
    }

    private void setCancelStr(String cancelStr) {
        this.cancelStr = cancelStr;
    }

    private String getConfirmStr() {
        return confirmStr;
    }

    private void setConfirmStr(String confirmStr) {
        this.confirmStr = confirmStr;
    }*/



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface OnCancelClickListener{
        void onClick();
    }

    public interface OnConfirmClickListener{
        void onClick();
    }
}
