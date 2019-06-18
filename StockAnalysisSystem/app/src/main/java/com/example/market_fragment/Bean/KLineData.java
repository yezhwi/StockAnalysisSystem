package com.example.market_fragment.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class KLineData implements Parcelable {
    public String minurl;//分时图
    public String dayurl;//日k
    public String weekurl;//周K
    public String monthurl;//月K

    public String getMinurl() {
        return minurl;
    }

    public void setMinurl(String minurl) {
        this.minurl = minurl;
    }

    public String getDayurl() {
        return dayurl;
    }

    public void setDayurl(String dayurl) {
        this.dayurl = dayurl;
    }

    public String getWeekurl() {
        return weekurl;
    }

    public void setWeekurl(String weekurl) {
        this.weekurl = weekurl;
    }

    public String getMonthurl() {
        return monthurl;
    }

    public void setMonthurl(String monthurl) {
        this.monthurl = monthurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(minurl);
        dest.writeString(dayurl);
        dest.writeString(weekurl);
        dest.writeString(monthurl);
    }

    public static final Parcelable.Creator<KLineData>CREATOR=new Parcelable.
            Creator<KLineData>(){
        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public KLineData createFromParcel(Parcel source) {
            KLineData kLineData=new KLineData();
            kLineData.minurl=source.readString();
            kLineData.dayurl=source.readString();
            kLineData.weekurl=source.readString();
            kLineData.monthurl=source.readString();
            return kLineData;
        }

        @Override
        public KLineData[] newArray(int size) {
            return new KLineData[0];
        }
    };
}
