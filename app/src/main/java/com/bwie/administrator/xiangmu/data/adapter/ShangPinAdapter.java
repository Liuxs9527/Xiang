package com.bwie.administrator.xiangmu.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.administrator.xiangmu.R;
import com.bwie.administrator.xiangmu.data.bean.ShopCarBean;
import com.bwie.administrator.xiangmu.ui.view.JiaJianView;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

public class ShangPinAdapter extends RecyclerView.Adapter<ShangPinAdapter.ViewHolder> {

    private Context context;
    private List<ShopCarBean.ResultBean> list = new ArrayList<>();

    public ShangPinAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ShopCarBean.ResultBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sp_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String pic = list.get(position).getPic();
        Glide.with(context).load(pic).into(holder.sp_img);
        holder.sp_name.setText(list.get(position).getCommodityName());
        holder.sp_price.setText("￥:"+list.get(position).getPrice());

        holder.sp_check.setChecked(list.get(position).isCheck());
        holder.sp_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setCheck(isChecked);
                if (mshopCallBackListener != null) {
                    mshopCallBackListener.callBack();
                }
                if (selOrDelSPListener != null) {
                    selOrDelSPListener.selordelsp(list);
                }
            }
        });
        holder.jiaJianView.setData(this, list, position);
        holder.jiaJianView.setOnJiaJian(new JiaJianView.JiajianListener() {
            @Override
            public void jiajian() {
                if (mshopCallBackListener != null) {
                    mshopCallBackListener.callBack();

                }

            }

        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox sp_check;
        private final ImageView sp_img;
        private final TextView sp_name;
        private final TextView sp_price;
        private final JiaJianView jiaJianView;
        private final SwipeLayout swipeLayout;
        private final TextView del;

        public ViewHolder(View itemView) {
            super(itemView);
            sp_check = itemView.findViewById(R.id.sp_check);
            sp_img = itemView.findViewById(R.id.sp_img);
            sp_name = itemView.findViewById(R.id.sp_name);
            sp_price = itemView.findViewById(R.id.sp_price);
            jiaJianView = itemView.findViewById(R.id.jiajianView);
            swipeLayout = itemView.findViewById(R.id.swipe);
            del = itemView.findViewById(R.id.del);
        }
    }

    public void selOrdelAll(boolean isSelectAll) {
        for (ShopCarBean.ResultBean resultList : list) {
            resultList.setCheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

    private ShopCallBackListener mshopCallBackListener;

    public void setListener(ShopCallBackListener listeners) {
        this.mshopCallBackListener = listeners;
    }

    public interface ShopCallBackListener {
        void callBack();
    }

    private SelOrDelSPListener selOrDelSPListener;

    public void setSelOrDelSPListener(SelOrDelSPListener selOrDelSPListener) {
        this.selOrDelSPListener = selOrDelSPListener;
    }

    public interface SelOrDelSPListener {
        void selordelsp(List<ShopCarBean.ResultBean> list);
    }

    private ItemClick click;

    public void setClick(ItemClick clicks) {
        this.click = clicks;
    }

    public interface ItemClick {
        void onClick(int position);
    }
}