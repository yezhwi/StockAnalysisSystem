package com.vinsonguo.klinelib.model;

import java.util.ArrayList;
import java.util.List;

public class RSV {
    private ArrayList<Double> rsv;
    private int n;
    double high = 0.0;
    double low = 0.0;
    double close = 0.0;

    public RSV(List<HisData> OHLCData, int m) {
        n = m;
        rsv = new ArrayList<Double>();
        ArrayList<Double> r = new ArrayList<Double>();
        double rs = 0.0;

        if (OHLCData != null && OHLCData.size() > 0) {
            HisData oHLCEntity,test;
            for (int i = 0; i<OHLCData.size(); i++) {
                oHLCEntity=OHLCData.get(i);
                close = oHLCEntity.getClose();
                if (i < n) {
                    oHLCEntity = OHLCData.get(0);
                    high=oHLCEntity.getHigh();
                    low=oHLCEntity.getLow();
                    for(int j=0;j<=i;j++){
                        test=OHLCData.get(i);
                        if(test.getHigh()>high)
                            high=test.getHigh();
                        if(test.getLow()<low)
                            low=test.getLow();
                    }
                } else {
                    oHLCEntity = OHLCData.get(i-n);
                    high=oHLCEntity.getHigh();
                    low=oHLCEntity.getLow();
                    for(int j=i-n;j<=i;j++){
                        test=OHLCData.get(i);
                        if(test.getHigh()>high)
                            high=test.getHigh();
                        if(test.getLow()<low)
                            low=test.getLow();
                    }
                }
                if (high != low) {
                    rs = (close - low) / (high - low) * 100;
                    r.add(rs);
                } else {
                    r.add(0.00);
                }
            }
            for (int i = 0; i<r.size(); i++) {
                rsv.add(r.get(i));
            }

        }
    }

    public List<Double> getRSV() {
        return rsv;
    }
}

