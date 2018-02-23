package com.iwh.shetoruletheworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import static com.iwh.shetoruletheworld.Constants.flag;

public class Teach extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner subject = (Spinner)findViewById(R.id.subject);
        Spinner std = (Spinner)findViewById(R.id.std);
        Spinner teachingmode = (Spinner)findViewById(R.id.teachingmode);
        DatePicker date = (DatePicker)findViewById(R.id.date);
        TimePicker time = (TimePicker)findViewById(R.id.time);
        EditText ids = (EditText)findViewById(R.id.ids);
        Button Btnsend = (Button)findViewById(R.id.Btnsend);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subject, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(adapter1);

        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String id = subject.get(position).getId();
            }
        });
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.std, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        std.setAdapter(adapter2);

        std.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String id = std.get(position).getId();
            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.teachingmode, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teachingmode.setAdapter(adapter3);

        teachingmode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String id = teachingmode.get(position).getId();
            }
        });

        Btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            flag = "";

        }
        if (id == R.id.teach) {
            Toast.makeText(Teach.this, "You are on the same page", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.postjob) {
            Intent i = new Intent(this, PostJob.class);
            startActivity(i);
        }
        return true;
    }

}
