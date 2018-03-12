package com.iwh.shetoruletheworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.iwh.shetoruletheworld.Constants.flag;

public class FlashScreen extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPREFERENCES" ;
    String ph = "";
    String pwd = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView she, s;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        she = (TextView)findViewById(R.id.she);
        s = (TextView)findViewById(R.id.s);
        //getActionBar().hide();
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        she.startAnimation(myanim);
        s.startAnimation(myanim);
        final Intent i = new Intent(this, Signup.class);
        final Intent intent = new Intent(this, EduOrEnt.class);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                    ph = sharedpreferences.getString("phone","");
                    pwd = sharedpreferences.getString("password","");
                    if(flag == ""){
                        startActivity(i);
                    }
                    if(ph != "" && pwd != ""){
                        startActivity(intent);
                    }
                    else{
                        startActivity(i);
                    }
                    finish();
                }
            }
        };
        timer.start();
    }
}
