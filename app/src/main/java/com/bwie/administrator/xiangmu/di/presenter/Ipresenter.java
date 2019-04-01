package com.bwie.administrator.xiangmu.di.presenter;

import java.util.Map;

public interface Ipresenter {

    //登录注册
    void startRequest(String urlData, Map<String,String> map, Class clazz);
    //圈子列表
    void getCircle(String urlData,int page,int count, Class clazz );
    //首页轮播
    void getXBanner(String urlData,Class clazz);
    //get
    void get(String urlData,Class clazz);
    //post
    void post(String urlData,Map<String,String> map,Class clazz);
    //put
    void put(String urlData,Map<String,String> map,Class clazz);
    //delete
    void delete(String urlData,Map<String,String> map,Class clazz);

}
