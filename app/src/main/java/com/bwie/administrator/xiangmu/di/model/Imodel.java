package com.bwie.administrator.xiangmu.di.model;

import com.bwie.administrator.xiangmu.di.view.MyCallBack;

import java.util.Map;

public interface Imodel {

    //登录注册
    void startRequestData(String urlData, Map<String,String> map, Class clazz, MyCallBack myCallBack);
    //圈子列表
    void getCircleData(String urlData,int page,int count,Class clazz,MyCallBack myCallBack);
    //首页轮播
    void getXBannerData(String urlData,Class clazz,MyCallBack myCallBack);
    //get
    void get(String urlData,Class clazz,MyCallBack myCallBack);
    //post
    void postData(String urlData, Map<String,String> map,Class clazz,MyCallBack myCallBack);
    //put
    void put(String urlData,Map<String,String> map,Class clazz,MyCallBack myCallBack);
    //delete
    void delete(String urlData,Map<String,String> map,Class clazz,MyCallBack myCallBack);

}
