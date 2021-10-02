package com.example.madproject;

public class BMI_Calculations {
    public double getBMIval(double w, double h){
        return w/(h/100*h/100);
    }

    public double getweightToD(double w, double h){
        double reqW = 25*(h/100)*(h/100);
        return reqW;
    }

    public double getweightToI(double w, double h){
        double reqW = 18.5*(h/100)*(h/100);
        return reqW;
    }
}
