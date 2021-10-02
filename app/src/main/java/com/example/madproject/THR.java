package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class THR extends AppCompatActivity {

    EditText thrNametxt;
    EditText thrAgetxt;
    EditText thrWeighttxt;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    String iName;
    String iAge;
    String iHR;
    boolean checkF = false;
    boolean checkR = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr);

        thrNametxt = findViewById(R.id.thrNametxt);
        thrAgetxt = findViewById(R.id.thrAgetxt);
        thrWeighttxt = findViewById(R.id.thrWeighttxt);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        calculatethr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calculateTHR();
            }
        });
    }*/

    public String calculateTHR(int v){
        THR_Calculations thrCal = new THR_Calculations();

        String f1 = "";
        String f2 = "";
        String f3 = "";

        iName = thrNametxt.getText().toString();
        iAge = thrAgetxt.getText().toString();
        iHR = thrWeighttxt.getText().toString();

        if(TextUtils.isEmpty(iName) || TextUtils.isEmpty(iAge) || TextUtils.isEmpty(iHR)){
            checkF = true;
        }
        else{
            Double maxHR = 0.0;
            Double lowE = 0.0;
            Double highE = 0.0;
            if(radioButtonMale.isChecked() || radioButtonFemale.isChecked()){
                Double ageF = Double.parseDouble(iAge);
                Double hrF = Double.parseDouble(iHR);

                maxHR = thrCal.getMaxHR(ageF);
                lowE = thrCal.getLowEnd(maxHR);
                highE = thrCal.getHighEnd(maxHR);
            }
            else{
                checkR = true;
            }

            f1 = (new Double(maxHR).toString());
            f2 = (new Double(lowE).toString());
            f3 = (new Double(highE).toString());

        }
        //return f1;
        if(v==0){
            return f1;
        }
        else if(v==1){
            return f2;
        }
        else{
            return f3;
        }
    }

    public void onClickTHrrecords(View view){
        Intent intent = new Intent(this, THR_records.class);
        startActivity(intent);
    }

    public void onClickback1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickview1(View view){

        if(TextUtils.isEmpty(thrNametxt.getText().toString())){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(thrAgetxt.getText().toString())){
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_LONG).show();
            return;
        }
        if(!(radioButtonMale.isChecked() || radioButtonFemale.isChecked())){
            Toast.makeText(this,"Please select your gender",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(thrWeighttxt.getText().toString())){
            Toast.makeText(this,"Please enter your current heart rate",Toast.LENGTH_LONG).show();
            return;
        }

        String f1 = calculateTHR(0);
        String f2 = calculateTHR(1);
        String f3 = calculateTHR(2);


        Intent intent = new Intent(this, ViewTHR.class);
        intent.putExtra("n1", f1);
        intent.putExtra("n2", f2);
        intent.putExtra("n3", f3);
        intent.putExtra("n4", iHR);
        intent.putExtra("n5", iName);
        startActivity(intent);


    }
}