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

public class THR_records extends AppCompatActivity {

    ListView THRlist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_records);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);

        THRlist = findViewById(R.id.THRlist);
        final Cursor c = db.rawQuery("select * from thrrecords",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int thr = c.getColumnIndex("thr");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        THRlist.setAdapter(arrayAdapter);
        final  ArrayList<THRclass> THRobj1 = new ArrayList<THRclass>();

        if(c.moveToFirst())
        {
            do{
                THRclass THRobj2 = new THRclass();
                THRobj2.id = c.getString(id);
                THRobj2.name = c.getString(name);
                THRobj2.thr = c.getString(thr);
                THRobj1.add(THRobj2);

                titles.add(" \t " + c.getString(id) + " \t " + " \t " + c.getString(name) + " \t " + " \t " + " \t " + c.getString(thr) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            THRlist.invalidateViews();
        }

        THRlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                //Toast.makeText(getApplicationContext(),aa,Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(THR_records.this);
                builder.setTitle("Delete");
                builder.setMessage("Do you want to delete this record?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        THRclass thrcls = THRobj1.get(position);
                        String sid = thrcls.id;
                        deleteTHRrecord(sid);
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
    public void deleteTHRrecord(String id){
        try
        {
            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

            String sql = "delete from thrrecords where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, THR_records.class);
            startActivity(intent);

        }
        catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
    public void onClickbacktoAdd1(View view){
        Intent intent = new Intent(this, THR.class);
        startActivity(intent);
    }
}