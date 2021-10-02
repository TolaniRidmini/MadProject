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

public class BFP_records extends AppCompatActivity {

    ListView bfplist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfp_records);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
        // db.execSQL("CREATE TABLE IF NOT EXISTS bfprecords(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,bfp VARCHAR)");
        bfplist = findViewById(R.id.bfplist);
        final Cursor c = db.rawQuery("select * from bfprecords",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int bfp = c.getColumnIndex("bfp");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        bfplist.setAdapter(arrayAdapter);
        final  ArrayList<BFPclass> BFPobj1 = new ArrayList<BFPclass>();

        if(c.moveToFirst())
        {
            do{
                BFPclass BFPobj2 = new BFPclass();
                BFPobj2.id = c.getString(id);
                BFPobj2.name = c.getString(name);
                BFPobj2.bfp = c.getString(bfp);
                BFPobj1.add(BFPobj2);

                titles.add(" \t " + c.getString(id) + " \t " + " \t " + c.getString(name) + " \t " + " \t " + " \t " + c.getString(bfp) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            bfplist.invalidateViews();
        }

        bfplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                //Toast.makeText(getApplicationContext(),aa,Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(BFP_records.this);
                builder.setTitle("Delete");
                builder.setMessage("Do you want to delete this record?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BFPclass bfpcls = BFPobj1.get(position);
                        String sid = bfpcls.id;
                        deleteBFPrecord(sid);
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
    public void deleteBFPrecord(String id){
        try
        {
            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

            String sql = "delete from bfprecords where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Record Deleted",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, BFP_records.class);
            startActivity(intent);

        }
        catch (Exception ex) {
            Toast.makeText(this, "Record Fail", Toast.LENGTH_LONG).show();
        }
    }
    public void onClickbacktoAdd2(View view){
        Intent intent = new Intent(this, BFP.class);
        startActivity(intent);
    }
}