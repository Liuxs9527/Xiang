package com.bwie.administrator.xiangmu.ui.fragment.myactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.SelByIdBean;
import com.bwie.administrator.xiangmu.data.bean.UpdataPassWordBean;
import com.bwie.administrator.xiangmu.data.bean.UpdataUserNameBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfromationActivity extends AppCompatActivity implements View.OnClickListener ,Iview {

    @BindView(R.id.change_tx)
    SimpleDraweeView changeTx;
    @BindView(R.id.change_name)
    TextView changeName;
    @BindView(R.id.change_password)
    TextView changePassword;
    private IpresenterImp ipresenter;
    private SelByIdBean selByIdBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infromation);
        ButterKnife.bind(this);

        changeTx.setOnClickListener(this);
        changeName.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        ipresenter = new IpresenterImp(MyInfromationActivity.this);
        ipresenter.get(Api.GetUserByIdPath,SelByIdBean.class);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_tx:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MyInfromationActivity.this);
                dialog.setTitle("需要更换头像么");
                dialog.setIcon(R.mipmap.bitmap);
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                        startActivityForResult(intent,1);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MyInfromationActivity.this, "您已取消更换头像", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;

            case R.id.change_name:
                AlertDialog.Builder dialogname=new AlertDialog.Builder(MyInfromationActivity.this);
                final EditText name = new EditText(this);
                String s = changeName.getText().toString();
                dialogname.setTitle("需要更换名称么");
                dialogname.setView(name);
                name.setText(s);
                dialogname.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final Editable text = name.getText();
                        Map<String,String> map=new HashMap<>();
                        map.put("nickName",text+"");

                        ipresenter.put(Api.UpdataUsernamePath,map,UpdataUserNameBean.class);
                        changeName.setText(text);
                    }
                });
                dialogname.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MyInfromationActivity.this, "您已取消更换名称", Toast.LENGTH_SHORT).show();
                    }
                });

                dialogname.show();
                break;

            case R.id.change_password:
                final AlertDialog.Builder dialogpass=new AlertDialog.Builder(MyInfromationActivity.this);
                final EditText editTextPass = new EditText(this);
                final String pass = changePassword.getText().toString().trim();
                dialogpass.setTitle("需要更换密码么");
                dialogpass.setTitle("你的旧密码："+pass);
                dialogpass.setView(editTextPass);
                dialogpass.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newPass = editTextPass.getText().toString();
                        Map<String,String> map=new HashMap<>();
                        map.put("oldPwd",pass+"");
                        map.put("newPwd",newPass+"");
                        changePassword.setText(newPass);
                        ipresenter.put(Api.UpdataPasswordPath,map,UpdataPassWordBean.class);
                    }
                });

                dialogpass.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MyInfromationActivity.this, "您已取消更换密码", Toast.LENGTH_SHORT).show();
                    }
                });

                dialogpass.show();

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==1){
            if (data!=null){
                changeTx.setImageURI(data.getData());
            }
        }
    }



    @Override
    public void onSuccessData(Object data) {
        if (data instanceof SelByIdBean){
            selByIdBean = (SelByIdBean) data;
            changeName.setText(selByIdBean.getResult().getNickName());
            changePassword.setText(selByIdBean.getResult().getPassword());
            changeTx.setImageURI(selByIdBean.getResult().getHeadPic());
        }


    }

    @Override
    public void onFailData(Exception e) {
        Toast.makeText(this, "数据获取失败", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
