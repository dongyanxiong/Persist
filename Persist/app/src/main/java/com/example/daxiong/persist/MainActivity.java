package com.example.daxiong.persist;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daxiong.persist.widget.GirlBean;
import com.example.daxiong.persist.widget.MyAdapter;
import com.example.daxiong.persist.widget.Recyce_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import libary.base.BaseFragment;
import libary.base.GestureActivity;

public class MainActivity extends GestureActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private String network_msg = "";
    private final int MESSAGE = 1;
    private int page = 1 ;
    private boolean tag = true ;
   // private ListView listView;
    private RecyclerView my_recycleview ;
    private Recyce_Adapter recyce_adapter ;
    private List<GirlBean> data = new ArrayList<>();
    private MyAdapter myAdapter;
    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE:
                    Log.d("dongtest","mHandler jie  shoudao   message!!!");
                      data.addAll((Collection<? extends GirlBean>) msg.obj);
                    Log.d("dongtest", "mHandler jie  here data = " + data.size());
                    page++;
                    recyce_adapter.notifyDataSetChanged();
                    tag = true ;
                    //myAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void doFinish() {

        Log.d(TAG, "调用了doFinish方法，");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
       // listView = (ListView) findViewById(R.id.list_item);
        my_recycleview = (RecyclerView) findViewById(R.id.my_recycleView);
       // final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //my_recycleview.setLayoutManager(linearLayoutManager);
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        my_recycleview.setLayoutManager(staggeredGridLayoutManager);
        my_recycleview.setAnimation(null);
        recyce_adapter = new Recyce_Adapter((ArrayList<GirlBean>) data,getApplicationContext());
        my_recycleview.setAdapter(recyce_adapter);



       // myAdapter = new MyAdapter(this, (ArrayList<GirlBean>) data);
       // listView.setAdapter(myAdapter);
        final NewWork network = new NewWork();
        new Thread(network).start();
        my_recycleview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int lastItem = linearLayoutManager.findLastVisibleItemPosition();
//                int total = linearLayoutManager.getItemCount();
                //int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
              //  position = getMaxPosition(lastPositions);
                int[] lastItem1 = staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
                Log.d("dongtest","here.size"+lastItem1.length);
                int lastItem = getMaxPosition(lastItem1);
                Log.d("dongtest","lastItem.=="+lastItem);

                int total = staggeredGridLayoutManager.getItemCount();
                if ((lastItem > total - 2) && dy > 0) {
                    Toast.makeText(MainActivity.this, "正在加载第" +(page)+
                            "页", Toast.LENGTH_SHORT).show();
                    if (tag){
                        tag = false;
                        new Thread(network).start();
                    }
                }
            }
        });
    }
    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    //network
    class NewWork implements Runnable {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            try {
                Log.d("DONGTEST","PAGE ========"+page);
                String dizhi = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/"+page;
                URL url = new URL(dizhi);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                Log.d(TAG, "NET_WORK.MSG ====="+page + response.toString());
                jsonparst(response.toString());
            } catch (Exception e) {
                e.printStackTrace();
                tag = true ;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }

    //jjson jie
    private void jsonparst(String res_json) {
        List<GirlBean> girl_list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(res_json);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            Log.d("dongtest","jsonObject=="+jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {

                GirlBean girlBean = new GirlBean();
                JSONObject array = (JSONObject) jsonArray.get(i);
                Log.d("dongtest======="+array.getString("url"),array.getString("who"));
                girlBean.setWho(array.getString("who"));
                girlBean.setUrl(array.getString("url"));
                girl_list.add(girlBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            tag = true ;
        } finally {
            if (girl_list != null && girl_list.size() > 0) {
                Message msg = handler.obtainMessage();
                msg.what = MESSAGE;
                msg.obj = girl_list;
                handler.sendMessage(msg);
            }else {
                tag = true ;
            }
        }
    }
}
