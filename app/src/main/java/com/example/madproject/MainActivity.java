package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
public void onClick(View view){
    Intent intent = new Intent(this, BMI.class);
    startActivity(intent);
        }
    public void onClick1(View view){
        Intent intent = new Intent(this, THR.class);
        startActivity(intent);
    }
    public void onClick2(View view){
        Intent intent = new Intent(this, BFP.class);
        startActivity(intent);
    }
    public void onClick3(View view){
        Intent intent = new Intent(this, BMR.class);
        startActivity(intent);
    }

}