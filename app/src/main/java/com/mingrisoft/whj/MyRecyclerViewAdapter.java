package com.mingrisoft.whj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyRecyclerViewAdapter extends RecyclerSwipeAdapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<String> list;
    private IOnBindView handle;
    private View view;

    public HashMap<Integer,Boolean> hashMap=new HashMap<>();      //用于保存复选框的选中状态

    public MyRecyclerViewAdapter(Context context, List<String> list,IOnBindView handle) {
        this.context = context;
        this.list = list;
        this.handle=handle;
        if (!list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(i,false);
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder,int position) {
        handle.onBindData(viewHolder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public CheckBox checkBox;
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox_item);
            textView=itemView.findViewById(R.id.tv_item);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onRecyclerItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    interface IOnBindView{
        void onBindData(MyViewHolder holder,int i);
    }

    //////////////////////////为RecyclerView设置item的单击事件////////////////////////////
    //定义接口,并实现方法
    public interface OnRecyclerItemClickListener{
        void onRecyclerItemClick(int position);
    }
    //创建这个接口的对象
    public OnRecyclerItemClickListener mOnItemClickListener;
    //供外部使用
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        this.mOnItemClickListener=listener;
    }
    //////////////////////////为RecyclerView设置item的单机事件////////////////////////////

    /**全选*/
    public void chooseAll(){
        if (!list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(i,true);
            }
        }
        notifyDataSetChanged();
    }

    /**取消全选*/
    public void cancelChooseAll(){
        if (!list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                hashMap.put(i,false);
            }
        }
        notifyDataSetChanged();
    }

    /**全选*/
    public void selectAll(){
        Set<Map.Entry<Integer,Boolean>> entrySet=hashMap.entrySet();
        boolean shouldAll=false;
        for (Map.Entry<Integer,Boolean> entry :entrySet){
            Boolean value=entry.getValue();
            if (!value){
                shouldAll=true;
            }
        }
        for (Map.Entry<Integer,Boolean> entry:entrySet) {
            entry.setValue(shouldAll);
        }
        notifyDataSetChanged();
    }

    /**取消全选*/
    public void cancelSelectAll(){
        Set<Map.Entry<Integer,Boolean>> entrySet=hashMap.entrySet();
        boolean shouldAll=true;
        for (Map.Entry<Integer,Boolean> entry :entrySet){
            Boolean value=entry.getValue();
            if (value){
                shouldAll=false;
            }
        }
        for (Map.Entry<Integer,Boolean> entry:entrySet) {
            entry.setValue(shouldAll);
        }
        notifyDataSetChanged();
    }

        /**反选*/
    public void reverseSelect(){
        Set<Map.Entry<Integer,Boolean>> entrySet=hashMap.entrySet();
        for (Map.Entry<Integer,Boolean> entry :entrySet){
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }

    /**只允许单选*/
    public void singleChoose(int position){
        Set<Map.Entry<Integer,Boolean>> entrySet=hashMap.entrySet();
        for (Map.Entry<Integer,Boolean> entry :entrySet){
            entry.setValue(false);
        }
        hashMap.put(position,true);
        notifyDataSetChanged();
    }
}

