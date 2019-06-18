package com.vinsonguo.klinelib.model;

import java.util.ArrayList;
import java.util.List;

public class BOLL {

    private List<Double> MAS;//n日内的收盘均值
    private List<Double> MDS;//标准差
    private List<Double> MBS;//中轨线数据
    private List<Double> UPS;//上轨线数据
    private List<Double> DNS;//下轨线数据

    /**
     * 得到BOLL数据
     *
     * @param kLineBeen
     */
    public BOLL(List<HisData> kLineBeen) {
        MAS = new ArrayList<>();
        MDS =new ArrayList<>();
        MBS = new ArrayList<>();
        UPS = new ArrayList<>();
        DNS = new ArrayList<>();
        //Double ma;
        if (kLineBeen != null && kLineBeen.size() > 0) {
            Double ma=0.0;

           for(int i=0;i<kLineBeen.size();i++) {
               ma+=kLineBeen.get(i).getClose();
               Double t=ma/(i+1);
               MAS.add(Double.parseDouble(String.format("%.2f",t)));
               Double md=0.0;
               for(int k=0;k<=i;k++){
                   md+=(MAS.get(i)-kLineBeen.get(k).getClose())*(MAS.get(i)-kLineBeen.get(k).getClose());
               }
               md/=(i+1);

               MDS.add(Double.parseDouble(String.format("%.2f",Math.sqrt(md))));
           }
           for(int i=0;i<kLineBeen.size();i++){
                if(i==0){
                    MBS.add(MAS.get(0));
                    UPS.add(MAS.get(0));
                    DNS.add(MAS.get(0));
                }else {
                    MBS.add(MAS.get(i-1));
                    Double up=MBS.get(i)+2*MDS.get(i);
                    UPS.add(Double.parseDouble(String.format("%.2f",up)));
                    Double dn=MBS.get(i)-2*MDS.get(i);
                    DNS.add(Double.parseDouble(String.format("%.2f",dn)));
                }

           }
        }

    }

    public List<Double> getMBS() {
        return MBS;
    }

    public List<Double> getUPS() {
        return UPS;
    }

    public List<Double> getDNS() {
        return DNS;
    }

}
