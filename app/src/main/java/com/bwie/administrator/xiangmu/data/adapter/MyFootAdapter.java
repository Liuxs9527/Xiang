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
import com.bwie.administrator.xiangmu.data.bean.MyFootBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyFootAdapter extends RecyclerView.Adapter<MyFootAdapter.ViewHolder> {

    private List<MyFootBean.ResultBean> list;
    private Context mContext;

    public MyFootAdapter(Context mContext) {
        this.list = new ArrayList<>();
        this.mContext = mContext;
    }

    public int getPid(int position) {
        return list.get(position).getCommodityId();
    }

    public void setList(List<MyFootBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.myfoot_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(mContext).load(list.get(position).getMasterPic()).into(holder.myfootimg);
        holder.myfoot_shopname.setText(list.get(position).getCommodityName());
        holder.myfoot_shopprice.setText("￥：" + list.get(position).getPrice());
        long createTime = list.get(position).getBrowseTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        holder.myfoottime.setText(simpleDateFormat.format(createTime));
        holder.myfootitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myfootlistenter != null) {
                    myfootlistenter.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView myfootimg;
        private final TextView myfoot_shopname;
        private final TextView myfoot_shopprice;
        private final TextView myfoottime;
        private final LinearLayout myfootitem;

        public ViewHolder(View itemView) {
            super(itemView);
            myfootimg = itemView.findViewById(R.id.myfoot_shopimg);
            myfoot_shopname = itemView.findViewById(R.id.myfoot_shopname);
            myfoot_shopprice = itemView.findViewById(R.id.myfoot_shopprice);
            myfoottime = itemView.findViewById(R.id.myfoot_time);
            myfootitem = itemView.findViewById(R.id.myfootitem);

        }
    }

    public MyFootListener myfootlistenter;

    public void setMyFootClickListener(MyFootListener footListener) {
        myfootlistenter = footListener;
    }


    public interface MyFootListener {
        void onClick(int position);
    }
}