package it.step.databaseinandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDB extends AppCompatActivity {


    ListView listOne;
    Button buttonAdd;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        buttonAdd = findViewById(R.id.buttonAdd);

        SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);

        listOne = findViewById(R.id.listOne);
        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int deviceName = c.getColumnIndex("deviceName");
        int model = c.getColumnIndex("model");
        int problems = c.getColumnIndex("problems");
        titles.clear();


        arrayAdapter = new ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,titles);
        listOne.setAdapter(arrayAdapter);

        final  ArrayList<Device> devices = new ArrayList<Device>();


        if(c.moveToFirst())
        {
            do{
                Device device = new Device();
                device.id = c.getString(id);
                device.deviceName = c.getString(deviceName);
                device.model = c.getString(model);
                device.problems = c.getString(problems);
                devices.add(device);

                titles.add("Заказ №"+c.getString(id));

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            listOne.invalidateViews();



        }

        listOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String aa = titles.get(position).toString();
                Device device = devices.get(position);
                Intent in = new Intent(getApplicationContext(),Edit.class);
                in.putExtra("id",device.id);
                in.putExtra("deviceName",device.deviceName);
                in.putExtra("model",device.model);
                in.putExtra("problems",device.problems);
                startActivity(in);

            }
        });
        buttonAdd.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}