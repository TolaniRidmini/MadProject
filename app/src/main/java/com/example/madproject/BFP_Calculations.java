package com.example.madproject;

public class BFP_Calculations {
    public double getMaleBFP(double waist, double height, double neck){
        return 100*((4.95/(1.0324 - 0.19077 * Math.log10(waist-neck)+0.15456*Math.log10(height)))-4.5);
    }

    public double getFemaleBFP(double waist, double height, double neck, double hip){
        return 100*((4.95/(1.29579 - 0.35004 * Math.log10(waist+hip-neck)+0.22100*Math.log10(height)))-4.5);
    }
}
