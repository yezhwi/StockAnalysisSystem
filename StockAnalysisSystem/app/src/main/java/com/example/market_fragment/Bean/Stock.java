package com.example.market_fragment.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Stock implements Parcelable {
    public String symbol;//股票代码
    public String name;//股票名称
    public String trade;//最新价
    public String pricechange;//涨跌价
    public String changepercent;//涨跌幅度
    public String date;
    public String time;
    public String open;
    public String high;
    public String low;
    public String close;

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getPricechange() {
        return pricechange;
    }

    public void setPricechange(String pricechange) {
        this.pricechange = pricechange;
    }

    public String getChangepercent() {
        return changepercent;
    }

    public void setChangepercent(String changepercent) {
        this.changepercent = changepercent;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(name);
        dest.writeString(trade);
        dest.writeString(pricechange);
        dest.writeString(changepercent);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(open);
        dest.writeString(high);
        dest.writeString(low);
        dest.writeString(close);
    }

    public static final Parcelable.Creator<Stock>CREATOR=new Parcelable.Creator<Stock>(){
        @Override
        public Stock createFromParcel(Parcel source) {
           Stock stock=new Stock();
           stock.symbol=source.readString();
           stock.name=source.readString();
           stock.trade=source.readString();
           stock.pricechange=source.readString();
           stock.changepercent=source.readString();
           stock.date=source.readString();
           stock.time=source.readString();
           stock.open=source.readString();
           stock.high=source.readString();
           stock.low=source.readString();
           stock.close=source.readString();
           return stock;
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[0];
        }
    };

}
