package com.maiajam.sofa.data.restApi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyRequestQueue  {


    static MyRequestQueue mInstance;
    Context context ;
    RequestQueue mRequestQueue ;



    MyRequestQueue(Context con)
    {
        context = con ;
    }

    public static MyRequestQueue getInstance(Context context)
    {

        if(mInstance == null)
        {
            mInstance = new MyRequestQueue(context);
        }

        return mInstance ;
    }


    public RequestQueue getmRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue ;
    }


    public void AddToRequestQueu(Request request)
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        mRequestQueue.add(request);
    }


}
