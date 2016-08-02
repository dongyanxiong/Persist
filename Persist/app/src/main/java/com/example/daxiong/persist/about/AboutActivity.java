package com.example.daxiong.persist.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daxiong.persist.R;
import com.example.daxiong.persist.widget.CircleImageView;

import java.io.File;


public class AboutActivity extends AppCompatActivity {

    private static String backUrl = "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=github&step_word=&pn=2&spn=0&di=48206679950&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2954802999%2C248096967&os=3077838925%2C3392037641&simid=4197245739%2C808913872&adpicid=0&ln=1000&fr=&fmq=1469363681613_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=10&oriquery=&objurl=http%3A%2F%2Fwww.myexception.cn%2Fimg%2F2013%2F01%2F16%2F1026021639.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4yjxvjrpt5g_z%26e3BvgAzdH3Fotg15ofAzdH3F88mmcdd_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0";
    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";

    private ImageView imageView ;
    private CircleImageView user_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        imageView = (ImageView) findViewById(R.id.backdrop);
        user_img = (CircleImageView) findViewById(R.id.user_img);
        initView();
    }

    private void initView() {
        File file = new File("mnt/shell/emulated/0/DCIM/Camera/IMG.jpg");
        Glide.with(AboutActivity.this).load(currentGirl).into(imageView);
        Glide.with(AboutActivity.this).load(currentGirl).into(user_img);

    }
}
