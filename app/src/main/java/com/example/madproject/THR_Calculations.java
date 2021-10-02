package com.example.madproject;

public class THR_Calculations {

    public double getMaxHR(double age){
        return 220.0 - age;
    }

    public double getLowEnd(double mx){
        double low = 0.0;
        low = mx*0.6;
        return low;
    }

    public double getHighEnd(double mx){
        double high = 0.0;
        high = mx*0.85;
        return high;
    }
}
