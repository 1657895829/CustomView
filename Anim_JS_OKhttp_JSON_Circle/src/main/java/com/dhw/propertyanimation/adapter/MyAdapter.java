package com.dhw.propertyanimation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhw.propertyanimation.R;
import com.dhw.propertyanimation.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsBean.DataBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private final int ZERO_TYPE = 0,ONE_TYPE = 1,TEO_TYPE = 2;


    public MyAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<GoodsBean.DataBean> data){
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    public void updateData(List<GoodsBean.DataBean> data){
        this.list.clear();
        addData(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder1 holder1;
        final ViewHolder2 holder2;
        final ViewHolder3 holder3;
        if (viewType == ZERO_TYPE) {
            final View view = inflater.inflate(R.layout.goods_item, parent, false);
            holder2 = new ViewHolder2(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRecyclerViewListener.setRecyclerViewListener(view,holder2.getLayoutPosition());
                }
            });
            return holder2;
        }else if (viewType == ONE_TYPE){
            final View view = inflater.inflate(R.layout.recycler_item1, parent, false);
            holder1 = new ViewHolder1(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRecyclerViewListener.setRecyclerViewListener(view,holder1.getLayoutPosition());
                }
            });
            return holder1;
        }
        final View view = inflater.inflate(R.layout.recycler_item2, parent, false);
        holder3 = new ViewHolder3(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecyclerViewListener.setRecyclerViewListener(view,holder3.getLayoutPosition());
            }
        });
        return holder3;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoodsBean.DataBean bean = list.get(position);
        String[] split = bean.images.split("\\|");
        if (holder instanceof ViewHolder2){
            Glide.with(context).load(split[0]).into(((ViewHolder2) holder).imageView);
            ((ViewHolder2) holder).textView.setText(bean.subhead);
        }
        if (holder instanceof ViewHolder1){
            ((ViewHolder1) holder).textView.setText(bean.title);
        }
        if (holder instanceof ViewHolder3){
            Glide.with(context).load(split[0]).into(((ViewHolder3) holder).img1);
            Glide.with(context).load(split[1]).into(((ViewHolder3) holder).img2);
            Glide.with(context).load(split[2]).into(((ViewHolder3) holder).img3);
            Glide.with(context).load(split[0]).into(((ViewHolder3) holder).img4);
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).itemtype == ONE_TYPE){
            return ONE_TYPE;
        }else if (list.get(position).itemtype == ZERO_TYPE){
            return ZERO_TYPE;
        }
        return TEO_TYPE;
    }



    public class ViewHolder1 extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder1(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.one_text);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{

        public TextView textView;
        public ImageView imageView;

        public ViewHolder2(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.zero_text);
            imageView = (ImageView)itemView.findViewById(R.id.zero_img);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder{

        public ImageView img1,img2,img3,img4;

        public ViewHolder3(View itemView) {
            super(itemView);
            img1 = (ImageView)itemView.findViewById(R.id.two_img1);
            img2 = (ImageView)itemView.findViewById(R.id.two_img2);
            img3 = (ImageView)itemView.findViewById(R.id.two_img3);
            img4 = (ImageView)itemView.findViewById(R.id.two_img4);
        }
    }

    private SetRecyclerViewListener setRecyclerViewListener;

    public void setListener(SetRecyclerViewListener setRecyclerViewListener){
        this.setRecyclerViewListener = setRecyclerViewListener;
    }

    public interface SetRecyclerViewListener{
        void setRecyclerViewListener(View view,int position);
    }
}
