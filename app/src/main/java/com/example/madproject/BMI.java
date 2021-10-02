package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class BMI extends AppCompatActivity {

    EditText bmiNametxt;
    EditText bmiAgetxt;
    EditText bmiWeighttxt;
    EditText bmiHeighttxt;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    String iName;
    String iAge;
    String iH;
    String iW;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        bmiNametxt = findViewById(R.id.bmiNametxt);
        bmiAgetxt = findViewById(R.id.bmiAgetxt);
        bmiWeighttxt = findViewById(R.id.bmiWeighttxt);
        bmiHeighttxt = findViewById(R.id.bmiHeighttxt);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }

    public String calculateBMI(int v){
        BMI_Calculations bmiCal = new BMI_Calculations();

        String f1 = "";
        String f2 = "";

        iName = bmiNametxt.getText().toString();
        iAge = bmiAgetxt.getText().toString();
        iW = bmiWeighttxt.getText().toString();
        iH = bmiHeighttxt.getText().toString();

        if(TextUtils.isEmpty(iName) || TextUtils.isEmpty(iAge) || TextUtils.isEmpty(iH) || TextUtils.isEmpty(iW)){
            //checkF = true;
        }
        else{

            Double bmiVal = 0.0;
            Double targetW = 0.0;

            if(radioButtonMale.isChecked() || radioButtonFemale.isChecked()){
                Double ageF = Double.parseDouble(iAge);
                Double hF = Double.parseDouble(iH);
                Double wF = Double.parseDouble(iW);

                bmiVal = bmiCal.getBMIval(wF, hF);
                if(bmiVal <= 18.5){
                    targetW = bmiCal.getweightToI(wF, hF);
                }
                else if(bmiVal >= 25){
                    targetW = bmiCal.getweightToD(wF, hF);
                }
                else{
                    targetW = 0.0;
                }
            }
            else{
                //checkR = true;
            }

            f1 = (new Double(bmiVal).toString());
            f2 = (new Double(targetW).toString());

        }
        //return f1;
        if(v==0){
            return f1;
        }
        else{
            return f2;
        }
    }
    public void onClickback(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickview(View view){

        if(TextUtils.isEmpty(bmiNametxt.getText().toString())){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bmiAgetxt.getText().toString())){
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_LONG).show();
            return;
        }
        if(!(radioButtonMale.isChecked() || radioButtonFemale.isChecked())){
            Toast.makeText(this,"Please select your gender",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bmiWeighttxt.getText().toString())){
            Toast.makeText(this,"Please enter your weight",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bmiHeighttxt.getText().toString())){
            Toast.makeText(this,"Please enter your height",Toast.LENGTH_LONG).show();
            return;
        }

        String f1 = calculateBMI(0);
        String f2 = calculateBMI(1);

        Intent intent = new Intent(this, ViewBMI.class);
        intent.putExtra("n1", f1);
        intent.putExtra("n2", f2);
        intent.putExtra("n3", iName);
        startActivity(intent);

    }
    public void onClickBMI_records(View view){

        Intent intent = new Intent(this, BMI_records.class);
        startActivity(intent);
    }
}