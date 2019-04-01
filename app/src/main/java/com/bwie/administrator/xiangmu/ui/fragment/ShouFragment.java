package com.bwie.administrator.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.adapter.ByKeyWordAdapter;
import com.bwie.administrator.xiangmu.data.adapter.MoreAdapter;
import com.bwie.administrator.xiangmu.data.adapter.SYMLSSAdapter;
import com.bwie.administrator.xiangmu.data.adapter.SYPZSHAdapter;
import com.bwie.administrator.xiangmu.data.adapter.SYRXXPAdapter;
import com.bwie.administrator.xiangmu.data.api.Api;
import com.bwie.administrator.xiangmu.data.bean.ByKeywordBean;
import com.bwie.administrator.xiangmu.data.bean.ClickMoreBean;
import com.bwie.administrator.xiangmu.data.bean.HomeXBannerBean;
import com.bwie.administrator.xiangmu.data.bean.SYShopBean;
import com.bwie.administrator.xiangmu.di.presenter.IpresenterImp;
import com.bwie.administrator.xiangmu.di.view.Iview;
import com.bwie.administrator.xiangmu.ui.activity.ParticularsActivity;
import com.bwie.administrator.xiangmu.ui.view.SpaceItemDecoration;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShouFragment extends Fragment implements Iview {

    @BindView(R.id.sy_menu)
    ImageView syMenu;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.sy_sousuo)
    ImageView sySousuo;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.rxmore)
    ImageView rxmore;
    @BindView(R.id.rxxprecycleView)
    RecyclerView rxxprecycleView;
    @BindView(R.id.mlmore)
    ImageView mlmore;
    @BindView(R.id.mlssrecycleView)
    RecyclerView mlssrecycleView;
    @BindView(R.id.pzmore)
    ImageView pzmore;
    @BindView(R.id.pzshRecycleView)
    RecyclerView pzshRecycleView;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.moreRecycle)
    RecyclerView moreRecycle;
    @BindView(R.id.morelinear)
    LinearLayout morelinear;
    Unbinder unbinder;

    private int xpnum = 3;
    private int pznum = 2;
    private SYMLSSAdapter symlssAdapter;
    private SYPZSHAdapter sypzshAdapter;
    private SYRXXPAdapter syrxxpAdapter;
    private MoreAdapter moreAdapter;
    private ByKeyWordAdapter byKeyWordAdapter;
    private IpresenterImp ipresenter;
    private String ed;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shou, container, false);

        unbinder = ButterKnife.bind(this, view);

        ipresenter = new IpresenterImp(this);
        ipresenter.getXBanner(Api.XBannerPath,HomeXBannerBean.class);
        initSYRXXPData();
        initSYMLSSData();
        initSYPZSHData();
        rxmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMoreData(1002);
            }
        });
        mlmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMoreData(1003);
            }
        });
        pzmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMoreData(1004);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                morelinear.setVisibility(View.GONE);

                edit.getText().clear();
            }
        });
        sySousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed = edit.getText().toString();

                initByKeyWordData();


            }
        });


        return view;
    }

    private void initByKeyWordData() {
        scrollView.setVisibility(View.GONE);
        morelinear.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),pznum);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        moreRecycle.setLayoutManager(gridLayoutManager);
        Log.e("cxy",edit.getText().toString());
        ipresenter.getXBanner(Api.ByKeywordPath+"?keyword="+ed+"&page=1&count=10",ByKeywordBean.class);
        byKeyWordAdapter = new ByKeyWordAdapter(getContext());
        moreRecycle.addItemDecoration(new SpaceItemDecoration(5));
        moreRecycle.setAdapter(byKeyWordAdapter);
        byKeyWordAdapter.setOnByKeyWordClickListenter(new ByKeyWordAdapter.ByKeyWordClickListenter() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity().getApplicationContext(),ParticularsActivity.class);
                int pid = byKeyWordAdapter.getPid(position);
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

    }

    private void initMoreData(int num) {
        scrollView.setVisibility(View.GONE);
        morelinear.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),pznum);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        moreRecycle.setLayoutManager(gridLayoutManager);
        ipresenter.getXBanner(Api.MoreShopPath+"?labelId="+num+"&page=1&count=10",ClickMoreBean.class);
        moreAdapter = new MoreAdapter(getContext());
        moreRecycle.addItemDecoration(new SpaceItemDecoration(10));
        moreRecycle.setAdapter(moreAdapter);
        moreAdapter.setOnMoreClickListenter(new MoreAdapter.MoreClickListenter() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity().getApplicationContext(),ParticularsActivity.class);
                int pid = moreAdapter.getPid(position);
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    private void initSYRXXPData() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),xpnum);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rxxprecycleView.setLayoutManager(gridLayoutManager);
        ipresenter.getXBanner(Api.SPPath,SYShopBean.class);
        syrxxpAdapter = new SYRXXPAdapter(getContext());
        rxxprecycleView.addItemDecoration(new SpaceItemDecoration(20));
        rxxprecycleView.setAdapter(syrxxpAdapter);
        syrxxpAdapter.setOnRXXPClickListenter(new SYRXXPAdapter.RXXPClickListenter() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity().getApplicationContext(),ParticularsActivity.class);
                int pid = syrxxpAdapter.getPid(position);
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });




    }
    private void initSYMLSSData() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mlssrecycleView.setLayoutManager(linearLayoutManager);
        ipresenter.getXBanner(Api.SPPath,SYShopBean.class);
        symlssAdapter = new SYMLSSAdapter(getContext());
        mlssrecycleView.addItemDecoration(new SpaceItemDecoration(20));
        mlssrecycleView.setAdapter(symlssAdapter);
        symlssAdapter.setOnMLSSClickListenter(new SYMLSSAdapter.MLSSClickListenter() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity().getApplicationContext(),ParticularsActivity.class);
                int pid = symlssAdapter.getPidxx(position);
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }
    private void initSYPZSHData() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),pznum);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        pzshRecycleView.setLayoutManager(gridLayoutManager);
        ipresenter.getXBanner(Api.SPPath,SYShopBean.class);
        sypzshAdapter = new SYPZSHAdapter(getContext());
        pzshRecycleView.addItemDecoration(new SpaceItemDecoration(20));
        pzshRecycleView.setAdapter(sypzshAdapter);
        sypzshAdapter.setOnPZSHClickListenter(new SYPZSHAdapter.PZSHClickListenter() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity().getApplicationContext(),ParticularsActivity.class);
                int pid = sypzshAdapter.getPidx(position);
                Bundle bundle=new Bundle();
                bundle.putString("pid",pid+"");
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccessData(Object data) {
        if (data instanceof SYShopBean){
            SYShopBean bean= (SYShopBean) data;
            syrxxpAdapter.setCommodityListBeans(bean.getResult().getRxxp().getCommodityList());
            symlssAdapter.setCommodityListBeanXX(bean.getResult().getMlss().getCommodityList());
            sypzshAdapter.setCommodityListBeanXs(bean.getResult().getPzsh().getCommodityList());

        }else if (data instanceof ClickMoreBean){
            ClickMoreBean clickMoreBean= (ClickMoreBean) data;
            moreAdapter.setResultBeans(clickMoreBean.getResult());
        }else if (data instanceof ByKeywordBean){
            ByKeywordBean byKeywordBean= (ByKeywordBean) data;
            List<ByKeywordBean.ResultBean> result = byKeywordBean.getResult();

            if(result.size()==0){
                Toast.makeText(getActivity(),"没有您想要的内容",Toast.LENGTH_SHORT).show();

            }else {
                byKeyWordAdapter.setResultBeans(byKeywordBean.getResult());

            }

        }else if (data instanceof HomeXBannerBean){
            HomeXBannerBean bannerBean = (HomeXBannerBean) data;
            //为XBanner绑定数据
            xbanner.setData(bannerBean.getResult(),null);
            //XBanner适配数据
            xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    HomeXBannerBean.ResultBean bannerbean= (HomeXBannerBean.ResultBean) model;
                    Glide.with(getActivity()).load(bannerbean.getImageUrl()).into((ImageView) view);
                    banner.setPageChangeDuration(1000);

                }
            });
        }
    }

    @Override
    public void onFailData(Exception e) {
        ipresenter.detach();
    }
}
