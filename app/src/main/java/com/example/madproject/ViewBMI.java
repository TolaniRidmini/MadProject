package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewBMI extends AppCompatActivity {

    TextView BMIvaluetxt;
    TextView BMIstatustxt;
    TextView targetWeight;
    TextView BMIdestxt;

    String val1;
    String val2;
    String val3;
    BMIclass bmic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bmi);

        BMIvaluetxt = findViewById(R.id.BMIvaluetxt);
        BMIstatustxt = findViewById(R.id.BMIstatustxt);
        targetWeight = findViewById(R.id.targetWeight);
        BMIdestxt = findViewById(R.id.BMIdestxt);

        Intent intent = getIntent();
        val1 = intent.getStringExtra("n1");
        val2 = intent.getStringExtra("n2");
        val3 = intent.getStringExtra("n3");

        Float fbmi = Float.parseFloat(val1);
        String stat = "";
        String desc = "";

        if(fbmi <= 18.5){
            stat = "Under weight";
            desc = "You are underweight for your height. It's important to aim to keep within your healthy weight range.";
        }
        else if(fbmi <= 25){
            stat = "Healthy weight";
            desc = "You are in a healthy weight for your height.Keep it up";
        }
        else if(fbmi <= 30){
            stat = "Over weight";
            desc = "Keeping to a healthy weight will help you control your blood pressure and cholesterol levels.";
        }
        else{
            stat = "Obesity";
            desc = "It is important that you take steps to reduce your weight.It helps to decrease your risk of heart disease,diabetes and other illnesses.";
        }

        BMIvaluetxt.setText(val1);
        BMIstatustxt.setText(stat);
        targetWeight.setText(val2);
        BMIdestxt.setText(desc);
    }
    public void onClickback(View view){
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
    }
    public void onClickSaveRecord(View view){
        insert();
    }

    public void insert(){

        try{

            String Qname = BMIvaluetxt.getText().toString();
            String Qbmi = BMIvaluetxt.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS bmirecords(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,bmi VARCHAR)");

            String sql = "insert into bmirecords(name,bmi)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,val3);
            statement.bindString(2,Qbmi);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();
        }

        catch (Exception ex){
            Toast.makeText(this,"Faild",Toast.LENGTH_LONG).show();
        }
    }
    public void onClickBMI_records(View view){

        Intent intent = new Intent(this, BMI_records.class);
        startActivity(intent);
    }
}