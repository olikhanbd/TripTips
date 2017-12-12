package com.nerdbugger.triptips.utils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by oli on 12/12/17.
 */

public class TripTipsController extends Application{

    public static final String TAG = TripTipsController.class.getSimpleName();
    private static TripTipsController controller;
    private RequestQueue queue;

    public static synchronized TripTipsController getController() {
        Log.e("Controller", "Getting Controller");

        return controller;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("BaxController", "Initializing");
        controller = this;
        //MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.app_id));
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
            Log.e("Controller", "Queue was null");

        } else {
            Log.e("Controller", "Queue was not null");
        }

        return queue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
        Log.e("Controller", "New Request Added with tag: " + tag);

    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
        Log.e("Controller", "New Request Added!");

    }

    public void cancelPendingRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
            Log.e("Controller", "Cancelling all requests with tag: " + tag);
        } else {
            Log.e("Controller", "Couldn't cancel request with tag as queue is null");
        }
    }
}
