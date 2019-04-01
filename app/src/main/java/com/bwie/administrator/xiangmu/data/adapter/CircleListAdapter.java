package com.bwie.administrator.xiangmu.data.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.bean.CircleListBean;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CircleListAdapter extends RecyclerView.Adapter<CircleListAdapter.ViewHolder> {

    private List<CircleListBean.ResultBean> list;
    private Context mContext;

    public CircleListAdapter(Context mContext) {
        this.list = new ArrayList<>();
        this.mContext = mContext;
    }

    public void setList(List<CircleListBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.circle_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        GenericDraweeHierarchy tx=GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        holder.circle_user_txpicture.setHierarchy(tx);
        Uri pic = Uri.parse(list.get(position).getHeadPic());
        holder.circle_user_txpicture.setImageURI(pic);
        holder.circle_user_name.setText(list.get(position).getNickName());
        long createTime = list.get(position).getCreateTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        holder.circle_user_time.setText(simpleDateFormat.format(createTime));
        holder.circle_user_text.setText(list.get(position).getContent());

        GenericDraweeHierarchy zp=GenericDraweeHierarchyBuilder.newInstance(mContext.getResources())
                .build();
        holder.circle_user_picture.setHierarchy(zp);
        Uri uri = Uri.parse(list.get(position).getImage());
        holder.circle_user_picture.setImageURI(uri);
        if (list.get(position).getWhetherGreat()==1){
            holder.dianzan.setButtonDrawable(R.drawable.common_btn_prise_s_hdpi);

        }else if (list.get(position).getWhetherGreat()==2) {
            holder.dianzan.setButtonDrawable(R.drawable.common_btn_prise_n_hdpi);
        }
        holder.dznum.setText(list.get(position).getGreatNum()+"");
        holder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dzlistenter!=null){
                    dzlistenter.onClick(list.get(position).getWhetherGreat(),position,list.get(position).getId());
                }
            }
        });
    }
    //进行点赞
    public void Dianzan(int position) {
        list.get(position).setGreatNum(list.get(position).getGreatNum()+1);
        list.get(position).setWhetherGreat(1);
        notifyDataSetChanged();
    }
    //取消点赞
    public void UnDaianzan(int position) {
        list.get(position).setWhetherGreat(2);
        list.get(position).setGreatNum(list.get(position).getGreatNum()-1);
        notifyDataSetChanged();
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
        private final CheckBox dianzan;
        private final TextView dznum;

        public ViewHolder(View itemView) {
            super(itemView);
            circle_user_txpicture = itemView.findViewById(R.id.circle_user_txpicture);
            circle_user_name = itemView.findViewById(R.id.circle_user_name);
            circle_user_time = itemView.findViewById(R.id.circle_user_time);
            circle_user_text = itemView.findViewById(R.id.circle_user_text);
            circle_user_picture = itemView.findViewById(R.id.circle_user_picture);
            dianzan = itemView.findViewById(R.id.circle_dianzan_img);
            dznum = itemView.findViewById(R.id.circle_dianzan_num);

        }
    }
    public DZClickListenter  dzlistenter;

    public  void setOnDZClickListenter(DZClickListenter dzclickListenter){
        dzlistenter=dzclickListenter;
    }


    public interface DZClickListenter{
        void onClick(int WhetherGreat, int position,int id);
    }

}

