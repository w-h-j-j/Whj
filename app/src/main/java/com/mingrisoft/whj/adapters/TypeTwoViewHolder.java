package com.mingrisoft.whj.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mingrisoft.whj.R;

public class TypeTwoViewHolder extends BaseViewHolder{

    public Button tvName;
    public Button tvAge;

    public TypeTwoViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tv_name_type_two);
        tvAge=itemView.findViewById(R.id.tv_age_type_two);
    }

    @Override
    public void bindHolder(DataModel dataModel) {
        //tvName.setText(dataModel.getName());
        //tvAge.setText(dataModel.getAge());
    }



    /*@Override
    public void bindHolder(DataModel dataModel) {
        tvName.setText(dataModel.getName());
        tvAge.setText(dataModel.getAge());
    }*/
}
