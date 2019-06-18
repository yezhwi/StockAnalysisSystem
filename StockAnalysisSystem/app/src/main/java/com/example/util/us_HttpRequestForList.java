package com.example.util;

import android.content.Context;
import android.util.Log;

import com.example.market_fragment.Bean.Stock;
import com.example.market_fragment.db.StockInfoDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class us_HttpRequestForList {
    static ArrayList<Stock>arrayList=new ArrayList<>();
    public static ArrayList<Stock>getAllStockList(Context context,String url1){
        try {
            //1.请求服务器获取股票数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            arrayList.clear();
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
                parseJSONWithJSONObject(context, string);
            }
            ;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void parseJSONWithJSONObject(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONObject jsonArray1 = root_json.getJSONObject("result");//获取root_json中的stocks作为jsonArray对象
            String string=jsonArray1.toString();

            Log.i("result",string);
            Log.d("totalCount:",jsonArray1.getString("totalCount"));
            Log.d("page:",jsonArray1.getString("page"));
            Log.d("num:",jsonArray1.getString("num"));

            JSONArray jsonArray=jsonArray1.getJSONArray("data");
            for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
                JSONObject stocks_json = jsonArray.getJSONObject(i);//获取一条新闻的json
                Stock stock = new Stock();
                stock.trade=stocks_json.getString("price");
                stock.symbol=stocks_json.getString("symbol");
                stock.name=stocks_json.getString("cname");
                stock.pricechange=stocks_json.getString("diff");
                stock.changepercent=stocks_json.getString("chg");
                arrayList.add(stock);

            }} catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }


    ////////////////获取单个股票信息
    static Stock mystock=new Stock();
    //public static ArrayList<Stock>getSomeStockList(Context context,String url1){
    public static Stock getSomeStockList(Context context,String url1){
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
        return mystock;
    }

    public static void parseJSONWithJSONObject1(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONArray jsonArray1=root_json.getJSONArray("result");
            String string=jsonArray1.toString();

            Log.i("result",string);

            JSONObject big_json = jsonArray1.getJSONObject(0);//获取一条股票的json

            JSONObject stocks_json =big_json.getJSONObject("data");
            String data=stocks_json.toString();
            Log.i("data",data);
            mystock.symbol=stocks_json.getString("gid");
            mystock.name=stocks_json.getString("name");
            mystock.trade=stocks_json.getString("lastestpri");//
            mystock.pricechange=stocks_json.getString("uppic");
            mystock.changepercent=stocks_json.getString("limit");
            String dateAndTime=stocks_json.getString("chtime");
            String arrays[] = dateAndTime.split(" ");
            mystock.date=arrays[0];
            mystock.time=arrays[1];
            mystock.open=stocks_json.getString("openpri");
            mystock.high=stocks_json.getString("maxpri");
            mystock.low=stocks_json.getString("minpri");
            mystock.close=stocks_json.getString("formpri");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }

    //将数据存入数据库
    static ArrayList<StockInfoDB>arrayList2=new ArrayList<>();
    public static ArrayList<StockInfoDB>getUpdateStockList(Context context,String url1){
        try {
            //1.请求服务器获取股票数据
            //获取一个url对象，通过url对象得到一个urlconnnection对象
            arrayList2.clear();
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
               // Log.i("Json数据", string);
                UpdateParseJSONWithJSONObject(context, string);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList2;
    }


    public static void  UpdateParseJSONWithJSONObject(Context context, String jsonData) {

        JSONObject root_json= null;
        try {
            root_json = new JSONObject(jsonData);
            JSONObject jsonArray2 = root_json.getJSONObject("result");//获取root_json中的newss作为jsonArray对象
            String string=jsonArray2.toString();
            Log.i("result",string);
            JSONArray jsonArray=jsonArray2.getJSONArray("data");
            for (int i = 0 ;i < jsonArray.length();i++){//循环遍历jsonArray
                JSONObject stocks_json = jsonArray.getJSONObject(i);//获取一条新闻的json
                StockInfoDB stockInfoDB=new StockInfoDB();
                /*
                *    gid;//json里的symbol,
                *    name;//json里的name，美股里的cname,
                *    now_Price;//trade，港股里的lasttrade，美股里的price
                    price_change;//pricechange，美股里的diff,
                    change_percent;//changepercent,美股里的chg
                    volume;//成交量*/
                stockInfoDB.gid=stocks_json.getString("symbol");
                stockInfoDB.now_Price=Double.parseDouble(stocks_json.getString("price"));
                stockInfoDB.name=stocks_json.getString("cname");
                stockInfoDB.price_change=Double.parseDouble(stocks_json.getString("diff"));
                stockInfoDB.change_percent=Double.parseDouble(stocks_json.getString("chg"));
                stockInfoDB.volume=Integer.parseInt(stocks_json.getString("volume"));
                arrayList2.add(stockInfoDB);
            }} catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将一个字符串封装成一个json对象。
    }

}
