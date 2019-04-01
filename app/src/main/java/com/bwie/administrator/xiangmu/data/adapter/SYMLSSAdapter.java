package com.bwie.administrator.xiangmu.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.bean.SYShopBean;

import java.util.ArrayList;
import java.util.List;

public class SYMLSSAdapter extends RecyclerView.Adapter<SYMLSSAdapter.ViewHolder> {


    private List<SYShopBean.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXs=new ArrayList<>();
    private Context mContext;

    public SYMLSSAdapter(Context Context) {
        this.mContext = Context;
    }
    public int getPidxx(int position){
        return commodityListBeanXXs.get(position).getCommodityId();
    }


    public void setCommodityListBeanXX(List<SYShopBean.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXX) {
        this.commodityListBeanXXs = commodityListBeanXX;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.mlss_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        Glide.with(mContext).load(commodityListBeanXXs.get(position).getMasterPic()).into(holder.sy_mlss_shopimg);
        holder.sy_mlss_shopprice.setText("￥:"+commodityListBeanXXs.get(position).getPrice()+".00");
        holder.sy_mlss_shopname.setText(commodityListBeanXXs.get(position).getCommodityName()+"");
        holder.mlssLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenter!=null){
                    listenter.onClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return commodityListBeanXXs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView sy_mlss_shopimg;
        private final TextView sy_mlss_shopname;
        private final TextView sy_mlss_shopprice;
        private final LinearLayout mlssLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            sy_mlss_shopimg = itemView.findViewById(R.id.sy_mlss_shopimg);
            sy_mlss_shopname = itemView.findViewById(R.id.sy_mlss_shopname);
            sy_mlss_shopprice = itemView.findViewById(R.id.sy_mlss_shopprice);
            mlssLinearLayout = itemView.findViewById(R.id.mlssLinearLayout);
        }
    }
    public MLSSClickListenter listenter;

    public  void setOnMLSSClickListenter(MLSSClickListenter clickListenter){
        listenter=clickListenter;
    }


    public interface MLSSClickListenter{
        void onClick(int position);
    }
}
