package com.iwh.shetoruletheworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iwh.shetoruletheworld.apiControllers.SignupAPI;
import com.iwh.shetoruletheworld.apiControllers.TestAPI;

import static com.iwh.shetoruletheworld.Constants.SUCCESSFULLY_REGISTERED;
import static com.iwh.shetoruletheworld.Constants.USER_ALREADY_EXISTS;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText Name = (EditText)findViewById(R.id.Name);
        final EditText Phone = (EditText)findViewById(R.id.Phone);
        final EditText Password = (EditText)findViewById(R.id.Password);
        Button BtnSignup = (Button)findViewById(R.id.BtnSignup);
        TextView BtnLogin = (TextView) findViewById(R.id.BtnLogin);
        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph = Phone.getText().toString();
                if(ph.length() != 10){
                    Toast.makeText(Signup.this, "Please give a valid phone number", Toast.LENGTH_SHORT).show();
                }else {


                    SignupAPI signupAPI = new SignupAPI();
                    signupAPI.name = Name.getText().toString();
                    signupAPI.phone = Phone.getText().toString();
                    signupAPI.password = Password.getText().toString();
                    signupAPI.doCall();
                    String apiResponse = signupAPI.response;
                    if (apiResponse.equals(USER_ALREADY_EXISTS)) {
                        Toast.makeText(Signup.super.getApplicationContext(), "You have already registered! Please login", Toast.LENGTH_SHORT).show();
                    } else if (apiResponse.equals(SUCCESSFULLY_REGISTERED)) {
                        Toast.makeText(Signup.super.getApplicationContext(), "Registered successfully..", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent("com.iwh.shetoruletheworld.Login");
                        startActivity(i);
                    }
                }
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("com.iwh.shetoruletheworld.Login");
                startActivity(i);
            }
        });
    }
}
