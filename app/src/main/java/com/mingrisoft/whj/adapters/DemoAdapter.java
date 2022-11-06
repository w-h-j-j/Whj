package com.mingrisoft.whj.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mingrisoft.whj.R;

import java.util.ArrayList;
import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final Context context;
    private final LayoutInflater inflater;
    private final List<DataModel> list=new ArrayList<>();
    private final IOnBindViews iOnBindView;

    public DemoAdapter(Context context, IOnBindViews iOnBindView) {
        this.context=context;
        this.iOnBindView=iOnBindView;
        inflater=LayoutInflater.from(context);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addList(List<DataModel> mList){
        list.addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                View view1=inflater.inflate(R.layout.item_type_one,parent,false);
                return new TypeOneViewHolder(view1);
            case 2:
                View view2=inflater.inflate(R.layout.item_type_two,parent,false);
                return new TypeTwoViewHolder(view2);
            case 3:
                View view3=inflater.inflate(R.layout.item_type_three,parent,false);
                return new TypeThreeViewHolder(view3);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //BaseViewHolder holder1= (BaseViewHolder) holder;
        //holder1.bindHolder(list.get(position));

        /*holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("适配器："+position);
            }
        });*/
        /*if (holder instanceof TypeOneViewHolder){
            TypeOneViewHolder typeOneViewHolder= (TypeOneViewHolder) holder;
            typeOneViewHolder.bindHolder(list.get(position));
        }
        if (holder instanceof TypeTwoViewHolder){
            TypeTwoViewHolder typeTwoViewHolder= (TypeTwoViewHolder) holder;
            typeTwoViewHolder.bindHolder(list.get(position));
            typeTwoViewHolder.tvName.setText(list.get(position).getName());
            typeTwoViewHolder.tvAge.setText(list.get(position).getAge());
        }*/
        if (iOnBindView!=null){
            iOnBindView.onBindData(holder, position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface IOnBindViews {
        void onBindData( RecyclerView.ViewHolder holder, int i);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    interface OnItemClickListener{
        void onItemClick(RecyclerView.ViewHolder holder,int position);
    }


}
