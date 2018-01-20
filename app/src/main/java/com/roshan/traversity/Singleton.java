package com.roshan.traversity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by arobc on 1/19/2018.
 */

public class Singleton {

    private static Singleton instance;
    private RequestQueue requestQueue;
    private static Context Scontext;

    private Singleton(Context context){
        Scontext = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){

            requestQueue = Volley.newRequestQueue(Scontext.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized Singleton getInstance(Context context){

        if(instance==null){

            instance = new Singleton(context);

        }
        return instance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
