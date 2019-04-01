package com.bwie.administrator.xiangmu.di.model;

import android.util.Log;

import com.bwie.administrator.xiangmu.data.util.OkHttpUtils;
import com.bwie.administrator.xiangmu.di.view.MyCallBack;
import com.google.gson.Gson;

import java.util.Map;

public class Imodelmpl implements Imodel {
    //登录注册
    @Override
    public void startRequestData(String urlData, final Map<String, String> map, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().postEneuque(urlData, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                myCallBack.onSuccess(data);
            }

            @Override
            public void onFail(Exception e) {
                myCallBack.onFail(e);
            }


        });
    }

    @Override
    public void postData(String urlData, final Map<String, String> map, final Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().post(urlData, map, new OkHttpUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data,clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack != null){
                        myCallBack.onFail(e);
                    }
                }
            }

            @Override
            public void onFail(String error) {
                Log.i("cuowu",error);
            }
        });

    }

    //圈子列表
    @Override
    public void getCircleData(String urlData, int page, int count, final Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().getCircle(urlData, page, count, new OkHttpUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if (myCallBack != null) {
                        myCallBack.onSuccess(o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (myCallBack != null) {
                        myCallBack.onFail(e);
                    }
                }
            }

            @Override
            public void onFail(String error) {
            }
        });
    }

    /**
     * 首页轮播
     * @param urlData
     * @param clazz
     * @param myCallBack
     */
    @Override
    public void getXBannerData(String urlData, final Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().getXBanners(urlData, new OkHttpUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if (myCallBack != null) {
                    myCallBack.onSuccess(o);
                }
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    /**
     * get
     * @param urlData
     * @param clazz
     * @param myCallBack
     */
    @Override
    public void get(String urlData, final Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().get(urlData, new OkHttpUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack != null){
                        myCallBack.onFail(e);
                    }
                }
            }

            @Override
            public void onFail(String error) {

            }
        });
    }


    @Override
    public void put(String urlData,Map<String,String> map,  final Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().put(urlData,map, new OkHttpUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack != null){
                        myCallBack.onFail(e);
                    }
                }
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void delete(String urlData, Map<String, String> map, final Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getOkHttpUtils().del(urlData, map, new OkHttpUtils.HttpListener() {
            @Override
            public void onSuccess(String data) {
                try {
                    Object o = new Gson().fromJson(data, clazz);
                    if(myCallBack != null){
                        myCallBack.onSuccess(o);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    if(myCallBack != null){
                        myCallBack.onFail(e);
                    }
                }
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
