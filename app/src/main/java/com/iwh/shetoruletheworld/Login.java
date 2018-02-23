package com.iwh.shetoruletheworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iwh.shetoruletheworld.apiControllers.LoginAPI;
import com.iwh.shetoruletheworld.apiControllers.SignupAPI;

import static com.iwh.shetoruletheworld.Constants.INVALID_CREDS;
import static com.iwh.shetoruletheworld.Constants.NOT_A_USER;
import static com.iwh.shetoruletheworld.Constants.SUCCESSFULLY_LOGGED_IN;
import static com.iwh.shetoruletheworld.Constants.SUCCESSFULLY_REGISTERED;
import static com.iwh.shetoruletheworld.Constants.USER_ALREADY_EXISTS;
import static com.iwh.shetoruletheworld.Constants.flag;

public class Login extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String phone = "phone";
    public static final String password = "password";

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText Phone = (EditText)findViewById(R.id.Phone);
        final EditText Password = (EditText)findViewById(R.id.Password);
        Button BtnSignup = (Button)findViewById(R.id.BtnSignup);
        Button BtnLogin = (Button)findViewById(R.id.BtnLogin);


        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("com.iwh.shetoruletheworld.Signup");
                startActivity(i);
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAPI loginAPI = new LoginAPI();
                loginAPI.phone = Phone.getText().toString();
                loginAPI.password = Password.getText().toString();
                sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(phone, Phone.getText().toString());
                editor.putString(password, Password.getText().toString());
                editor.commit();
                flag = "notnull";
                loginAPI.doCall();
                String apiResponse = loginAPI.response;
                if (apiResponse.equals(NOT_A_USER)) {
                    Toast.makeText(Login.super.getApplicationContext(), "You are not a user! Please sign up", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent("com.iwh.shetoruletheworld.Signup");
                    startActivity(i);
                }
                else if (apiResponse.equals(SUCCESSFULLY_LOGGED_IN)){
                    Toast.makeText(Login.super.getApplicationContext(), "Logged in successfully..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent("com.iwh.shetoruletheworld.EduOrEnt");
                    startActivity(i);
                }
                else if(apiResponse.equals(INVALID_CREDS)){
                    Toast.makeText(Login.super.getApplicationContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
