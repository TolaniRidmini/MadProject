package com.example.madproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BMI_records extends AppCompatActivity {

    ListView bmiList;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_records);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);

        bmiList = findViewById(R.id.bmiList);
        final Cursor c = db.rawQuery("select * from bmirecords",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int bmi = c.getColumnIndex("bmi");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        bmiList.setAdapter(arrayAdapter);
        final  ArrayList<BMIclass> BMIobj1 = new ArrayList<BMIclass>();

        if(c.moveToFirst())
        {
            do{
                BMIclass BMIobj2 = new BMIclass();
                BMIobj2.id = c.getString(id);
                BMIobj2.name = c.getString(name);
                BMIobj2.bmi = c.getString(bmi);
                BMIobj1.add(BMIobj2);

                titles.add(" \t " + c.getString(id) + " \t " + " \t " + c.getString(name) + " \t " + " \t " + " \t " + c.getString(bmi) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            bmiList.invalidateViews();
        }

        bmiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                //Toast.makeText(getApplicationContext(),aa,Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(BMI_records.this);
                builder.setTitle("Delete");
                builder.setMessage("Do you want to delete this record?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BMIclass bmicls = BMIobj1.get(position);
                        String sid = bmicls.id;
                        deleteBMIrecord(sid);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.create().show();
            }
        });
    }
    public void deleteBMIrecord(String id){
        try
        {
            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

            String sql = "delete from bmirecords where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, BMI_records.class);
            startActivity(intent);

        }
        catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickbacktoAdd(View view){
        Intent intent = new Intent(this, BMI.class);
        startActivity(intent);
    }
}