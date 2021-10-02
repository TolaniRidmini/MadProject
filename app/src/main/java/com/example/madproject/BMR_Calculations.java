package com.example.madproject;

public class BMR_Calculations {

    public double getMaleBMR(double w, double h, int a){
        return 5 + (10 * w) + (6.25 * h) - (5 * a) ;
    }

    public double getFemaleBMR(double w, double h, int a){
        return (10 * w) + (6.25 * h) - (5 * a) - 161 ;
    }
}
