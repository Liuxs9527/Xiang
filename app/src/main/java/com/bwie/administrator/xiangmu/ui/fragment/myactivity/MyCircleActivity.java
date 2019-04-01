package com.bwie.administrator.xiangmu.ui.fragment.myactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.adapter.MyCircleAdapter;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.AddAdressBean;
import com.bwie.administrator.xiangmu.data.bean.MyCircleBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.ui.view.SpaceItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCircleActivity extends AppCompatActivity implements Iview {

    @BindView(R.id.iv_circle_recycle)
    RecyclerView ivCircleRecycle;
    private IpresenterImp ipresenter;
    private MyCircleAdapter myCircleAdapter;
    private MyCircleBean myCircleBean;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_circle);
        ButterKnife.bind(this);
        ipresenter = new IpresenterImp(this);
        getCircleData();
        myCircleAdapter = new MyCircleAdapter(this);
        ivCircleRecycle.setAdapter(myCircleAdapter);
        ivCircleRecycle.addItemDecoration(new SpaceItemDecoration(20));
        myCircleAdapter.setMyCircleListenter(new MyCircleAdapter.MyCircleListener() {
            @Override
            public void onClick(int position) {
                id = myCircleBean.getResult().get(position).getId();
                myCircleAdapter.delList(position);
                getDelMyCircle();
            }
        });


    }

    private void getDelMyCircle() {
        Map<String, String> map = new HashMap<>();
        map.put("circleId", id + "");
        ipresenter.delete(Api.DelMyCirclePath, map, AddAdressBean.class);


    }

    private void getCircleData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ivCircleRecycle.setLayoutManager(linearLayoutManager);
        ipresenter.getCircle(Api.GetMyCirclePath, 1, 10, MyCircleBean.class);


    }

    @Override
    public void onSuccessData(Object data) {
        if (data instanceof MyCircleBean) {
            myCircleBean = (MyCircleBean) data;
            myCircleAdapter.setList(myCircleBean.getResult());


        } else if (data instanceof AddAdressBean) {
            AddAdressBean addAdressBean = (AddAdressBean) data;

            myCircleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailData(Exception e) {

    }
}