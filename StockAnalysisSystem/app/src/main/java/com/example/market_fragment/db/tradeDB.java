package com.example.market_fragment.db;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class tradeDB extends LitePalSupport implements Serializable {
   // private long id;
    public String gid;//沪股深股sh,sz,港股全是数字如00001,美股英文
    public String name;
    public String sum;//持仓
    public String buyPrice;//买的时候成本价
    public String lastPrice;//上次计算盈亏时的价格
    public String lastSum;//上次计算的盈亏

    public String getLastSum() {
        return lastSum;
    }

    public void setLastSum(String lastSum) {
        this.lastSum = lastSum;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

//    public String getNowPrice() {
//        return nowPrice;
//    }
//
//    public void setNowPrice(String nowPrice) {
//        this.nowPrice = nowPrice;
//    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

//    public static tradeDB findTradeByGId(String gid) {
//        List<tradeDB> trades = LitePal.where("gid = ?", gid).find(tradeDB.class);
//        if (trades == null || trades.size() == 0) {
//            return null;
//        } else {
//            for (int i = 1; i < trades.size(); i++) {
//                trades.get(i).delete();
//            }
//        }
//        return trades.get(0);
//    }
//
//    @Override
//    public boolean save() {
//        tradeDB t =findTradeByGId(gid);
//        if (t == null || t.id == 0) {//sqlite中自增id从1开始，0表示不存在
//            return super.save();
//        } else {
//            this.id = t.id;
//            return super.save();
//        }
//    }
}
