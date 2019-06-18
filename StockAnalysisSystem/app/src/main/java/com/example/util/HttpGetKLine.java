package com.example.util;

import android.content.Context;
import android.util.Log;

import com.example.market_fragment.Bean.KLineData;
import com.example.market_fragment.Bean.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetKLine {
    //获取单个股票KLine信息
    static KLineData kLineData=new KLineData();
    public static KLineData getKLineData (Context context, String url1){
        try {
            //1.请求服务器获取股票数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            //arrayList1.clear();
            URL url = new URL(url1);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的方式和超时时间
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            //获取请求响应码
            int code = connection.getResponseCode();
            if(code == 200){
                //获取请求到的流信息
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf_8"));
                StringBuilder response = new StringBuilder();
                String line;;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String string=response.toString();
                Log.i("Json数据", string);
                parseJSONWithJSONObject1(context, string);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return kLineData;
    }

    public static void parseJSONWithJSONObject1(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONArray jsonArray1=root_json.getJSONArray("result");
            String string=jsonArray1.toString();

            Log.i("result",string);

            JSONObject big_json = jsonArray1.getJSONObject(0);//获取一条股票的json

            JSONObject kline_json =big_json.getJSONObject("gopicture");
            String data=kline_json.toString();
            Log.i("data",data);
            kLineData.minurl=kline_json.getString("minurl");
            kLineData.dayurl=kline_json.getString("dayurl");
            kLineData.weekurl=kline_json.getString("weekurl");
            kLineData.monthurl=kline_json.getString("monthurl");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }
}
