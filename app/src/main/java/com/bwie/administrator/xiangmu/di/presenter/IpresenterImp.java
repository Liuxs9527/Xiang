package com.bwie.administrator.xiangmu.di.presenter;

import com.bwie.administrator.xiangmu.di.model.Imodelmpl;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.di.view.MyCallBack;

import java.util.Map;

public class IpresenterImp implements Ipresenter {


    private Imodelmpl imodelmpl;
    private Iview iview;

    public IpresenterImp(Iview iview) {
        imodelmpl=new Imodelmpl();
        this.iview = iview;
    }

    //登录注册
    @Override
    public void startRequest(String urlData, Map<String, String> map, Class clazz) {
        imodelmpl.startRequestData(urlData, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });

    }
    //post
    @Override
    public void post(String urlData, Map<String, String> map, Class clazz) {
        imodelmpl.postData(urlData, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });
    }

    //圈子列表
    @Override
    public void getCircle(String urlData, int page, int count, Class clazz) {
        imodelmpl.getCircleData(urlData,page,count, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });
    }
    //XBanner
    @Override
    public void getXBanner(String urlData,Class clazz) {
        imodelmpl.getXBannerData(urlData,clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });
    }
    //get
    @Override
    public void get(String urlData,Class clazz) {
        imodelmpl.get(urlData,clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });
    }

    //put
    @Override
    public void put(String urlData,Map<String,String> map, Class clazz) {
        imodelmpl.put(urlData,map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });
    }

    @Override
    public void delete(String urlData, Map<String, String> map, Class clazz) {
        imodelmpl.delete(urlData, map, clazz, new MyCallBack() {
            @Override
            public void onSuccess(Object data) {
                iview.onSuccessData(data);
            }

            @Override
            public void onFail(Exception e) {
                iview.onFailData(e);
            }
        });
    }


    public void detach(){
        imodelmpl=null;
        iview=null;
    }
}
