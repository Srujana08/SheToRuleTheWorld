package com.iwh.shetoruletheworld.apiControllers;

import android.annotation.SuppressLint;

import java.net.URLEncoder;

/**
 * You just need to create the table, API in server..
 * I dont need separate java classes anymore?
 * One for login.. ome for signup..
 * each class for an API//
 *
 * Only API class is enough?
 * yes
 *
 * retrieve cheyalante?
 * API response lo retrieved data vostadhi kadhaa..
 * nuvvu asalu insert query ekkada raasav?
 * PHP api lo..
 * chupi
 */
public class SignupAPI extends HTTPApiCall {

    public String name, password, phone;

    public String response;

    @Override
    public void doCall() {
        response = doCallInThread().toString();
    }

    @Override
    public String getURL() {
        return "http://gvpcse.helplena.co/api/signUp.php";
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getParameters() {
        // these are wrong..... ans have URLEncoded to string with spaces..
        return String.format("name=%s&password=%s&phone=%s", URLEncoder.encode(name), password, phone);
    }
}
