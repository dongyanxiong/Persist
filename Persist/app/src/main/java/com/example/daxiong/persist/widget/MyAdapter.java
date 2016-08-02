package com.example.daxiong.persist.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daxiong.persist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/7/27.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext ;
    private ArrayList<GirlBean> list ;
    private LayoutInflater inflater;

    public MyAdapter(Context mContext, ArrayList<GirlBean> data) {
        this.mContext = mContext;
        this.list = data;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GirlBean girlBean = list.get(position);
        ViewHolder viewHolder = null ;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.girl_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.girl_img);
            //viewHolder.textView = (TextView) convertView.findViewById(R.id.girl_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       // viewHolder.textView.setText(girlBean.who);
        Glide.with(mContext)
                .load(girlBean.url)
                .error(R.drawable.ic_launcher)
                .placeholder(R.drawable.ic_launcher)
                .into(viewHolder.imageView);

        return convertView;
    }


//    static class AppHolder {
//        View itemView;
//
//        ImageView vImg;
//        TextView vName;
//        ImageButton vCollect;
//        DownLoadButton vDownload;
//        Button vInstall;
//    }

    static class ViewHolder{
        ImageView imageView ;
        TextView textView ;
    }

}
