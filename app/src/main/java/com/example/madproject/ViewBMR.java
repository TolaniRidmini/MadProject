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

public class ViewBMR extends AppCompatActivity {

    TextView BMRValtxt;
    TextView BMRstatustxt;
    TextView BMRdestxt;

    String val1;
    String val2;
    String val3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bmr);

        BMRValtxt = findViewById(R.id.BMRValtxt);
        BMRstatustxt = findViewById(R.id.BMRstatustxt);
        BMRdestxt = findViewById(R.id.BMRdestxt);

        Intent intent = getIntent();
        val1 = intent.getStringExtra("n1");
        val2 = intent.getStringExtra("n2");
        val3 = intent.getStringExtra("n3");

        Float fBMRval = Float.parseFloat(val1);

        String stat = "";
        String desc = "";

        Integer igender = Integer.parseInt(val3);

        if(igender==1){
            stat = "Male";
            if(fBMRval<1500){
                stat = "Healthy";
                desc = "You have a Body Metabolic Rate for your age. Keep it up.";
            }
            else{
                stat = "Unhealthy";
                desc = "You shouldn't consume fewer calories than your BMR. To lose weight properly, you need to consider both physical activity and your BMR.";
            }
        }
        if(igender==0){
            stat = "Female";
            if(fBMRval<1400){
                stat = "Healthy";
                desc = "You have a Body Metabolic Rate for your age. Keep it up.";
            }
            else{
                stat = "Unhealthy";
                desc = "You shouldn't consume fewer calories than your BMR. To lose weight properly, you need to consider both physical activity and your BMR.";
            }
        }

        BMRValtxt.setText(val1);
        BMRstatustxt.setText(stat);
        BMRdestxt.setText(desc);
    }
    public void onClickback3(View view){
        Intent intent = new Intent(this, BMR.class);
        startActivity(intent);
    }
    public void onClickSaveRecord(View view){
        insert();
    }
    public void insert(){

        try{

            String Qbmr = BMRValtxt.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS bmrrecords(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,bmr VARCHAR)");

            String sql = "insert into bmrrecords(name,bmr)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,val2);
            statement.bindString(2,Qbmr);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();
        }

        catch (Exception ex){
            Toast.makeText(this,"Faild",Toast.LENGTH_LONG).show();
        }
    }
    public void onClickBMR_records(View view){

        Intent intent = new Intent(this, BMR_records.class);
        startActivity(intent);
    }
}