package com.vinsonguo.klinelib.model;

import java.util.ArrayList;
import java.util.List;

public class RSI {

    private List<Double> LCs;
    private List<Double> RS1s;
    private List<Double> RS2s;
    private List<Double> RS3s;


    public RSI(List<HisData> kLineBeen) {
        LCs = new ArrayList<>();
        RS1s = new ArrayList<>();
        RS2s = new ArrayList<>();
        RS3s = new ArrayList<>();

        List<Double> l=new ArrayList<>();
        List<Double> h=new ArrayList<>();
        if (kLineBeen != null && kLineBeen.size() > 0) {
            for(int i=0;i<kLineBeen.size();i++){
                double lc;
                if(i==0){
                    lc=0;
                }else{
                    lc=kLineBeen.get(i-1).getClose();
                }
                LCs.add(lc);
            }

            for(int i=0;i<LCs.size();i++){
                double rs1,l1,h1;
                if(i<5){
                    if(i==0){
                        h1=Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0);
                        l1=Math.abs(kLineBeen.get(i).getClose()-LCs.get(i));
                        rs1=0;
                    }else{
                        h1=((Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0)+(i-1)*h.get(i-1))/i);
                        l1=((Math.abs(kLineBeen.get(i).getClose()-LCs.get(i))+(i-1)*l.get(i-1))/i);
                    }
                }else {
                   h1=((Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0)+5*h.get(i-1))/6);
                   l1=((Math.abs(kLineBeen.get(i).getClose()-LCs.get(i))+5*l.get(i-1))/6);
                }
                h.add(h1);
                l.add(l1);
                rs1=h1/l1;
                rs1*=100;
                RS1s.add(Double.parseDouble(String.format("%.2f",rs1)));
            }

            h.clear();
            l.clear();
            for(int i=0;i<LCs.size();i++){
                double rs2,l1,h1;
                if(i<11){
                    if(i==0){
                        h1=Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0);
                        l1=Math.abs(kLineBeen.get(i).getClose()-LCs.get(i));
                        rs2=0;
                    }else{
                        h1=((Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0)+(i-1)*h.get(i-1))/i);
                        l1=((Math.abs(kLineBeen.get(i).getClose()-LCs.get(i))+(i-1)*l.get(i-1))/i);
                    }
                }else {
                    h1=((Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0)+11*h.get(i-1))/12);
                    l1=((Math.abs(kLineBeen.get(i).getClose()-LCs.get(i))+11*l.get(i-1))/12);
                }
                h.add(h1);
                l.add(l1);
                rs2=h1/l1;
                rs2*=100;
                RS2s.add(Double.parseDouble(String.format("%.2f",rs2)));
            }

            h.clear();
            l.clear();
            for(int i=0;i<LCs.size();i++){
                double rs3,l1,h1;
                if(i<23){
                    if(i==0){
                        h1=Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0);
                        l1=Math.abs(kLineBeen.get(i).getClose()-LCs.get(i));
                        rs3=0;
                    }else{
                        h1=((Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0)+(i-1)*h.get(i-1))/i);
                        l1=((Math.abs(kLineBeen.get(i).getClose()-LCs.get(i))+(i-1)*l.get(i-1))/i);
                    }
                }else {
                    h1=((Math.max((kLineBeen.get(i).getClose()-LCs.get(i)),0.0)+23*h.get(i-1))/24);
                    l1=((Math.abs(kLineBeen.get(i).getClose()-LCs.get(i))+23*l.get(i-1))/24);
                }
                h.add(h1);
                l.add(l1);
                rs3=h1/l1;
                rs3*=100;
                RS3s.add(Double.parseDouble(String.format("%.2f",rs3)));
            }
        }

    }

    public List<Double> getLC() {
        return LCs;
    }

    public List<Double> getRSI1() {
        return RS1s;
    }

    public List<Double> getRSI2() {
        return RS2s;
    }
    public List<Double> getRSI3() {
        return RS3s;
    }



}
