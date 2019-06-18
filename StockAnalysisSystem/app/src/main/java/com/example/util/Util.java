package com.example.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.kLine_fragment.Bean.KModel;
import com.example.kLine_fragment.Bean.LineModel;
import com.vinsonguo.klinelib.model.HisData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import com.example.util.StockAPI;

/**
 * 数据解析的示例，数据来自于R.raw.his_data的json
 * Created by guoziwei on 2017/11/23.
 */

public class Util {

    private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.getDefault());
    private static SimpleDateFormat sFormat1 = new SimpleDateFormat("HHmm", Locale.getDefault());
    private static SimpleDateFormat sFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static SimpleDateFormat sFormat3 = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
    static List<KModel> klist=new List<KModel>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<KModel> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] ts) {
            return null;
        }

        @Override
        public boolean add(KModel kModel) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends KModel> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends KModel> collection) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public KModel get(int i) {
            return null;
        }

        @Override
        public KModel set(int i, KModel kModel) {
            return null;
        }

        @Override
        public void add(int i, KModel kModel) {

        }

        @Override
        public KModel remove(int i) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<KModel> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<KModel> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<KModel> subList(int i, int i1) {
            return null;
        }
    };
    static List<LineModel> Mlist=new List<LineModel>() {
       @Override
       public int size() {
           return 0;
       }

       @Override
       public boolean isEmpty() {
           return false;
       }

       @Override
       public boolean contains(Object o) {
           return false;
       }

       @Override
       public Iterator<LineModel> iterator() {
           return null;
       }

       @Override
       public Object[] toArray() {
           return new Object[0];
       }

       @Override
       public <T> T[] toArray(T[] ts) {
           return null;
       }

       @Override
       public boolean add(LineModel lineModel) {
           return false;
       }

       @Override
       public boolean remove(Object o) {
           return false;
       }

       @Override
       public boolean containsAll(Collection<?> collection) {
           return false;
       }

       @Override
       public boolean addAll(Collection<? extends LineModel> collection) {
           return false;
       }

       @Override
       public boolean addAll(int i, @NonNull Collection<? extends LineModel> collection) {
           return false;
       }

       @Override
       public boolean removeAll(Collection<?> collection) {
           return false;
       }

       @Override
       public boolean retainAll(Collection<?> collection) {
           return false;
       }

       @Override
       public void clear() {

       }

       @Override
       public LineModel get(int i) {
           return null;
       }

       @Override
       public LineModel set(int i, LineModel lineModel) {
           return null;
       }

       @Override
       public void add(int i, LineModel lineModel) {

       }

       @Override
       public LineModel remove(int i) {
           return null;
       }

       @Override
       public int indexOf(Object o) {
           return 0;
       }

       @Override
       public int lastIndexOf(Object o) {
           return 0;
       }

       @NonNull
       @Override
       public ListIterator<LineModel> listIterator() {
           return null;
       }

       @NonNull
       @Override
       public ListIterator<LineModel> listIterator(int i) {
           return null;
       }

       @NonNull
       @Override
       public List<LineModel> subList(int i, int i1) {
           return null;
       }
   };



    public static List<HisData> get1Day(final Context context,final String gid) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //请求网络数据
                    //Mlist = HttpForList.getAllStockMList(context,"http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_600000_1_1");
                    //网易的数据，上海股票前加0，如600756变成0600756，深圳股票前加1
                    String s=gid.substring(0,2);
                    String id=gid.substring(2,gid.length());
                    if(s.equalsIgnoreCase("sh")) {
                        Mlist=HttpForList.getAllStockMList(context,StockAPI.getMhg(id));
                    }else  if(s.equalsIgnoreCase("sz")) {
                        Mlist=HttpForList.getAllStockMList(context,StockAPI.getMsg(id));
                    }
                    //Mlist = HttpForList.getAllStockMList(context,"http://img1.money.126.net/data/hs/time/today/0600000.json");
                    countDownLatch.countDown();
                }
            }).start();
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        List<HisData> hisData = new ArrayList<>(100);
        for (int i = 0; i < Mlist.size(); i++) {
            LineModel m = Mlist.get(i);
            HisData data = new HisData();
            data.setClose(m.getPrice());
            data.setVol(m.getVolume());
            data.setOpen(i == 0 ? 0 : Mlist.get(i - 1).getPrice());
            try {
                data.setDate(sFormat1.parse(m.getTime()).getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
            hisData.add(data);
        }
        return hisData;
    }


    public static List<HisData> getK(final Context context, int day,final String gid) {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String s=gid.substring(0,2);
        final String id=gid.substring(2,gid.length());
        if(day==1){
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        //klist = HttpForList.getAllStockList(context,"http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_600000_1_6");
                        if(s.equalsIgnoreCase("sh")) {
                            klist=HttpForList.getAllStockList(context,StockAPI.gethgKday(id));
                        }else  if(s.equalsIgnoreCase("sz")) {
                            klist=HttpForList.getAllStockList(context,StockAPI.getsgKday(id));
                        }
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(day==7){
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        if(s.equalsIgnoreCase("sh")) {
                            klist=HttpForList.getAllStockList(context,StockAPI.gethgKweek(id));
                        }else  if(s.equalsIgnoreCase("sz")) {
                            klist=HttpForList.getAllStockList(context,StockAPI.getsgKweek(id));
                        }
                       // klist = HttpForList.getAllStockList(context,"http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_600000_1_7");
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(day==30){
            try{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //请求网络数据
                        if(s.equalsIgnoreCase("sh")) {
                            klist=HttpForList.getAllStockList(context,StockAPI.gethgKmonth(id));
                        }else  if(s.equalsIgnoreCase("sz")) {
                            klist=HttpForList.getAllStockList(context,StockAPI.getsgKmonth(id));
                        }
                       // klist = HttpForList.getAllStockList(context,"http://cq.ssajax.cn/interact/getTradedata.ashx?pic=qlpic_600000_1_8");
                        countDownLatch.countDown();
                    }
                }).start();
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        List<HisData> hisData = new ArrayList<>(100);
        for (int i = 0; i < klist.size(); i++) {
            KModel m = klist.get(i);
            HisData data = new HisData();
            data.setClose(m.getPrice_c());
            data.setOpen(m.getPrice_o());
            data.setHigh(m.getPrice_h());
            data.setLow(m.getPrice_l());
            data.setVol(m.getVolume());
            try {
                data.setDate(sFormat2.parse(m.getTime()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            hisData.add(data);
        }
        return hisData;
    }
}
