package com.bwie.administrator.xiangmu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.RegisterBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.ui.app.YZUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhuceActivity extends AppCompatActivity implements Iview {

    @BindView(R.id.zc_phone)
    EditText zcPhone;
    @BindView(R.id.zc_security)
    EditText zcSecurity;
    @BindView(R.id.zc_password)
    EditText zcPassword;
    @BindView(R.id.zc_seepassword)
    ImageView zcSeepassword;
    @BindView(R.id.go_login)
    TextView goLogin;
    @BindView(R.id.zc_button)
    Button zcButton;

    private String zcphones;
    private String zcpasswords;
    private IpresenterImp ipresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);

        initView();
        PDUserandPwd();
        //密码框显示隐藏
        findViewById(R.id.zc_seepassword).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() ==MotionEvent.ACTION_DOWN){
                    zcPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    zcPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
        //已经注册，去登陆跳转
        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhuceActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {

        ipresenter = new IpresenterImp(ZhuceActivity.this);

    }
    // 点击注册，判断手机号和密码是否正确
    private void PDUserandPwd() {
        zcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YZUtils.isPhoneValidator(zcPhone.getText().toString())){
                    if (YZUtils.isPwdValidator(zcPassword.getText().toString())){

                        getData();
                    }else {
                        Toast.makeText(ZhuceActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(ZhuceActivity.this,"手机号错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getData() {
        Map<String,String> map=new HashMap<>();
        map.put("phone",zcPhone.getText().toString());
        map.put("pwd",zcPassword.getText().toString());
        ipresenter.startRequest(Api.registerPath,map,RegisterBean.class);
    }


    @Override
    public void onSuccessData(Object data) {
        if (data instanceof RegisterBean){
            RegisterBean registerBean= (RegisterBean) data;
            String status = registerBean.getStatus();
            if (status.equals("0000")){
                Toast.makeText(ZhuceActivity.this,"注册成功,登陆一下看看有什么新东西吧",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ZhuceActivity.this,MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(ZhuceActivity.this,registerBean.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }

    }

    @Override
    public void onFailData(Exception e) {
        ipresenter.detach();
    }
}

