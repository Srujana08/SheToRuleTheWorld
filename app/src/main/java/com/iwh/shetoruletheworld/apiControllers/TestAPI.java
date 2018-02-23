package com.iwh.shetoruletheworld.apiControllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This Class implements the HTTPApiCall interface..
 * the interface has the logic to do the network call operations..
 *
 * In the implemented class you just provide the..
 * {@link #name}, {@link #id} get parameters..
 * and the url in {@link #getURL()}
 * and the get parameters structure in getParameters {@link #getParameters()}..
 *
 * Soo... call the method like..
 * <code>
 *     TestAPI testAPI = new TestAPI();
 *     testAPI.name = "baba is great :P";
 *     testAPI.id = 100;
 *     // response will have the response we print in the PHP API..
 *     String response = testAPI.response;
 * </code>
 * Got it
 * so here you are giving it statically? otherwise i have to tale name and id from the form! anthena?
 * Yup.. for example.. if you are using signup api....
 *
 * even docall is not required,.. moved it to constructor
 */

public class TestAPI extends HTTPApiCall {

    public String name;
    public Integer id;
    public String response;

    public TestAPI() {
        this.doCall();
    }

    @Override
    public void doCall() {
        response = doCallInThread().toString();
    }

    @Override
    public String getURL() {
        return "http://gvpcse.helplena.co/api/testAPI.php";
    }

    @Override
    public String getParameters() {
        try {
            return String.format("msg=%s&id=%d", URLEncoder.encode(name, "UTF-8"), id);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
