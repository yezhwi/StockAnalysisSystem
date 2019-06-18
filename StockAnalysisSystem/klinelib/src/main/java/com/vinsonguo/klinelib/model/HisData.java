package com.vinsonguo.klinelib.model;

/**
 * chart data model
 */

public class HisData {

    private double close;
    private double high;
    private double low;
    private double open;
    private long vol;
    private long date;
    private long amountVol;
    private double avePrice;
    private double total;
    private double maSum;
    private double ma5;
    private double ma10;
    private double ma20;
    private double ma30;

    private double dif;
    private double dea;
    private double macd;

    private double k;
    private double d;
    private double j;

    private double rsi1;
    private double rsi2;
    private double rsi3;

    private double up;
    private double mb;
    private double dn;

    private double wr1;
    private double wr2;

    private double ema12;
    private double ema50;

    private double roc;
    private double maroc;

    private double pdi;
    private double mdi;
    private double adx;
    private double adxr;
    public double getDif() {
        return dif;
    }



    public HisData() {
    }

    public HisData(double open,double close, double high, double low,  int vol, long date) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.vol = vol;
        this.date = date;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public long getVol() {
        return vol;
    }

    public void setVol(long vol) {
        this.vol = vol;
    }


    public double getAvePrice() {
        return avePrice;
    }

    public void setAvePrice(double avePrice) {
        this.avePrice = avePrice;
    }


    public long getAmountVol() {
        return amountVol;
    }

    public void setAmountVol(long amountVol) {
        this.amountVol = amountVol;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMa5() {
        return ma5;
    }

    public void setMa5(double ma5) {
        this.ma5 = ma5;
    }

    public double getMa10() {
        return ma10;
    }

    public void setMa10(double ma10) {
        this.ma10 = ma10;
    }

    public double getMa20() {
        return ma20;
    }

    public void setMa20(double ma20) {
        this.ma20 = ma20;
    }

    public double getMa30() {
        return ma30;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HisData data = (HisData) o;

        return date == data.date;
    }

    @Override
    public int hashCode() {
        return (int) (date ^ (date >>> 32));
    }

    public double getMaSum() {
        return maSum;
    }

    public void setMaSum(double maSum) {
        this.maSum = maSum;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }


    public double getRsi1() {
        return rsi1;
    }

    public void setRsi1(double rsi1) {
        this.rsi1 = rsi1;
    }

    public double getRsi2() {
        return rsi2;
    }

    public void setRsi2(double rsi2) {
        this.rsi2 = rsi2;
    }

    public double getRsi3() {
        return rsi3;
    }

    public void setRsi3(double rsi3) {
        this.rsi3 = rsi3;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public double getMb() {
        return mb;
    }

    public void setMb(double mb) {
        this.mb = mb;
    }

    public double getDn() {
        return dn;
    }

    public void setDn(double dn) {
        this.dn = dn;
    }

    public double getWr1() {
        return wr1;
    }

    public void setWr1(double wr1) {
        this.wr1 = wr1;
    }

    public double getWr2() {
        return wr2;
    }

    public void setWr2(double wr2) {
        this.wr2 = wr2;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma50() {
        return ema50;
    }

    public void setEma50(double ema50) {
        this.ema50 = ema50;
    }

    public double getRoc() {
        return roc;
    }

    public void setRoc(double roc) {
        this.roc = roc;
    }

    public double getMaroc() {
        return maroc;
    }

    public void setMaroc(double maroc) {
        this.maroc = maroc;
    }

    public double getPdi() {
        return pdi;
    }

    public void setPdi(double pdi) {
        this.pdi = pdi;
    }

    public double getMdi() {
        return mdi;
    }

    public void setMdi(double mdi) {
        this.mdi = mdi;
    }

    public double getAdx() {
        return adx;
    }

    public void setAdx(double adx) {
        this.adx = adx;
    }

    public double getAdxr() {
        return adxr;
    }

    public void setAdxr(double adxr) {
        this.adxr = adxr;
    }

    @Override
    public String toString() {
        return "HisData{" +
                "close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", vol=" + vol +
                ", date=" + date +
                ", amountVol=" + amountVol +
                ", avePrice=" + avePrice +
                ", total=" + total +
                ", maSum=" + maSum +
                ", ma5=" + ma5 +
                ", ma10=" + ma10 +
                ", ma20=" + ma20 +
                ", ma30=" + ma30 +
                ", dif=" + dif +
                ", dea=" + dea +
                ", macd=" + macd +
                ", k=" + k +
                ", d=" + d +
                ", j=" + j +
                ", rsi1=" + rsi1 +
                ", rsi2=" + rsi2 +
                ", rsi3=" + rsi3 +
                ", up=" + up +
                ", mb=" + mb +
                ", dn=" + dn +
                ", wr1=" + wr1 +
                ", wr2=" + wr2 +
                ", ema12=" + ema12 +
                ", ema50=" + ema50 +
                ", roc=" + roc +
                ", maroc=" + maroc +
                ", pdi=" + pdi +
                ", mdi=" + mdi +
                ", adx=" + adx +
                ", adxr=" + adxr +
                '}';
    }
}
