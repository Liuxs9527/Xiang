package com.bwie.administrator.xiangmu.ui.fragment.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.adapter.MyFootAdapter;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.MyFootBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.ui.activity.ParticularsActivity;
import com.bwie.administrator.xiangmu.ui.view.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFootActivity extends AppCompatActivity implements Iview {

    @BindView(R.id.my_foot_recycle)
    RecyclerView myFootRecycle;
    private IpresenterImp ipresenter;
    private MyFootAdapter myFootAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_foot);
        ButterKnife.bind(this);
        ipresenter = new IpresenterImp(this);
        myFootAdapter = new MyFootAdapter(this);
        getMyFootData();
        myFootAdapter.setMyFootClickListener(new MyFootAdapter.MyFootListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getApplicationContext(),ParticularsActivity.class);
                int pid = myFootAdapter.getPid(position);
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    private void getMyFootData() {
        myFootRecycle.addItemDecoration(new SpaceItemDecoration(10));
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        myFootRecycle.setLayoutManager(staggeredGridLayoutManager);

        myFootRecycle.setAdapter(myFootAdapter);
        ipresenter.getCircle(Api.GetMyFootPath,1,10,MyFootBean.class);

    }

    @Override
    public void onSuccessData(Object data) {
        if (data instanceof MyFootBean){
            MyFootBean myFootBean= (MyFootBean) data;
            myFootAdapter.setList(myFootBean.getResult());

        }
    }

    @Override
    public void onFailData(Exception e) {

    }
}
