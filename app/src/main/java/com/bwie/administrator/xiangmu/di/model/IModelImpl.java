package com.bwie.administrator.xiangmu.di.model;

import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.di.contrant.Contrant;
import com.lzy.okgo.OkGo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IModelImpl  implements Contrant.IDengModel  {


    @Override
    public void containLoginResponseData(String userName, String password, CallBack callback) {
        requestLoginDataEnqueue(userName, password, callback);
    }

    private void requestLoginDataEnqueue(String userName, String password, final CallBack callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .method("POST", formBody)
                .url(Api.loginPath + "?phone=" + userName + "&pwd=" + password)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                String responseData = e.getMessage();
                callback.responseData(responseData);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseData = response.body().string();
                callback.responseData(responseData);

            }
        });
    }
}
