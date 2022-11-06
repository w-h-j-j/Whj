package com.mingrisoft.whj.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mingrisoft.whj.R;

public class TypeThreeViewHolder extends BaseViewHolder{

    public ImageView imgGoodsPhoto;
    public TextView tvGoodsName;
    public TextView tvGoodsPrice;
    public ImageView imgGoodsShare;

    public TypeThreeViewHolder(@NonNull View itemView) {
        super(itemView);
        imgGoodsPhoto=itemView.findViewById(R.id.img_type_three_goods_photo);
        tvGoodsName=itemView.findViewById(R.id.tv_type_three_goods_name);
        tvGoodsPrice=itemView.findViewById(R.id.tv_type_three_goods_price);
        imgGoodsShare=itemView.findViewById(R.id.img_type_three_goods_share);
    }

    @Override
    public void bindHolder(DataModel dataModel) {

    }
}
