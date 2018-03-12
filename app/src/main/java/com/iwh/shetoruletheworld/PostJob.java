package com.iwh.shetoruletheworld;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import static com.iwh.shetoruletheworld.Constants.FRAGMENTS_TAB_OVERRIDE_KEY;
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
        Button btnsend = (Button)findViewById(R.id.Btnsend);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.jobrole, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobrole.setAdapter(adapter1);

        jobrole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jrole = jobrole.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.salary, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salary.setAdapter(adapter2);

        salary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String id = salary.get(position).getId();
                sal = salary.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter3);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loc = location.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("JobRole", jrole);
        editor.putString("Salary", sal);
        editor.putString("Location", loc);
        editor.apply();

        SharedPreferences sp = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        phone = sp.getString("phone","");
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jrole.equals("Choose role") || sal.equals("Choose salary") || loc.equals("Choose location")){
                    Toast.makeText(PostJob.super.getApplicationContext(),"Please fill the fields", Toast.LENGTH_SHORT).show();
                }else {


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
                    Intent entrepreneurShipIntent = new Intent(PostJob.super.getApplicationContext(), Entrepreneurship.class);

                    entrepreneurShipIntent.putExtra(FRAGMENTS_TAB_OVERRIDE_KEY, 1);
                    startActivity(entrepreneurShipIntent);
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
