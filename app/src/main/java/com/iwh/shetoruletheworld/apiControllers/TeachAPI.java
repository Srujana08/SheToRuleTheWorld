package com.iwh.shetoruletheworld.apiControllers;

import android.annotation.SuppressLint;

/**
 * Created by SrujanaParupudi on 3/6/2018.
 */

public class TeachAPI extends HTTPApiCall {
    public String subject, strd, tmode, ids, phone, month;
    public int day,year;

    public String response;

    @Override
    public void doCall() {
        response = doCallInThread().toString();
    }

    @Override
    public String getURL() {
        return "http://gvpcse.helplena.co/api/teach.php";
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getParameters() {
        // these are wrong..... ans have URLEncoded to string with spaces..
        return String.format("phone=%s&subject=%s&strd=%s&tmode=%s&ids=%s&day=%d&month=%s&year=%d",phone, subject, strd, tmode, ids, day, month, year);
    }
}
