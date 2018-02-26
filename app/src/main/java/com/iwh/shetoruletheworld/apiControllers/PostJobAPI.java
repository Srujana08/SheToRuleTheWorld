package com.iwh.shetoruletheworld.apiControllers;

import android.annotation.SuppressLint;

import java.net.URLEncoder;

/**
 * Created by SrujanaParupudi on 2/26/2018.
 */

public class PostJobAPI extends HTTPApiCall {
    public String salary, location, jobrole, phone, name;

    public String response;

    @Override
    public void doCall() {
        response = doCallInThread().toString();
    }

    @Override
    public String getURL() {
        return "http://gvpcse.helplena.co/api/postJob.php";
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getParameters() {
        // these are wrong..... ans have URLEncoded to string with spaces..
        return String.format("phone=%s&jobrole=%s&salary=%s&location=%s",phone,jobrole,salary,location);
    }
}
