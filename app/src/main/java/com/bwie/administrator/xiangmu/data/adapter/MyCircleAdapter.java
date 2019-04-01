package com.bwie.administrator.xiangmu.data.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.bean.MyCircleBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyCircleAdapter  extends RecyclerView.Adapter<MyCircleAdapter.ViewHolder> {

    private List<MyCircleBean.ResultBean> list;
    private Context mContext;

    public MyCircleAdapter(Context mContext) {
        this.list = new ArrayList<>();
        this.mContext = mContext;
    }

    public int getPid(int position) {
        return list.get(position).getCommodityId();
    }

    public void setList(List<MyCircleBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void delList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.mycircle_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        GenericDraweeHierarchy tx = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        holder.circle_user_txpicture.setHierarchy(tx);
        Uri pic = Uri.parse(list.get(position).getHeadPic());
        holder.circle_user_txpicture.setImageURI(pic);

        holder.circle_user_name.setText(list.get(position).getNickName());
        long createTime = list.get(position).getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        holder.circle_user_time.setText(simpleDateFormat.format(createTime));
        holder.circle_user_text.setText(list.get(position).getContent());

        GenericDraweeHierarchy zp = GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                .build();
        holder.circle_user_picture.setHierarchy(zp);
        Uri uri = Uri.parse(list.get(position).getImage());
        holder.circle_user_picture.setImageURI(uri);
        holder.del_my_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCircleListenter != null) {
                    myCircleListenter.onClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView circle_user_txpicture;
        private final TextView circle_user_name;
        private final TextView circle_user_time;
        private final TextView circle_user_text;
        private final SimpleDraweeView circle_user_picture;
        private final LinearLayout mycircle;
        private final ImageView del_my_circle;

        public ViewHolder(View itemView) {
            super(itemView);
            circle_user_txpicture = itemView.findViewById(R.id.mycircle_user_txpicture);
            circle_user_name = itemView.findViewById(R.id.mycircle_user_name);
            circle_user_time = itemView.findViewById(R.id.mycircle_user_time);
            circle_user_text = itemView.findViewById(R.id.mycircle_user_text);
            circle_user_picture = itemView.findViewById(R.id.mycircle_user_picture);
            mycircle = itemView.findViewById(R.id.mycircle);
            del_my_circle = itemView.findViewById(R.id.del_my_circle);
        }
    }

    public MyCircleListener myCircleListenter;

    public void setMyCircleListenter(MyCircleListener circleListenter) {
        myCircleListenter = circleListenter;
    }


    public interface MyCircleListener {
        void onClick(int position);
    }
}
