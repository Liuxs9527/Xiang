package com.bwie.administrator.xiangmu.di.model;

import android.os.AsyncTask;

import com.bwie.administrator.xiangmu.di.contrant.ZhuceContrant;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ZhuIModelImpl implements ZhuceContrant.IZheCeModel {

    public static final String REQUEST_IRL = "http://mobile.bwstudent.com/small/user/v1/register";

    @Override
    public void containData(String userName, String pwd, CallBack callBack) {
        new SubAysncTask(userName,pwd,callBack).execute(REQUEST_IRL);
    }

    class SubAysncTask extends AsyncTask<String,Integer,String>{

        String userName;
        String pwd;
        CallBack callBack;

        public SubAysncTask(String userName,String pwd,CallBack callBack){
            this.userName = userName;
            this.pwd = pwd;
            this.callBack = callBack;
        }


        @Override
        protected String doInBackground(String... urlStrings) {
            try {
                //请求的URL字串
                String urlString = urlStrings[0];
                String completeUrlString = urlString + "?phone="+userName+"&pwd="+pwd;
                //请求的URL地址
                URL url = new URL(completeUrlString);
                //请求对象
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //设置请求方法
                //参数提交
                //提交的数据有phone、pwd
                connection.setRequestMethod("POST");
                //连接
                connection.connect();
                //判断请求码
                if (connection.getResponseCode() == 200) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    //输入流读取
                    InputStream inputStream = connection.getInputStream();
                    //搞笑字节流
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    //把读取的内容转换成字串
                    return stringBuilder.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String responsne) {
            super.onPostExecute(responsne);
            callBack.responseData(responsne);
        }
    }

}
