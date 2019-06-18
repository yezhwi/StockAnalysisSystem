package com.vinsonguo.klinelib.model;

import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class DMI {
    private ArrayList<Double> PDI;
    private ArrayList<Double> MDI;
    private ArrayList<Double> DX;
    private ArrayList<Double> ADX;
    private ArrayList<Double> ADXR;
    Double high=0.0,low=0.0;
    public DMI(List<HisData> OHLCData) {
        PDI = new ArrayList<Double>();
        MDI = new ArrayList<Double>();
        DX = new ArrayList<Double>();
        ADX =new ArrayList<Double>();
        ADXR =new ArrayList<Double>();
        ArrayList<Double> Pdm=new ArrayList<Double>();
        ArrayList<Double> Mdm=new ArrayList<Double>();
        ArrayList<Double> Tr=new ArrayList<Double>();
        if (OHLCData != null && OHLCData.size() > 0) {
            HisData oHLCEntity;
            double t1,t2,t3,t4,t5;
            for (int i = 0; i<OHLCData.size(); i++) {
                oHLCEntity=OHLCData.get(i);
                //close = oHLCEntity.getClose();
                high=oHLCEntity.getHigh();
                low=oHLCEntity.getLow();
                t1=high-low;
                t2=high;
                t3=low;
                if(i!=0){
                    high=oHLCEntity.getHigh()-OHLCData.get(i-1).getHigh();
                    low=OHLCData.get(i-1).getLow()-oHLCEntity.getLow();
                    t2=oHLCEntity.getHigh()-OHLCData.get(i-1).getClose();
                    t3=oHLCEntity.getLow()-OHLCData.get(i-1).getClose();
                }
                if (high<=0.0)
                    high=0.0;
                if(low<=0.0)
                    low=0.0;
                if(high>low){
                    low=0.0;
                }else if(high<low){
                    high=0.0;
               }
                Pdm.add(high);
                Mdm.add(low);
                Tr.add(Double.parseDouble(String.format("%.2f",getmax(t1,t2,t3))));
                double s1=0.0,s2=0.0,s3=0.0,s4=0.0,s5=0.0;
                if(i<11){
                    for(int j=0;j<=i;j++){
                        s1+=Pdm.get(j);
                        s2+=Mdm.get(j);
                        s3+=Tr.get(j);
                    }
                    s1/=(i+1);
                    s2/=(i+1);
                    s3/=(i+1);
                }else {
                    for (int j=0;j<12;j++){
                        s1+=Pdm.get(i-j);
                        s2+=Mdm.get(i-j);
                        s3+=Tr.get(i-j);
                    }
                    s1/=12;
                    s2/=12;
                    s3/=12;
                }
                t4=s1/s3*100;
                t5=s2/s3*100;
                PDI.add(Double.parseDouble(String.format("%.2f",t4)));
                MDI.add(Double.parseDouble(String.format("%.2f",t5)));
                double dx=(Math.abs(PDI.get(i)-MDI.get(i))/(PDI.get(i)+MDI.get(i))*100);
                DX.add(Double.parseDouble(String.format("%.2f",dx)));
                if(i<5){
                    for (int j=0;j<=i;j++){
                        s4+=DX.get(j);
                    }
                    s4/=(i+1);
                }else {
                    for (int j=0;j<6;j++){
                        s4+=DX.get(i-j);
                    }
                    s4/=6;
                }
                ADX.add(Double.parseDouble(String.format("%.2f",s4)));
                if(i<5){
                    s5=ADX.get(i)+ADX.get(0);
                }else {
                    s5=ADX.get(i)+ ADX.get(i+1-6);
                }
                s5/=2;
                ADXR.add(Double.parseDouble(String.format("%.2f",s5)));

            }
        }
    }

    public List<Double> getPdi() {
        return PDI;
    }
    public List<Double> getMdi() {
        return MDI;
    }
    public List<Double> getAdx() {
        return ADX;
    }
    public List<Double> getAdxr() {
        return ADXR;
    }

    double getmax(double t1,double t2,double t3){
        double t;
        if(t1>=t2){
            t=t1;
        }else {
            t=t2;
        }
        if(t<t3){
            t=t3;
        }
        return t;
    }
}
