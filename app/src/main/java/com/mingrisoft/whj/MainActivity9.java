package com.mingrisoft.whj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.mingrisoft.whj.adapters.DataModel;
import com.mingrisoft.whj.adapters.DemoAdapter;
import com.mingrisoft.whj.adapters.MyImageLoader;
import com.mingrisoft.whj.adapters.TypeOneViewHolder;
import com.mingrisoft.whj.adapters.TypeThreeViewHolder;
import com.mingrisoft.whj.adapters.TypeTwoViewHolder;
import com.mingrisoft.whj.components.CustomActionBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity9 extends AppCompatActivity {

    private List<DataModel> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private DemoAdapter adapter;
    private List<String> urlPathList=new ArrayList<>();

    private List<Integer> imgList=new ArrayList<>();
    private List<String> nameList=new ArrayList<>();
    private List<String> priceList=new ArrayList<>();
    private List<Integer> shareList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        ImmersionBar.with(this).statusBarColor(R.color.orange).statusBarDarkFont(false).init();
        imgList.add(R.mipmap.icon_search);
        imgList.add(R.mipmap.icon_delete);
        imgList.add(R.mipmap.icon_search);
        imgList.add(R.mipmap.icon_delete);
        imgList.add(R.mipmap.icon_search);
        imgList.add(R.mipmap.icon_delete);
        imgList.add(R.mipmap.icon_search);
        imgList.add(R.mipmap.icon_delete);
        imgList.add(R.mipmap.icon_search);
        imgList.add(R.mipmap.icon_delete);
        imgList.add(R.mipmap.icon_search);
        imgList.add(R.mipmap.icon_delete);
        imgList.add(R.mipmap.icon_delete);
        urlPathList.add("https://img2.baidu.com/it/u=3395582942,4228440123&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        urlPathList.add("https://img0.baidu.com/it/u=705102279,1721672537&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        urlPathList.add("https://img0.baidu.com/it/u=2367444748,1187071260&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500");
        urlPathList.add("https://img0.baidu.com/it/u=2774905038,3717297690&fm=253&fmt=auto?w=889&h=500");

        recyclerView=findViewById(R.id.recyclerview9);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type=adapter.getItemViewType(position);
                switch (type){
                    case 1:
                        return 6;
                    case 2:
                    case 3:
                        return 3;
                }
                return 0;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new DemoAdapter(this, new DemoAdapter.IOnBindViews() {
            @Override
            public void onBindData(RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof TypeOneViewHolder){
                    TypeOneViewHolder typeOneViewHolder= (TypeOneViewHolder) holder;
                    typeOneViewHolder.bindHolder(list.get(position));
                    typeOneViewHolder.banner.setImageLoader(new MyImageLoader())
                            .setImages(list.get(position).getUrlList())
                            .start();

                }
                if (holder instanceof TypeTwoViewHolder){
                    TypeTwoViewHolder typeTwoViewHolder= (TypeTwoViewHolder) holder;
                    typeTwoViewHolder.bindHolder(list.get(position));
                    typeTwoViewHolder.tvName.setText(list.get(position).getName());
                    typeTwoViewHolder.tvAge.setText(list.get(position).getAge());
                    typeTwoViewHolder.tvName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToastUtils.show(list.get(position).getName());
                        }
                    });
                    typeTwoViewHolder.tvAge.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToastUtils.show(list.get(position).getAge());
                        }
                    });
                }
                if (holder instanceof TypeThreeViewHolder){
                    TypeThreeViewHolder typeThreeViewHolder= (TypeThreeViewHolder) holder;
                    typeThreeViewHolder.bindHolder(list.get(position));
                    typeThreeViewHolder.imgGoodsPhoto.setImageResource(list.get(position).getImgIdList().get(position-11));
                    typeThreeViewHolder.tvGoodsPrice.setOnClickListener((v)->{ToastUtils.show("价格："+typeThreeViewHolder.tvGoodsPrice.getText());});
                    typeThreeViewHolder.imgGoodsShare.setOnClickListener((view -> {
                        new AlertDialog.Builder(MainActivity9.this)
                                .setMessage("暂时不支持分享")
                                .setPositiveButton("知道了", (dialogInterface, i) -> { })
                                .show();
                    }));
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MainActivity9.this, "这是类型"+holder.getItemViewType(), Toast.LENGTH_SHORT).show();
                        ToastUtils.show("这是类型"+holder.getItemViewType());
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
        initData();

        LinearLayout layout=findViewById(R.id.ll_add_view);
        LayoutInflater inflater=LayoutInflater.from(this);
        CustomActionBar customActionBar= (CustomActionBar) inflater.inflate(R.layout.action_bar_view,null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        customActionBar.setLayoutParams(params);
        layout.addView(customActionBar);

    }

    private void initData(){
        for (int i = 0; i < 24; i++) {
            DataModel dataModel=new DataModel();
            int type;
            if (i<1){
                type=1;
                dataModel.setType(type);
                dataModel.setUrlList(urlPathList);
            }else if (i<11 && i>=1){
                type=2;
                dataModel.setType(type);
                dataModel.setName("托尼"+i);
                dataModel.setAge("年龄"+String.valueOf(i*2));
            }else {
                type=3;
                dataModel.setType(type);
                dataModel.setImgIdList(imgList);
            }


            list.add(dataModel);
        }
        adapter.addList(list);
    }

}
