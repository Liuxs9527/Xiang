package com.bwie.administrator.xiangmu.di.presenter;

import com.bwie.administrator.xiangmu.di.contrant.ZhuceContrant;
import com.bwie.administrator.xiangmu.di.model.ZhuIModelImpl;

import java.lang.ref.SoftReference;

public class ZhuIPresenterImpl implements ZhuceContrant.IZhuCePresenter {

    ZhuceContrant.IZhuCerView zhuCerView;
    private SoftReference<ZhuceContrant.IZhuCerView> reference;
    private ZhuceContrant.IZheCeModel model;


    @Override
    public void attahView(ZhuceContrant.IZhuCerView zhuceView) {
        this.zhuCerView = zhuceView;

        reference = new SoftReference<>(zhuCerView);

        model = new ZhuIModelImpl();
    }

    @Override
    public void detachView(ZhuceContrant.IZhuCerView zhuceView) {
        reference.clear();;
    }

    @Override
    public void requestData(String userName, String pwd) {
        model.containData(userName, pwd, new ZhuceContrant.IZheCeModel.CallBack() {
            @Override
            public void responseData(String responsne) {
                zhuCerView.showData(responsne);
            }
        });
    }
}
