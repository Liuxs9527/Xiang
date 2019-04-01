package com.bwie.administrator.xiangmu.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.administrator.xiangmu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BuyFragment extends Fragment {


    @BindView(R.id.all_list)
    TextView allList;
    @BindView(R.id.wait_for_pay)
    TextView waitForPay;
    @BindView(R.id.wait_for_receive)
    TextView waitForReceive;
    @BindView(R.id.wait_for_evaluate)
    TextView waitForEvaluate;
    @BindView(R.id.completed)
    TextView completed;
    @BindView(R.id.my_order_viewpager)
    ViewPager myOrderViewpager;
    Unbinder unbinder;
    @BindView(R.id.buy_fragment)
    LinearLayout buyFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buy, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.all_list, R.id.wait_for_pay, R.id.wait_for_receive, R.id.wait_for_evaluate, R.id.completed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_list:
                buyFragment.setBackgroundColor(Color.parseColor("#ccffff"));
                break;
            case R.id.wait_for_pay:
                buyFragment.setBackgroundColor(Color.parseColor("#ffffcc"));
                break;
            case R.id.wait_for_receive:
                buyFragment.setBackgroundColor(Color.parseColor("#ccffcc"));
                break;
            case R.id.wait_for_evaluate:
                buyFragment.setBackgroundColor(Color.parseColor("#E6E6FA"));
                break;
            case R.id.completed:
                buyFragment.setBackgroundColor(Color.parseColor("#FFE4E1"));
                break;
        }
    }
}
