package com.example.util;

import android.content.Context;
import android.util.Log;

import com.example.kLine_fragment.Bean.KModel;
import com.example.kLine_fragment.Bean.LineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpForList {
    static List<KModel> KDList =new ArrayList<>();
    //static List<LinkedHashMap<String, List<LineModel>>> Flist;
    static List<LineModel> MList =new ArrayList<>();

    //k线数据
    public static List<KModel> getAllStockList(Context context, String url1){
        try {
            //1.请求服务器获取股票数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            KDList.clear();
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));//UTF-8
                StringBuilder response = new StringBuilder();
                String line;;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String string=response.toString();
                Log.i("Json数据", string);
                parseJSONWithJSONObject(context, string);
                Log.i("KDList.size:",KDList.size()+"");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return KDList;
    }

    public static void parseJSONWithJSONObject(Context context, String jsonData) {

        String[] s1=jsonData.split("=");
        String s=s1[1];
        JSONObject root_json= null;
        try {
            //String s=new String(s2.getBytes("ISO-8859-1"),"UTF-8");
            //System.out.println("编码后:========="+borrowName);//正常
            root_json = new JSONObject(s);
            JSONArray jsonArray=root_json.getJSONArray("datas");
            Log.i("datas",jsonArray.toString());
            for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
                JSONArray jsonArray1=jsonArray.getJSONArray(i);
                //Log.i("jsonArray1",jsonArray1.toString());
                KModel stock = new KModel();
                stock.setTime(jsonArray1.getString(0));
                //Log.i("day", stock.day);
                stock.setPrice_o(Double.parseDouble(jsonArray1.getString(1)));
                stock.setPrice_c(Double.parseDouble(jsonArray1.getString(4)));
                stock.setPrice_l(Double.parseDouble(jsonArray1.getString(3)));
                stock.setPrice_h(Double.parseDouble(jsonArray1.getString(2)));
                stock.setVolume_price(jsonArray1.getString(8));
                stock.setZf_bfb(jsonArray1.getString(6));//9
                String a=jsonArray1.getString(7);
                String regEx="[^0-9 .]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(a);

                String v=m.replaceAll("").trim();
                String sy=a.replaceAll(v,"");
                Log.i("数字部分",m.replaceAll("").trim());
                Log.i("剩余部分",sy);
                float number= Float.parseFloat(m.replaceAll("").trim());
               // stock.setVolume((long)number*10000);
                if(sy.equalsIgnoreCase("万手")){
                    stock.setVolume((long)(number*10000));
                }else if(sy.equalsIgnoreCase("亿手")){
                    stock.setVolume((long)(number*100000000));
                }
                Log.i("volume", stock.getVolume()+"");

                KDList.add(stock);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }

    //分时数据
    public static List<LineModel> getAllStockMList(Context context, String url1){
        try {
            //1.请求服务器获取股票数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            MList.clear();
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));//UTF-8
                StringBuilder response = new StringBuilder();
                String line;;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String string=response.toString();
                Log.i("Json数据", string);
                parseJSONWithJSONObjectMlist(context, string);
                Log.i("MList.size:",MList.size()+"");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return MList;
    }

    public static void parseJSONWithJSONObjectMlist(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONArray jsonArray=root_json.getJSONArray("data");
            Log.i("data",jsonArray.toString());

            for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
                JSONArray jsonArray1=jsonArray.getJSONArray(i);
                LineModel stock = new LineModel();
                stock.setTime(jsonArray1.getString(0));
                Log.i("nowtime",jsonArray1.getString(0));
                stock.setPrice(Double.parseDouble(jsonArray1.getString(1)));
                stock.setVolume(Long.parseLong(jsonArray1.getString(3)));
                stock.setAverage(Double.parseDouble(jsonArray1.getString(2)));
                MList.add(stock);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }

}
