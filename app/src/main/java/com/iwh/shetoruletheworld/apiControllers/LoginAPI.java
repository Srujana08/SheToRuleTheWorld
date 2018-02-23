package com.iwh.shetoruletheworld.apiControllers;

import android.annotation.SuppressLint;

import java.net.URLEncoder;

/**
 * Created by SrujanaParupudi on 2/22/2018.
 */

public class LoginAPI extends HTTPApiCall {
    public String password, phone;

    public String response;

    @Override
    public void doCall() {
        response = doCallInThread().toString();
    }

    @Override
    public String getURL() {
        return "http://gvpcse.helplena.co/api/login.php";
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getParameters() {
        // these are wrong..... ans have URLEncoded to string with spaces..
        return String.format("password=%s&phone=%s",password, phone);
    }
}
