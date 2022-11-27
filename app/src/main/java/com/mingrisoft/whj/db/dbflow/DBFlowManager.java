package com.mingrisoft.whj.db.dbflow;


import android.util.Log;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DBFlowManager {
    private static final String TAG="DBFlow数据存储";

    /**插入数据*/
    public static void insertData(String price,String color,String model){
        SQLite.insert(Apple.class)
                .columns(Apple_Table.price,Apple_Table.color,Apple_Table.model)
                .values(price,color,model)
                .execute();
    }

    /**修改数据*/
    public static void updateData(String newPrice,String oldPrice){
        Apple apple = SQLite.update(Apple.class)
                .set(Apple_Table.price.eq(newPrice))
                .where(Apple_Table.price.eq(oldPrice))
                .querySingle();
        if (apple!=null){
            apple.update();
            Log.i(TAG, "updateData: 修改成功!");
        }else {
            Log.e(TAG, "updateData: 修改失败!");
        }
    }

    /**通过ID删除数据*/
    public static void deleteDataById(int id){
        Apple apple = SQLite.select()
                .from(Apple.class)
                .where(Apple_Table.id.is(id))
                .querySingle();
        if (apple!=null){
            apple.delete();
            Log.i(TAG, "deleteData: 删除成功!");
        }else {
            Log.e(TAG, "deleteData: 删除失败!" );
        }
    }

    /**获取所有数据*/
    public static List<Apple> getAllData(){
        return SQLite.select()
                .from(Apple.class)
                .queryList();
    }

    /**通过model获取数据*/
    public static List<Apple> getDataByModel(String model){
        List<Apple> apples = SQLite.select()
                .from(Apple.class)
                .where(Apple_Table.model.is(model))
                .queryList();
        if (apples.size()!=0){
            Log.i(TAG, "getDataByModel: 查询数据成功!");
            return apples;
        }else {
            Log.e(TAG, "getDataByModel: 查询数据失败!");
            return null;
        }
    }
}
