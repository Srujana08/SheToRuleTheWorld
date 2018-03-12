package com.iwh.shetoruletheworld.apiControllers;

import android.annotation.SuppressLint;

/**
 * Created by Buffalo on 2/27/2018.
 *
 */

public class EntNotificationAPI extends HTTPApiCall {
    //public String phone, jobrole, salary, location;

    public String response;

    @Override
    public void doCall() {
        response = doCallInThread().toString();
    }

    @Override
    public String getURL() {
        return "http://gvpcse.helplena.co/api/entNotification.php";
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getParameters() {
        // these are wrong..... ans have URLEncoded to string with spaces..
        //return String.format("phone=%s&jobrole=%s&salary=%s&location=%s",phone, jobrole, salary, location);
        return null;
    }
}
