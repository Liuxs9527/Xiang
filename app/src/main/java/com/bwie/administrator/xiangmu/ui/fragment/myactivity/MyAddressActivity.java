package com.bwie.administrator.xiangmu.ui.fragment.myactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bwie.administrator.xiangmu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAddressActivity extends AppCompatActivity {

    @BindView(R.id.my_address_recycleview)
    RecyclerView myAddressRecycleview;
    @BindView(R.id.my_increased_address)
    TextView myIncreasedAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
    }
}
