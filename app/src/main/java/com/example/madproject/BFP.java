package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class BFP extends AppCompatActivity {

    EditText bfpNametxt;
    EditText bfpAgenum;
    EditText bfpWeightdnum;
    EditText bfpHeightdnum;
    EditText bfpneckdnum;
    EditText bfpWaistdnum;
    EditText bfpHipdnum;
    RadioButton radioButtonMale;
    RadioButton radioButton2;
    String iName;
    String iAge;
    String iH;
    String iW;
    String iN;
    String iWA;
    String iHI;
    int gender;
    String sgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfp);

        bfpNametxt = findViewById(R.id.bfpNametxt);
        bfpAgenum = findViewById(R.id.bfpAgenum);
        bfpWeightdnum = findViewById(R.id.bfpWeightdnum);
        bfpHeightdnum = findViewById(R.id.bfpHeightdnum);
        bfpneckdnum = findViewById(R.id.bfpneckdnum);
        bfpWaistdnum = findViewById(R.id.bfpWaistdnum);
        bfpHipdnum = findViewById(R.id.bfpHipdnum);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButton2 = findViewById(R.id.radioButton2);
    }

    public String calculateBFP(){
        BFP_Calculations bfpCal = new BFP_Calculations();

        String f1 = "fffffffff";

        iName = bfpNametxt.getText().toString();
        iAge = bfpAgenum.getText().toString();
        iW = bfpWeightdnum.getText().toString();
        iH = bfpHeightdnum.getText().toString();
        iN = bfpneckdnum.getText().toString();
        iWA = bfpWaistdnum.getText().toString();
        iHI = bfpHipdnum.getText().toString();

        Double bfpVal = 0.0;

        Double hF = Double.parseDouble(iH);
        Double nF = Double.parseDouble(iN);
        Double waF = Double.parseDouble(iWA);
        Double hiF = Double.parseDouble(iHI);

        if(radioButtonMale.isChecked()){
            bfpVal = bfpCal.getMaleBFP(waF, hF, nF);
            gender = 1;
        }
        if(radioButton2.isChecked()) {
            bfpVal = bfpCal.getFemaleBFP(waF, hF, nF, hiF);
            gender = 0;
        }


        f1 = (new Double(bfpVal).toString());

        return f1;

    }

    public void onClickback2(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickview2(View view){

        if(TextUtils.isEmpty(bfpNametxt.getText().toString())){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bfpAgenum.getText().toString())){
            Toast.makeText(this,"Please enter your age",Toast.LENGTH_LONG).show();
            return;
        }
        if(!(radioButtonMale.isChecked() || radioButton2.isChecked())){
            Toast.makeText(this,"Please select your gender",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bfpWeightdnum.getText().toString())){
            Toast.makeText(this,"Please enter your weight",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bfpHeightdnum.getText().toString())){
            Toast.makeText(this,"Please enter your height",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bfpWaistdnum.getText().toString())){
            Toast.makeText(this,"Please enter your waist size",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bfpneckdnum.getText().toString())){
            Toast.makeText(this,"Please enter your neck size",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(bfpHipdnum.getText().toString())){
            Toast.makeText(this,"Please enter your hip size",Toast.LENGTH_LONG).show();
            return;
        }

        String f2 = "Hi";
        f2 = calculateBFP();
        sgender = (new Integer(gender).toString());

        Intent intent = new Intent(this, ViewBFP.class);
        intent.putExtra("n1", f2);
        intent.putExtra("n2", iName);
        intent.putExtra("n3", sgender);
        startActivity(intent);

    }
    public void onClickBFP_records(View view){

        Intent intent = new Intent(this, BFP_records.class);
        startActivity(intent);
    }
}