package com.bwie.administrator.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.adapter.CircleListAdapter;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.CircleDZBean;
import com.bwie.administrator.xiangmu.data.bean.CircleListBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.ui.activity.MainActivity;
import com.bwie.administrator.xiangmu.ui.view.SpaceItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CircleFragment extends Fragment implements Iview {

    private int mPage=1;
    private IpresenterImp ipresenter;
    private XRecyclerView circle_recycle;
    private CircleListAdapter circleListAdapter;
    private TextView circle_dianzan_num;
    private ImageView circle_dianzan_img;
    int count=5;

    private int isClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        circle_recycle = view.findViewById(R.id.circle_recycle);
        ipresenter = new IpresenterImp(this);
        circle_dianzan_img = view.findViewById(R.id.circle_dianzan_img);
        circleListAdapter = new CircleListAdapter(getActivity());
        circle_recycle.addItemDecoration(new SpaceItemDecoration(10));
        circle_recycle.setAdapter(circleListAdapter);
        circle_recycle.setLoadingMoreEnabled(true);
        circle_recycle.setPullRefreshEnabled(true);
        circle_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                requestData();
            }

            @Override
            public void onLoadMore() {
                requestData();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        circle_recycle.setLayoutManager(linearLayoutManager);

        mPage=1;
        requestData();
        circleListAdapter.setOnDZClickListenter(new CircleListAdapter.DZClickListenter() {
            @Override
            public void onClick(int WhetherGreat, int position, int id) {
                isClick=position;
                if (WhetherGreat==1){
                    CancelLike(id);
                }else {
                    getLike(id);
                }
            }
        });

        return view;
    }

    //取消
    private void CancelLike(int id) {
        Map<String,String> map=new HashMap<>();
        map.put("circleId",id+"");
        ipresenter.delete(Api.UnCircleDZPath,map,CircleDZBean.class);

    }
    //点赞
    private void getLike(int id) {
        Map<String,String> map=new HashMap<>();
        map.put("circleId",id+"");
        ipresenter.startRequest(Api.CricleDZPath,map,CircleDZBean.class);
    }

    private void requestData() {
        ipresenter.getCircle(Api.circleListPath,mPage,count,CircleListBean.class);
    }

    @Override
    public void onSuccessData(Object data) {
        if (data instanceof CircleListBean){
            CircleListBean circleListBean= (CircleListBean) data;
            circleListAdapter.setList(circleListBean.getResult());

            mPage++;
            circle_recycle.loadMoreComplete();
            circle_recycle.refreshComplete();

        } else if(data instanceof CircleDZBean){
            CircleDZBean circleDZBean= (CircleDZBean) data;
            if(circleDZBean.getMessage().equals("请先登陆")){
                Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }else if(circleDZBean.getMessage().equals("点赞成功")){
                Toast.makeText(getContext(), circleDZBean.getMessage(), Toast.LENGTH_SHORT).show();
                circleListAdapter.Dianzan(isClick);
                circleListAdapter.notifyDataSetChanged();
            }else {
                circleListAdapter.UnDaianzan(isClick);
                Toast.makeText(getContext(), circleDZBean.getMessage(), Toast.LENGTH_SHORT).show();
                circleListAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onFailData(Exception e) {
        ipresenter.detach();
    }
}
