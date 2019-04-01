package com.bwie.administrator.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.ui.fragment.myactivity.MyCircleActivity;
import com.bwie.administrator.xiangmu.ui.fragment.myactivity.MyFootActivity;
import com.bwie.administrator.xiangmu.ui.fragment.myactivity.MyInfromationActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.MyTopImage)
    SimpleDraweeView MyTopImage;
    @BindView(R.id.text_geren)
    TextView textGeren;
    @BindView(R.id.text_quanzi)
    TextView textQuanzi;
    @BindView(R.id.text_zuji)
    TextView textZuji;
    @BindView(R.id.text_qianbao)
    TextView textQianbao;
    @BindView(R.id.text_dizhi)
    TextView textDizhi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.MyTopImage, R.id.text_geren, R.id.text_quanzi, R.id.text_zuji, R.id.text_qianbao, R.id.text_dizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MyTopImage:
                break;
            case R.id.text_geren:

                Intent intent = new Intent(getActivity(),MyInfromationActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "进入我的信息页面", Toast.LENGTH_SHORT).show();

                break;
            case R.id.text_quanzi:

                Intent intent1 = new Intent(getActivity(),MyCircleActivity.class);
                startActivity(intent1);
                Toast.makeText(getActivity(), "进入我的圈子页面", Toast.LENGTH_SHORT).show();

                break;
            case R.id.text_zuji:

                Intent intent2 = new Intent(getActivity(),MyFootActivity.class);
                startActivity(intent2);
                Toast.makeText(getActivity(), "进入我的足迹页面", Toast.LENGTH_SHORT).show();

                break;
            case R.id.text_qianbao:
                break;
            case R.id.text_dizhi:
                break;
        }
    }
}
