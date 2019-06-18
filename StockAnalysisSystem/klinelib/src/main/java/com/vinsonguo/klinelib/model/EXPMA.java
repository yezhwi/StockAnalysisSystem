package com.vinsonguo.klinelib.model;

import java.util.ArrayList;
import java.util.List;

public class EXPMA {
    private ArrayList<Double> EMA12;
    private ArrayList<Double> EMA50;

    double close = 0.0;

    public EXPMA(List<HisData> OHLCData) {
        EMA12 = new ArrayList<Double>();
        EMA50 = new ArrayList<Double>();

        if (OHLCData != null && OHLCData.size() > 0) {
            HisData oHLCEntity,test;
            for (int i = 0; i<OHLCData.size(); i++) {
                oHLCEntity=OHLCData.get(i);
                close = oHLCEntity.getClose();
                if(i==0){
                    EMA12.add(close);
                    EMA50.add(close);
                }else{
                    double t1=(close*2+EMA12.get(i-1)*(11))/13;
                    EMA12.add(Double.parseDouble(String.format("%.2f",t1)));
                    double t2=(close*2+EMA50.get(i-1)*(49))/51;
                    EMA50.add(Double.parseDouble(String.format("%.2f",t2)));
                }
            }
        }
    }

    public List<Double> getEMA12() {
        return EMA12;
    }
    public List<Double> getEMA50() {
        return EMA50;
    }
}
