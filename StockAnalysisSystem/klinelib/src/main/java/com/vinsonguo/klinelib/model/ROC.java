package com.vinsonguo.klinelib.model;

import java.util.ArrayList;
import java.util.List;

public class ROC {
    private ArrayList<Double> Roc;
    private ArrayList<Double> Maroc;
    private ArrayList<Double> Bx;
    private ArrayList<Double> Ax;
    double close = 0.0;

    public ROC(List<HisData> OHLCData) {
        Roc = new ArrayList<Double>();
        Maroc = new ArrayList<Double>();
        Bx=new ArrayList<Double>();
        Ax=new ArrayList<Double>();
        if (OHLCData != null && OHLCData.size() > 0) {
            HisData oHLCEntity;
            for (int i = 0; i<OHLCData.size(); i++) {
                oHLCEntity=OHLCData.get(i);
                close = oHLCEntity.getClose();
               if(i<12){
                  Bx.add(OHLCData.get(0).getClose());
                }else{
                  Bx.add(OHLCData.get(i+1-12).getClose());
                }
                Ax.add(close-Bx.get(i));
                double t1=Ax.get(i)/Bx.get(i);

                Roc.add(Double.parseDouble(String.format("%.2f",t1)));
                double s=0.0;
                if(i<5){
                   for(int j=0;j<=i;j++){
                       s+=Roc.get(j);
                   }
                   s/=(i+1);
                }else {
                    for(int j=0;j<6;j++){
                        s+=Roc.get(i-j);
                    }
                    s/=6;
                }
                Maroc.add(Double.parseDouble(String.format("%.2f",s)));
            }
        }
    }

    public List<Double> getRoc() {
        return Roc;
    }
    public List<Double> getMaroc() {
        return Maroc;
    }
}
