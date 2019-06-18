package com.example.market_fragment.Bean;

import android.os.Parcel;
import android.os.Parcelable;


public class IndexBean implements Parcelable {
    public String name;
    public String gid;
    public String nowPrice;
    public String changePrice;
    public String changePercent;
    public String todayMax;
    public String todayMin;
    public String todayStartPri;
    public String yestodEndPri;
    public String date;
    public String time;

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTodayMax() {
        return todayMax;
    }

    public void setTodayMax(String todayMax) {
        this.todayMax = todayMax;
    }

    public String getTodayMin() {
        return todayMin;
    }

    public void setTodayMin(String todayMin) {
        this.todayMin = todayMin;
    }

    public String getTodayStartPri() {
        return todayStartPri;
    }

    public void setTodayStartPri(String todayStartPri) {
        this.todayStartPri = todayStartPri;
    }

    public String getYestodEndPri() {
        return yestodEndPri;
    }

    public void setYestodEndPri(String yestodEndPri) {
        this.yestodEndPri = yestodEndPri;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gid);
        dest.writeString(nowPrice);
        dest.writeString(changePrice);
        dest.writeString(changePercent);
        dest.writeString(todayMax);
        dest.writeString(todayMin);
        dest.writeString(todayStartPri);
        dest.writeString(yestodEndPri);
        dest.writeString(date);
        dest.writeString(time);
    }

    public static final Parcelable.Creator<IndexBean>CREATOR=new Parcelable.Creator<IndexBean>(){
        @Override
        public IndexBean createFromParcel(Parcel source) {
            IndexBean indexBean=new IndexBean();
            indexBean.name=source.readString();
            indexBean.gid=source.readString();
            indexBean.nowPrice=source.readString();
            indexBean.changePrice=source.readString();
            indexBean.changePercent=source.readString();
            indexBean.todayMax=source.readString();
            indexBean.todayMin=source.readString();
            indexBean.todayStartPri=source.readString();
            indexBean.yestodEndPri=source.readString();
            indexBean.date=source.readString();
            indexBean.time=source.readString();
            return indexBean;
        }

        @Override
        public IndexBean[] newArray(int size) {
            return new IndexBean[0];
        }
    };
}
