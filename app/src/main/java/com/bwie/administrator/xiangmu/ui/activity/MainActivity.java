package com.bwie.administrator.xiangmu.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.LoginBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Iview {


    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_seepassword)
    ImageView loginSeepassword;
    @BindView(R.id.jzpassword)
    CheckBox jzpassword;
    @BindView(R.id.go_zc)
    TextView goZc;
    @BindView(R.id.login)
    Button login;

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private IpresenterImp ipresenter;
    private String phonenums;
    private String passwords;
    private LoginBean loginBean;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //判断网络状态二
        getNetInfor();

        initView();
        //去注册
        goZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
                startActivity(intent);
            }
        });
        //密码的显示和隐藏
        findViewById(R.id.login_seepassword).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenums = loginPhone.getText().toString();
                passwords = loginPassword.getText().toString();
                if (jzpassword.isChecked()) {
                    editor.putBoolean("check_jz", true);
                    editor.putString("phonenum", phonenums);
                    editor.putString("password", passwords);
                    editor.commit();
                } else {
                    editor.clear();
                    editor.commit();
                    jzpassword.setChecked(false);
                }
                getPath();
            }
        });
    }



    private void getPath() {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phonenums);
        map.put("pwd", passwords);
        ipresenter.startRequest(Api.loginPath, map, LoginBean.class);

    }

    private void initView() {
        loginPhone = findViewById(R.id.login_phone);
        loginPassword = findViewById(R.id.login_password);
        jzpassword = findViewById(R.id.jzpassword);
        goZc = findViewById(R.id.go_zc);
        login = findViewById(R.id.login);
        ipresenter = new IpresenterImp(this);
        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean check_jz = sharedPreferences.getBoolean("check_jz", false);
        if (check_jz) {
            String phonenums = sharedPreferences.getString("phonenum", null);
            String passwords = sharedPreferences.getString("password", null);
            loginPhone.setText(phonenums);
            loginPassword.setText(passwords);
            jzpassword.setChecked(true);
        }
    }
    //  成功
    @Override
    public void onSuccessData(Object data) {
        if (data instanceof LoginBean) {
            loginBean = (LoginBean) data;
            if (loginBean.getMessage().equals("登录成功")){

                /*boolean networkConnected = isNetworkConnected(context);*/

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("spDemo", MODE_PRIVATE);
                sharedPreferences.edit().putString("userId", loginBean.getResult().getUserId() + "").putString("sessionId", loginBean.getResult().getSessionId()).commit();
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this,loginBean.getMessage()+"",Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onFailData(Exception e) {
        e.printStackTrace();
    }


    //判断网络状态
    /*public static boolean isNetworkConnected(Context context){
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo()!=null){
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }*/

    //判断网络状态
    private void getNetInfor() {
        //首先是获取网络连接管理者
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        //网络状态存在并且是已连接状态
        if (info != null && info.isConnected()) {
            Toast.makeText(MainActivity.this, "网络已连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            //下面的这种写法你应该看得懂
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("请检查网络连接")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (android.os.Build.VERSION.SDK_INT > 10) {
                                //安卓系统3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
                                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                            } else {
                                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        }
                    })
                    .show();
        }
    }

}