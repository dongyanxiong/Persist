package com.example.daxiong.persist.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daxiong.persist.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/28.
 */
public class Recyce_Adapter extends RecyclerView.Adapter<Recyce_Adapter.myViewHolder> {

    private Context mContext ;
    private ArrayList<GirlBean> list ;
    private LayoutInflater inflater;

    public Recyce_Adapter(ArrayList<GirlBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.girl_item,parent,false);
        myViewHolder vh = new myViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

       // holder.textView.setText(list.get(position).who);
        Glide.with(mContext).load(list.get(position).url)
                .placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView ;
        public myViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.girl_img);
            //textView = (TextView) view.findViewById(R.id.girl_text);
        }
    }

}
