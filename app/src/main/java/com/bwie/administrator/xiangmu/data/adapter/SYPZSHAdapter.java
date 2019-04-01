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

public class SYPZSHAdapter extends RecyclerView.Adapter<SYPZSHAdapter.ViewHolder> {


    private List<SYShopBean.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXs=new ArrayList<>();
    private Context mContext;

    public SYPZSHAdapter(Context Context) {

        this.mContext = Context;
    }

    public void setCommodityListBeanXs(List<SYShopBean.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanX) {
        this.commodityListBeanXs = commodityListBeanX;
        notifyDataSetChanged();
    }
    public int getPidx(int position){
        return commodityListBeanXs.get(position).getCommodityId();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(mContext, R.layout.pzsh_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        Glide.with(mContext).load(commodityListBeanXs.get(position).getMasterPic()).into(holder.sy_pzsh_shopimg);
        holder.sy_pzsh_shopprice.setText("￥:"+commodityListBeanXs.get(position).getPrice()+".00");
        holder.sy_pzsh_shopname.setText(commodityListBeanXs.get(position).getCommodityName()+"");
        holder.pzshLinearLayout.setOnClickListener(new View.OnClickListener() {
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
        return commodityListBeanXs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView sy_pzsh_shopimg;
        private final TextView sy_pzsh_shopname;
        private final TextView sy_pzsh_shopprice;
        private final LinearLayout pzshLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            sy_pzsh_shopimg = itemView.findViewById(R.id.sy_pzsh_shopimg);
            sy_pzsh_shopname = itemView.findViewById(R.id.sy_pzsh_shopname);
            sy_pzsh_shopprice = itemView.findViewById(R.id.sy_pzsh_shopprice);
            pzshLinearLayout = itemView.findViewById(R.id.pzshLinearLayout);

        }
    }
    public PZSHClickListenter listenter;

    public  void setOnPZSHClickListenter(PZSHClickListenter clickListenter){
        listenter=clickListenter;
    }


    public interface PZSHClickListenter{
        void onClick(int position);
    }
}
