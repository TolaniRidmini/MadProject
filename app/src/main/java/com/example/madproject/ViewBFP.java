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

public class ViewBFP extends AppCompatActivity {

    TextView BPFvaluetxt;
    TextView BPFstatustxt;
    TextView BPFdestxt;

    String val1;
    String val2;
    String val3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bfp);

        String stat = "aaa";
        String desc = "sss";

        BPFvaluetxt = findViewById(R.id.BPFvaluetxt);
        BPFstatustxt = findViewById(R.id.BPFstatustxt);
        BPFdestxt = findViewById(R.id.BPFdestxt);

        Intent intent = getIntent();
        val1 = intent.getStringExtra("n1");
        val2 = intent.getStringExtra("n2");
        val3 = intent.getStringExtra("n3");

        Double bfp1 = Double.parseDouble(val1);

        stat = "xxx";

        //Toast.makeText(this,val3.toString(),Toast.LENGTH_LONG).show();
        Integer igender = Integer.parseInt(val3);

        if(igender==1){
            stat = "Male";
            if(bfp1>2&&bfp1<5){
                stat = "Essential fat";
                desc = "Having body fat levels at this end of the scale puts you in the genetic elite, or competition bodybuilder level.This level is only leaving enough for you to survive";
            }
            else if(bfp1<13){
                stat = "Athletes";
                desc = "The body fat is still lean, which means your abs will be visible. It’s also considered healthier and easier to obtain than essiential fat range";
            }
            else if(bfp1<17){
                stat = "Fitness";
                desc = "While still considered healthy, it’s less likely that you will see much muscle definition in this range. It’s unlikely that you’d see ab definition in this percentage.";
            }
            else if(bfp1<25){
                stat = "Average";
                desc = "There’s a good chance you will be soft around the middle. This means your abs will not be visible. This is the higher end of “average” for males";
            }
            else{
                stat = "Obese";
                desc = "Here you will not see your abs at all. This level is considered obese.  Focus on making lifestyle choices that will help you get back to a healthy body fat range.";
            }
        }
        if(igender==0){
            stat = "Female";
            if(bfp1>10&&bfp1<13){
                stat = "Essential fat";
                desc = "You’re aiming for low levels of body fat.This would result in an extremely athletic physique, with great muscle definition, and visible abs if genetic muscle belly thickness is there";
            }
            else if(bfp1<20){
                stat = "Athletes";
                desc = "At this level typically they have an athletic build, with great shape and very little body fat.abs starts to fade. If this is the level you’re aiming for, you will need to adhere to a strict diet and exercise plan.";
            }
            else if(bfp1<24){
                stat = "Fitness";
                desc = "This is considered to be a low to low-average level of body fat. Your natural curves will very much be a part of your body.";
            }
            else if(bfp1<31){
                stat = "Average";
                desc = "Your body may start to have a softer look. You still have very little in the way of excess fat, but your definition may be minimal.";
            }
            else{
                stat = "Obese";
                desc = "This is a red flag for weight loss.Like men in this range.This makes you a prime candidate for diabetes, and you have an elevated risk for heart disease in the future.";
            }
        }

        BPFvaluetxt.setText(val1);
        BPFstatustxt.setText(stat);
        BPFdestxt.setText(desc);
    }
    public void onClickback2(View view){
        Intent intent = new Intent(this, BFP.class);
        startActivity(intent);
    }
    public void onClickSaveRecord(View view){
        insert();
    }
    public void insert(){

        try{

            String Qbfp = BPFvaluetxt.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS bfprecords(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,bfp VARCHAR)");

            String sql = "insert into bfprecords(name,bfp)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,val2);
            statement.bindString(2,Qbfp);
            statement.execute();
            Toast.makeText(this,"Record addded",Toast.LENGTH_LONG).show();
        }

        catch (Exception ex){
            Toast.makeText(this,"Faild",Toast.LENGTH_LONG).show();
        }
    }
    public void onClickBFP_records(View view){

        Intent intent = new Intent(this, BFP_records.class);
        startActivity(intent);
    }
}