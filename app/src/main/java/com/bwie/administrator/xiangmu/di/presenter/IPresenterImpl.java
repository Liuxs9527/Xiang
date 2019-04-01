package com.bwie.administrator.xiangmu.di.presenter;

import com.bwie.administrator.xiangmu.di.contrant.Contrant;
import com.bwie.administrator.xiangmu.di.model.IModelImpl;

import java.lang.ref.SoftReference;

public class IPresenterImpl implements Contrant.IDengPresenter {

    Contrant.IDengView dengView;
    private SoftReference<Contrant.IDengView>  reference;
    private Contrant.IDengModel dengModel;


    @Override
    public void attachView(Contrant.IDengView dengView) {

        this.dengView = dengView;

        reference = new SoftReference<>(dengView);

        dengModel = new IModelImpl();

    }

    @Override
    public void detachView(Contrant.IDengView dengView) {
        reference.clear();;
    }

    @Override
    public void requstLoginData(String userName, String password) {
        dengModel.containLoginResponseData(userName, password, new Contrant.IDengModel.CallBack() {
            @Override
            public void responseData(String responseData) {
                dengView.showData(responseData);
            }
        });
    }
}
