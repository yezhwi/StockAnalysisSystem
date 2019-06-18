package com.example.home_fragment.ThemeDB;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

//5g
public class fgStock extends LitePalSupport implements Serializable {
    public String gid;//json里的symbol
    public String name;//json里的name，美股里的cname
    public String now_Price;//trade，港股里的lasttrade，美股里的price
    public String price_change;//pricechange，美股里的diff
    public String change_percent;//changepercent,美股里的chg
    public String volume;//成交量
    public String category;//目录
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

    public String getNow_Price() {
        return now_Price;
    }

    public void setNow_Price(String now_Price) {
        this.now_Price = now_Price;
    }

    public String getPrice_change() {
        return price_change;
    }

    public void setPrice_change(String price_change) {
        this.price_change = price_change;
    }

    public String getChange_percent() {
        return change_percent;
    }

    public void setChange_percent(String change_percent) {
        this.change_percent = change_percent;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}