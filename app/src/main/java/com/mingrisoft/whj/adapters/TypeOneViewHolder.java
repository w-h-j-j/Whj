package com.mingrisoft.whj.adapters;

import android.view.View;
import androidx.annotation.NonNull;

import com.mingrisoft.whj.R;
import com.youth.banner.Banner;

public class TypeOneViewHolder extends BaseViewHolder{

    public Banner banner;

    public TypeOneViewHolder(@NonNull View itemView) {
        super(itemView);
        banner=itemView.findViewById(R.id.banner_type_one);
    }

    @Override
    public void bindHolder(DataModel dataModel) {
        //tvName.setText(dataModel.getName());
        System.out.println("拿到的数据："+dataModel.getName());
    }



    /*@Override
    public void bindHolder(DataModel dataModel) {
        tvName.setText(dataModel.getName());
        System.out.println("拿到的数据："+dataModel.getName());
    }*/
}
