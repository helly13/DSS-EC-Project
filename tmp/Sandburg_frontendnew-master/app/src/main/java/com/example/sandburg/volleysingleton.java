package com.example.sandburg;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleysingleton {

    private static volleysingleton sInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private volleysingleton(Context context)
    {
        mCtx=context;
        requestQueue=getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue==null);
        requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }
    public static synchronized volleysingleton getInstance(Context context)
    {
        if (sInstance==null)
        {
            sInstance = new volleysingleton(context);
        }
    return sInstance;
    }

    public<T> void addToRequestQue(Request<T> request)
    {
        getRequestQueue().add(request);
    }

}
