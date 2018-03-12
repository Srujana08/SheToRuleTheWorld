package com.iwh.shetoruletheworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iwh.shetoruletheworld.apiControllers.PostJobAPI;
import com.iwh.shetoruletheworld.apiControllers.TeachAPI;

import static com.iwh.shetoruletheworld.Constants.FRAGMENTS_TAB_OVERRIDE_KEY;
import static com.iwh.shetoruletheworld.Constants.flag;

public class Teach extends AppCompatActivity {
    CalendarView calendar;
    String sub,strd,tmode,uids, phone, month;
    int day,year;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final Spinner subject = (Spinner)findViewById(R.id.subject);
        final Spinner std = (Spinner)findViewById(R.id.std);
        final Spinner teachingmode = (Spinner)findViewById(R.id.teachingmode);
        DatePicker date = (DatePicker)findViewById(R.id.date);
        //calendar = (CalendarView)findViewById(R.id.calendar);
        final EditText ids = (EditText)findViewById(R.id.ids);
        Button Btnsend = (Button)findViewById(R.id.Btnsend);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subject, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(adapter1);

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sub = subject.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.std, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        std.setAdapter(adapter2);

        std.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strd = std.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.teachingmode, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teachingmode.setAdapter(adapter3);

        teachingmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tmode = teachingmode.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //uids = ids.getText().toString();
        day = date.getDayOfMonth();
        month = getMonth(date.getMonth() + 1);
        year = date.getYear();
        /*calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                day = i2;
                month = getMonth(i1);
                year = i;
            }
        });*/

        final SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Subject", sub);
        editor.putString("Standard", strd);
        editor.putString("Teaching Mode", tmode);
        //editor.putString("ID", uids);
        editor.putInt("Day",day);
        editor.putString("Month",month);
        editor.putInt("Year",year);
        editor.apply();

        SharedPreferences sp = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
        phone = sp.getString("phone","");

        Btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uids = ids.getText().toString();
                editor.putString("ID", uids);
                if (sub.equals("Choose subject") || strd.equals("Choose class") || tmode.equals("Choose mode of teaching") || uids.equals("")) {
                    Toast.makeText(Teach.super.getApplicationContext(), "Please fill the fields", Toast.LENGTH_SHORT).show();

                } else {


                TeachAPI teachAPI = new TeachAPI();
                teachAPI.phone = phone;
                teachAPI.subject = sub;
                teachAPI.strd = strd;
                teachAPI.tmode = tmode;
                teachAPI.ids = uids;
                teachAPI.day = day;
                teachAPI.month = month;
                teachAPI.year = year;
                teachAPI.doCall();
                String apiResponse = teachAPI.response;
                if (apiResponse.equals("Notification sent..")) {
                    Toast.makeText(Teach.super.getApplicationContext(), "Notification sent", Toast.LENGTH_SHORT).show();
                }
                Intent educationIntent = new Intent(Teach.super.getApplicationContext(), Education.class);

                educationIntent.putExtra(FRAGMENTS_TAB_OVERRIDE_KEY, 1);
                startActivity(educationIntent);
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
            Toast.makeText(Teach.this, "You are on the same page", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.postjob) {
            Intent i = new Intent(this, PostJob.class);
            startActivity(i);
        }
        return true;
    }

    public String getMonth(int num){
        if(num == 1)
            return "January";
        else if(num == 2)
            return "February";
        else if(num == 3)
            return "March";
        else if(num == 4)
            return "April";
        else if(num == 5)
            return "May";
        else if(num == 6)
            return "June";
        else if(num == 7)
            return "July";
        else if(num == 8)
            return "August";
        else if(num == 9)
            return "September";
        else if(num == 10)
            return "October";
        else if(num == 11)
            return "November";
        else
            return "December";
    }
}
