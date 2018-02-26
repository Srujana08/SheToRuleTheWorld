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
import android.widget.Spinner;
import android.widget.Toast;

import com.iwh.shetoruletheworld.apiControllers.PostJobAPI;
import com.iwh.shetoruletheworld.apiControllers.SignupAPI;

import static com.iwh.shetoruletheworld.Constants.SUCCESSFULLY_REGISTERED;
import static com.iwh.shetoruletheworld.Constants.USER_ALREADY_EXISTS;
import static com.iwh.shetoruletheworld.Constants.flag;

public class PostJob extends AppCompatActivity {
    String jrole, sal, loc, phone;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final Spinner jobrole = (Spinner)findViewById(R.id.jobrole);
        final Spinner salary = (Spinner)findViewById(R.id.salary);
        final Spinner location= (Spinner)findViewById(R.id.location);
        Button Btnsend = (Button)findViewById(R.id.Btnsend);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.jobrole, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobrole.setAdapter(adapter1);

        jobrole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jrole = jobrole.getItemAtPosition(i).toString();
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.salary, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salary.setAdapter(adapter2);

        salary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String id = salary.get(position).getId();
                sal = salary.getItemAtPosition(i).toString();
            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter3);

        location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            loc = location.getItemAtPosition(i).toString();
            }
        });

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("JobRole", jrole);
        editor.putString("Salary", sal);
        editor.putString("Location", loc);
        editor.commit();

        SharedPreferences sp = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        phone = sp.getString("phone","");
        Btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostJobAPI postJobAPI = new PostJobAPI();
                postJobAPI.phone = phone;
                postJobAPI.jobrole = jrole;
                postJobAPI.salary = sal;
                postJobAPI.location = loc;
                postJobAPI.doCall();
                String apiResponse = postJobAPI.response;
                if (apiResponse.equals("Notification sent..")) {
                    Toast.makeText(PostJob.super.getApplicationContext(), "Notification sent", Toast.LENGTH_SHORT).show();
                }

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
            Intent i = new Intent(this, Teach.class);
            startActivity(i);
        }
        if (id == R.id.postjob) {
            Toast.makeText(PostJob.this, "You are on the same page", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

}
