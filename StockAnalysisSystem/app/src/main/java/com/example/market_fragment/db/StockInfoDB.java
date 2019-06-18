package com.example.market_fragment.db;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class StockInfoDB extends LitePalSupport implements Serializable {
    public String gid;//json里的symbol
    public String name;//json里的name，美股里的cname
    public double now_Price;//trade，港股里的lasttrade，美股里的price
    public double price_change;//pricechange，美股里的diff
    public double change_percent;//changepercent,美股里的chg
    public int volume;//成交量
    public int categoryID;//1沪股，2深股，3港股，4美股

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

    public double getNow_Price() {
        return now_Price;
    }

    public void setNow_Price(double now_Price) {
        this.now_Price = now_Price;
    }

    public double getPrice_change() {
        return price_change;
    }

    public void setPrice_change(double price_change) {
        this.price_change = price_change;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getChange_percent() {
        return change_percent;
    }

    public void setChange_percent(double change_percent) {
        this.change_percent = change_percent;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


}
