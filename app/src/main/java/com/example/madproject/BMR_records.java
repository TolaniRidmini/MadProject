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

public class BMR_records extends AppCompatActivity {

    ListView bmrList;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr_records);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);

        bmrList = findViewById(R.id.bmrList);
        final Cursor c = db.rawQuery("select * from bmrrecords",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int bmr = c.getColumnIndex("bmr");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        bmrList.setAdapter(arrayAdapter);
        final  ArrayList<BMRclass> BMRobj1 = new ArrayList<BMRclass>();

        if(c.moveToFirst())
        {
            do{
                BMRclass BMRobj2 = new BMRclass();
                BMRobj2.id = c.getString(id);
                BMRobj2.name = c.getString(name);
                BMRobj2.bmr = c.getString(bmr);
                BMRobj1.add(BMRobj2);

                titles.add(" \t " + c.getString(id) + " \t " + " \t " + c.getString(name) + " \t " + " \t " + " \t " + c.getString(bmr) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            bmrList.invalidateViews();
        }

        bmrList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                //Toast.makeText(getApplicationContext(),aa,Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(BMR_records.this);
                builder.setTitle("Delete");
                builder.setMessage("Do you want to delete this record?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BMRclass bmrcls = BMRobj1.get(position);
                        String sid = bmrcls.id;
                        deleteBMRrecord(sid);
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
    public void deleteBMRrecord(String id){
        try
        {
            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

            String sql = "delete from bmrrecords where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, BMR_records.class);
            startActivity(intent);

        }
        catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickbacktoAdd3(View view){
        Intent intent = new Intent(this, BMR.class);
        startActivity(intent);
    }
}