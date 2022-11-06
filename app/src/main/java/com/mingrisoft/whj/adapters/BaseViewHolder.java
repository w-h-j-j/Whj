package com.mingrisoft.whj.adapters;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public abstract void bindHolder(DataModel dataModel);

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
