package com.bwie.administrator.xiangmu.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.adapter.FragmentAdapter;
import com.bwie.administrator.xiangmu.ui.fragment.BuyFragment;
import com.bwie.administrator.xiangmu.ui.fragment.CircleFragment;
import com.bwie.administrator.xiangmu.ui.fragment.MyFragment;
import com.bwie.administrator.xiangmu.ui.fragment.ShoppingFragment;
import com.bwie.administrator.xiangmu.ui.fragment.ShouFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.home_button_home)
    RadioButton homeButtonHome;
    @BindView(R.id.home_button_circle)
    RadioButton homeButtonCircle;
    @BindView(R.id.home_button_shop)
    RadioButton homeButtonShop;
    @BindView(R.id.home_button_bill)
    RadioButton homeButtonBill;
    @BindView(R.id.home_button_my)
    RadioButton homeButtonMy;
    @BindView(R.id.rads)
    RadioGroup rads;
    private List<Fragment> fragments;
    private Context context;
    private BuyFragment buyFragment;
    private CircleFragment circleFragment;
    private MyFragment myFragment;
    private ShoppingFragment shoppingFragment;
    private ShouFragment shouFragment;
    private FragmentAdapter fragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        rads.check(rads.getChildAt(0).getId());

        buyFragment = new BuyFragment();
        circleFragment = new CircleFragment();
        myFragment = new MyFragment();
        shoppingFragment = new ShoppingFragment();
        shouFragment = new ShouFragment();

        fragments = new ArrayList<Fragment>();
        fragments.add(shouFragment);
        fragments.add(circleFragment);
        fragments.add(shoppingFragment);
        fragments.add(buyFragment);
        fragments.add(myFragment);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(fragmentAdapter);

        viewpager.setOnPageChangeListener(this);
        rads.setOnCheckedChangeListener(this);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                rads.check(R.id.home_button_home);
                break;
            case 1:
                rads.check(R.id.home_button_circle);
                break;

            case 2:
                rads.check(R.id.home_button_shop);
                break;

            case 3:
                rads.check(R.id.home_button_bill);
                break;

            case 4:
                rads.check(R.id.home_button_my);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){

                case R.id.home_button_home:
                    viewpager.setCurrentItem(0);
                    break;

                case R.id.home_button_circle:
                    viewpager.setCurrentItem(1);
                    break;

                case R.id.home_button_shop:
                    viewpager.setCurrentItem(2);
                    break;

                case R.id.home_button_bill:
                    viewpager.setCurrentItem(3);
                    break;

                case R.id.home_button_my:
                    viewpager.setCurrentItem(4);
                    break;



            }
    }
}
