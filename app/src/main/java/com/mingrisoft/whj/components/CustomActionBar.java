package com.mingrisoft.whj.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mingrisoft.whj.R;

public class CustomActionBar extends ConstraintLayout {


    public CustomActionBar(@NonNull Context context) {
        super(context);
    }

    public CustomActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ImageView getScanImgView(){
        return findViewById(R.id.img_scan);
    }

    public SearchView getSearchView(){
        return findViewById(R.id.search_view);
    }

    public ImageView getMgsImgView(){
        return findViewById(R.id.img_message);
    }
}
