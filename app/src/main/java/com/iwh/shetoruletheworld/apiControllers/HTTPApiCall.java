package com.iwh.shetoruletheworld.apiControllers;

import android.annotation.SuppressLint;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SrujanaParupudi on 2/20/2018.
 */

public abstract class HTTPApiCall {

    public abstract void doCall();

    public abstract String getURL();

    Object doCallInThread() {
        final String outputB[] = new String[1];
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    @SuppressLint("DefaultLocale")
                    URL url = new URL(getURL() + "?" + getParameters());

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream response = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader responseReader = new InputStreamReader(response);
                    BufferedReader output = new BufferedReader(responseReader);
                    String temp;StringBuilder responseAsString = new StringBuilder();
                    while((temp = output.readLine())!= null) {
                        responseAsString.append(temp);
                    }
                    System.out.println(responseAsString.toString());
                    outputB[0] = responseAsString.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    outputB[0] = "No O/P";
                }
            }
        });
        int timePass = 0;
        thread.start();
        while(thread.isAlive()){
            timePass = (timePass++)%10;
        }
        return outputB[0];
    }

    public abstract String getParameters();
}
