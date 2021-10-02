package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTHR extends AppCompatActivity {

    TextView THRlbtxt;
    TextView THRuptx;
    TextView THRststustxt;
    TextView THRtzonetxt;
    TextView HRThrtxt;
    TextView THRdestxt;

    String val1;
    String val2;
    String val3;
    String val4;
    String val5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_thr);

        THRlbtxt = findViewById(R.id.THRlbtxt);
        THRuptx = findViewById(R.id.THRuptx);
        THRststustxt = findViewById(R.id.THRststustxt);
        THRtzonetxt = findViewById(R.id.THRtzonetxt);
        HRThrtxt = findViewById(R.id.HRThrtxt);
        THRdestxt = findViewById(R.id.THRdestxt);

        Intent intent = getIntent();
        val1 = intent.getStringExtra("n1");
        val2 = intent.getStringExtra("n2");
        val3 = intent.getStringExtra("n3");
        val4 = intent.getStringExtra("n4");
        val5 = intent.getStringExtra("n5");

        Float fHRval = Float.parseFloat(val4);
        Float fLval = Float.parseFloat(val2);
        Float fUval = Float.parseFloat(val3);

        String stat = "Unhealthy";
        String desc = "";

        if(fHRval >= fLval && fHRval <= fUval){
            stat = "Healthy";
            desc = "currently you are healthy for your age, keep it up ";
        }

        if(fHRval <= fLval)
            desc = "slow heart rate is due to the effect of medication or toxic exposure, this must be treated medically.";
        if(fHRval >= fUval)
            desc = "The easiest and most effective way to achieve a lasting lower heart rate is to do regular exercise.";

        THRlbtxt.setText(val2);
        THRuptx.setText(val3);
        THRststustxt.setText(stat);
        THRtzonetxt.setText(val2+"-"+val3);
        HRThrtxt.setText(val4);
        THRdestxt.setText(desc);

    }
    public void onClickback1(View view){
        Intent intent = new Intent(this, THR.class);
        startActivity(intent);
    }
    public void onClickTHrrecords(View view){
        Intent intent = new Intent(this, THR_records.class);
        startActivity(intent);
    }
    public void onClickSaveRecord(View view){
        insert();
    }
    public void insert(){

        try{

            String Qthr = THRtzonetxt.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS thrrecords(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,thr VARCHAR)");

            String sql = "insert into thrrecords(name,thr)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,val5);
            statement.bindString(2,Qthr);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();
        }

        catch (Exception ex){
            Toast.makeText(this,"Faild",Toast.LENGTH_LONG).show();
        }
    }
}