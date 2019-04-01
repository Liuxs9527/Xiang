package com.bwie.administrator.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.adapter.ShangPinAdapter;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.ShopCarBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.ui.activity.YesActivity;
import com.bwie.administrator.xiangmu.ui.view.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingFragment extends Fragment implements Iview {

    @BindView(R.id.shopcarRecyclerview)
    RecyclerView shopcarRecyclerview;
    @BindView(R.id.shop_checkbox)
    CheckBox shopCheckbox;
    @BindView(R.id.layout_all)
    RelativeLayout layoutAll;
    @BindView(R.id.shop_price)
    TextView shopPrice;
    @BindView(R.id.sum_price_txt)
    TextView sumPriceTxt;
    @BindView(R.id.shop_sum_price)
    RelativeLayout shopSumPrice;
    Unbinder unbinder;
    private IpresenterImp ipresenter;
    private ShangPinAdapter shangPinAdapter;
    private ShopCarBean shopCarBean;
    private List<ShopCarBean.ResultBean> listAll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        unbinder = ButterKnife.bind(this, view);

        ipresenter = new IpresenterImp(this);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopcarRecyclerview.setLayoutManager(linearLayoutManager);
        shangPinAdapter = new ShangPinAdapter(getActivity());
        shopcarRecyclerview.setAdapter(shangPinAdapter);
        ipresenter.get(Api.ShopCarPath,ShopCarBean.class);

        /*view.findViewById(R.id.add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MyAddressActivity.class);
                startActivity(intent);
            }
        });*/
        shopcarRecyclerview.addItemDecoration(new SpaceItemDecoration(10));

        //购物车的多选框按钮
        shopCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shangPinAdapter.notifyDataSetChanged();
                checkSeller(shopCheckbox.isChecked());
            }
        });

        //跳转到确认订单页面
        sumPriceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),YesActivity.class);
                startActivity(intent);
            }
        });


        shangPinAdapter.setListener(new ShangPinAdapter.ShopCallBackListener() {
            @Override
            public void callBack() {
                double totalPrice=0;
                int num=0;
                int totalNum = 0;
                listAll = shopCarBean.getResult();
                for (int i = 0; i < listAll.size(); i++) {
                    totalNum = totalNum + listAll.get(i).getCount();
                    if (listAll.get(i).isCheck()) {
                        totalPrice = totalPrice + (listAll.get(i).getPrice() * listAll.get(i).getCount());
                        num = num + listAll.get(i).getCount();
                    }
                }
                if (num < totalNum) {
                    shopCheckbox.setChecked(false);
                } else {
                    shopCheckbox.setChecked(true);
                }
                shopPrice.setText("合计：" + totalPrice);
                sumPriceTxt.setText("去结算（" + num + ")");

            }
        });



        return view;
    }


    @Override
    public void onSuccessData(Object data) {
        if (data instanceof ShopCarBean){
            shopCarBean = (ShopCarBean) data;
            shangPinAdapter.setList(shopCarBean.getResult());
        }
    }

    @Override
    public void onFailData(Exception e) {

    }

    private void checkSeller(boolean bool){
        double totalPrice=0;
        int num=0;
        listAll = shopCarBean.getResult();
        for (int i = 0; i< listAll.size(); i++){
            listAll.get(i).setCheck(bool);
            totalPrice=totalPrice+(listAll.get(i).getPrice()* listAll.get(i).getCount());
            num=num+ listAll.get(i).getCount();
            shangPinAdapter.notifyDataSetChanged();
        }

        if (bool){
            shopPrice.setText("合计："+totalPrice);
            sumPriceTxt.setText("去结算（"+num+")");

        }else {

            shopPrice.setText("合计：0.00");
            sumPriceTxt.setText("去结算（0)");
        }
    }



}
