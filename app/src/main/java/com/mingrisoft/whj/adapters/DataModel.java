package com.mingrisoft.whj.adapters;

import java.util.List;

public class DataModel {

    private int type;
    private String name;
    private String age;

    private List<String> urlList;

    private List<Integer> imgIdList;
    private List<String> goodsNameList;
    private List<String> goodsPriceList;
    private List<Integer> goodsShareList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<Integer> getImgIdList() {
        return imgIdList;
    }

    public void setImgIdList(List<Integer> imgIdList) {
        this.imgIdList = imgIdList;
    }

    public List<String> getGoodsNameList() {
        return goodsNameList;
    }

    public void setGoodsNameList(List<String> goodsNameList) {
        this.goodsNameList = goodsNameList;
    }

    public List<String> getGoodsPriceList() {
        return goodsPriceList;
    }

    public void setGoodsPriceList(List<String> goodsPriceList) {
        this.goodsPriceList = goodsPriceList;
    }

    public List<Integer> getGoodsShareList() {
        return goodsShareList;
    }

    public void setGoodsShareList(List<Integer> goodsShareList) {
        this.goodsShareList = goodsShareList;
    }

}
