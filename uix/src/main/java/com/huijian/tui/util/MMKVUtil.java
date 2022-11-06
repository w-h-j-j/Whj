package com.huijian.tui.util;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.HashSet;
import java.util.Set;

/**用于存储轻量级数据的工具类*/
public class MMKVUtil {

    private final MMKV mmkv;
    private static MMKVUtil instance;

    private MMKVUtil(){
        mmkv=MMKV.defaultMMKV();
    }

    public static MMKVUtil getInstance(){
        if (instance==null){
            synchronized (MMKVUtil.class){
                if (instance==null){
                    instance=new MMKVUtil();
                }
            }
        }
        return instance;
    }

    public void setEncode(String key,String value){
        mmkv.encode(key,value);
    }

    public String getDecodeString(String key){
        return mmkv.decodeString(key,"Not find key!");
    }

    public void setEncode(String key,int value){
        mmkv.encode(key,value);
    }

    public int getDecodeInt(String key){
        return mmkv.decodeInt(key,0);
    }

    public void setEncode(String key,boolean value){
        mmkv.encode(key,value);
    }

    public boolean getDecodeBool(String key){
        return mmkv.decodeBool(key,false);
    }

    /**存储集合*/
    public void setEncode(String key, HashSet<String> hashSet){
        mmkv.encode(key,hashSet);
    }

    /**拿到集合*/
    public Set<String> decodeStringSet(String key){
        return mmkv.decodeStringSet(key,new HashSet<String>(){{add("null");}});
    }

    /**存储Bean实体对象*/
    public void setEncode(String key,Object object){
        mmkv.encode(key, (Parcelable) object);
    }

    /**提取Bean实体对象*/
    public <T> T getDecodeParcelable(String key,Class<T> tClass){
        return (T) mmkv.decodeParcelable(key,(Class<? extends Parcelable>)tClass);
    }

    /**删除单个数据*/
    public void removeValueForKey(String key){
        mmkv.removeValueForKey(key);
    }

    /**删除所有数据*/
    public void clearAll(){
        mmkv.clearAll();
    }

    /**检查是否存在key*/
    public boolean hasKey(String key){
        return mmkv.containsKey(key);
    }

    /**获取key的数量*/
    public long getKeyCount(){
        return mmkv.totalSize();
    }

    /**获取所有key*/
    public String[] getKeyArray(){
        return mmkv.allKeys();
    }

}
