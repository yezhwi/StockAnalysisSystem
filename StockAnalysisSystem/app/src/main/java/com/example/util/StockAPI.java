package com.example.util;

public class StockAPI {
    //URL
    public static final String HOST = "http://web.juhe.cn:8080/finance/stock/";
    //配置申请的KEY
    public static final String APPKEY = "ffe25c429cae68e20c305114f98dff43";
    //主用ffe25c429cae68e20c305114f98dff43
    //备用ba4122b2ca9daa72ee8df4b31e682608
    //1.获取沪深股市数据
    public static String getHS(String gid) {

        String url = HOST + "hs";
        return url  + "?gid=" + gid+"&key=" + APPKEY ;

    }
    //2.香港股市
    public static String getHK(String gid) {

        String url = HOST + "hk";
        return url + "?num="+ gid +"&key=" + APPKEY;
    }

    //3.美国股市
    public static String getUSA(String gid) {
        String url = HOST + "usa";
        return url + "?gid=" + gid+"&key=" + APPKEY ;

    }

    //4.获取香港股市列表数据
    public static String getHKAll(int page) {

        String url = HOST + "hkall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;
    }

    //5.获取美国股市列表数据
    public static String  getUSAAll(int page) {

        String url = HOST + "usaall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;

    }

    //6.获取深圳股市列表数据
    public static String getSGAll(int page) {
        String url = HOST + "szall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;
    }

    //7.获取沪股股市列表数据
    public static String getHGAll(int page) {
        String url = HOST + "shall";
        return url + "?type=2&key=" + APPKEY + "&page=" + page;
    }

    //沪股网易日数据
    public static String getMhg(String gid){
        String head="http://img1.money.126.net/data/hs/time/today/0";
        String end=".json";
        return head+gid+end;
    }
    //深股网易日数据
    public static String getMsg(String gid){
        String head="http://img1.money.126.net/data/hs/time/today/1";
        String end=".json";
        return head+gid+end;
    }
    //港股网易日数据
//    String getMhk(String gid){
//        String head="http://img1.money.126.net/data/hk/time/today/";
//        String end=".json";
//        return head+gid+end;
//    }

    //证券之星日数据
    //http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_000001_1_6
    public static String gethgKday(String gid){
        String head="http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_";
        String end="_1_6";
        return head+gid+end;
    }
    public static String getsgKday(String gid){
        String head="http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_";
        String end="_2_6";
        return head+gid+end;
    }
    //证券之星周数据
    //http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_000001_1_6
    public static String gethgKweek(String gid){
        String head="http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_";
        String end="_1_7";
        return head+gid+end;
    }
    public static String getsgKweek(String gid){
        String head="http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_";
        String end="_2_7";
        return head+gid+end;
    }
    //证券之星月数据
    //http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_000001_1_6
    public static String gethgKmonth(String gid){
        String head="http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_";
        String end="_1_8";
        return head+gid+end;
    }
    public static String getsgKmonth(String gid){
        String head="http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_";
        String end="_2_8";
        return head+gid+end;
    }
}
