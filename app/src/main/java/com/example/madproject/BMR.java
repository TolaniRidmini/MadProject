package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class BMR extends AppCompatActivity {

    EditText bmrNametxt;
    EditText bmrAgetxt;
    EditText bmrWeighttxt;
    EditText bmrHeighttxt;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    String iName;
    String iAge;
    String iH;
    String iW;
    int gender;
    String sgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        bmrNametxt = findViewById(R.id.bmrNametxt);
        bmrAgetxt = findViewById(R.id.bmrAgetxt);
        bmrWeighttxt = findViewById(R.id.bmrWeighttxt);
        bmrHeighttxt = findViewById(R.id.bmrHeighttxt);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
    }

    public String calculateBMR(){
        BMR_Calculations bmrCal = new BMR_Calculations();

        String f1 = "";

        iName = bmrNametxt.getText().toString();
        iAge = bmrAgetxt.getText().toString();
        iW = bmrWeighttxt.getText().toString();
        iH = bmrHeighttxt.getText().toString();

        if(TextUtils.isEmpty(iName) || TextUtils.isEmpty(iAge) || TextUtils.isEmpty(iH) || TextUtils.isEmpty(iW)){
            //checkF = true;
        }
        else{

            Double bmrVal = 0.0;

            Integer ageF = Integer.parseInt(iAge);
            Double hF = Double.parseDouble(iH);
            Double wF = Double.parseDouble(iW);

            if(radioButtonMale.isChecked()){
                bmrVal = bmrCal.getMaleBMR(wF, hF, ageF);
                gender = 1;
            }
            if(radioButtonFemale.isChecked()){
                bmrVal = bmrCal.getFemaleBMR(wF, hF, ageF);
                gender = 0;
            }
            else{
                //checkR = true;
            }

            f1 = (new Double(bmrVal).toString());

        }
        return f1;

    }

    public void onClickback3(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickview3(View view){

        if(TextUtils.isEmpty(bmrNametxt.getText().toString())){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bmrAgetxt.getText().toString())){
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_LONG).show();
            return;
        }
        if(!(radioButtonMale.isChecked() || radioButtonFemale.isChecked())){
            Toast.makeText(this,"Please select your gender",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bmrWeighttxt.getText().toString())){
            Toast.makeText(this,"Please enter your weight",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bmrHeighttxt.getText().toString())){
            Toast.makeText(this,"Please enter your height",Toast.LENGTH_LONG).show();
            return;
        }

        String f1 = calculateBMR();
        sgender = (new Integer(gender).toString());

        Intent intent = new Intent(this, ViewBMR.class);
        intent.putExtra("n1", f1);
        intent.putExtra("n2", iName);
        intent.putExtra("n3", sgender);
        startActivity(intent);
    }
    public void onClickBMR_records(View view){

        Intent intent = new Intent(this, BMR_records.class);
        startActivity(intent);
    }
}