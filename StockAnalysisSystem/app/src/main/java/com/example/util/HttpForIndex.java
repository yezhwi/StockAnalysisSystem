package com.example.util;

import android.content.Context;
import android.util.Log;

import com.example.market_fragment.Bean.IndexBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpForIndex {
    public static IndexBean indexBean = new IndexBean();
    public static IndexBean getSHIndexForNetWork(Context context, String Url) {
        try {
            //1.请求服务器获取新闻数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象

            URL url = new URL(Url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的方式和超时时间
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10 * 1000);
            //获取请求响应码
            int code = connection.getResponseCode();
            if (code == 200) {
                //获取请求到的流信息
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf_8"));
                StringBuilder response = new StringBuilder();
                String line;
                ;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String string = response.toString();
                Log.i("Json数据", string);
                parseJSONWithJSONObject(context, string);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return indexBean;
    }

    public static void parseJSONWithJSONObject(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONArray jsonArray1 = root_json.getJSONArray("result");//获取root_json中的newss作为jsonArray对象
            String string=jsonArray1.toString();

            Log.i("result1",string);
            JSONObject jsonArray=jsonArray1.getJSONObject(0);
            String js=jsonArray.toString();
            Log.i("shuju",js);

            JSONObject news_json = jsonArray.getJSONObject("data");
            indexBean.name=news_json.getString("name");
            indexBean.gid=news_json.getString("gid");
            indexBean.nowPrice=news_json.getString("nowPri");
            indexBean.changePrice=news_json.getString("increase");
            indexBean.changePercent = news_json.getString("increPer");
            indexBean.todayMax=news_json.getString("todayMax");
            indexBean.todayMin=news_json.getString("todayMin");
            indexBean.todayStartPri=news_json.getString("todayStartPri");
            indexBean.yestodEndPri=news_json.getString("yestodEndPri");
            indexBean.date = news_json.getString("date");
            indexBean.time=news_json.getString("time");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }

}
