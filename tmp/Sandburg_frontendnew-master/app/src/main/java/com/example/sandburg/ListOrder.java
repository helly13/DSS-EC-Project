package com.example.sandburg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sandburg.Constants.AddData;
import com.example.sandburg.Constants.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.example.sandburg.Constants.Utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class ListOrder extends LocalActivity {

   static ArrayList<OrderModel> order_data = new ArrayList<OrderModel>();
    RecyclerView recycler_view;
   static Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        context =getActivity();
        recycler_view = findViewById(R.id.recycler_view);
        WebserviceCall();
    }

    private void WebserviceCall() {
        if (Utils.isInternetConnected(getActivity())) {
            try {
                RequestParams params = new RequestParams();
                params.put("", "");
                AsyncHttpClient clientPhotos = new AsyncHttpClient();
                clientPhotos.setTimeout(60 * 1000);
                clientPhotos.setEnableRedirects(true);
                clientPhotos.setUserAgent(Utils.getPref(getActivity(), Constant.USER_AGENT, ""));
                clientPhotos.get(getActivity(), "http://192.168.70.1:3000/view", params, new MyUpdateAppDataResponseHandler());

            } catch (Exception ignored) {

            }
        }
    }

    private class MyUpdateAppDataResponseHandler extends AsyncHttpResponseHandler {

        @Override
        public void onStart() {
            super.onStart();
        }


        @Override
        public void onFinish() {
            super.onFinish();
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            final String response;
            try {

                response = new String(responseBody, "UTF-8");

                if (response.length() > 0) {
                    Log.e("List", "UpdateAppData response:" + response);

                    final GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    AddData[] testCase = gson.fromJson(response, AddData[].class);

                    order_data.clear();
                    Log.e("List", "UpdateAppData response SIZE:" +  testCase.length);
                    for (int i = 0; i < testCase.length; i++) {
                        //String test = testCase[i].Menu.Item_name;
                        order_data.add(new OrderModel(testCase[i]._id.toString(),testCase[i].Menu.Item_name,
                                testCase[i].Total_qty,testCase[i].Table_no));
                    }
                    setRecycleView();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onFailure(int statuscode, Header[] header, byte[] bytes, Throwable error) {
            Log.e("List-", "error:" + error.getMessage());

        }
    }

    static int rvimPositionIF;
    static View ChildView1IF;
    static Adp_Order aDPimageF;
    static String orderid ,itemname, qty,tableno;


    private  void setRecycleView() {
        recycler_view.setLayoutManager(new GridLayoutManager(context, 1));
        aDPimageF = new Adp_Order(context);
        aDPimageF.addAll(order_data);
        recycler_view.setAdapter(aDPimageF);
        recycler_view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
                ChildView1IF = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (ChildView1IF != null && gestureDetector.onTouchEvent(motionEvent)) {
                    rvimPositionIF = Recyclerview.getChildAdapterPosition(ChildView1IF);
                    orderid = order_data.get(rvimPositionIF).orderID;
                    itemname = order_data.get(rvimPositionIF).ItemName;
                    qty = order_data.get(rvimPositionIF).Qty;
                    tableno = order_data.get(rvimPositionIF).TableNo;

                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
    }


    public static void CliclItems(int pos, int type) {
        orderid = order_data.get(rvimPositionIF).orderID;
        itemname = order_data.get(rvimPositionIF).ItemName;
        qty = order_data.get(rvimPositionIF).Qty;
        tableno = order_data.get(rvimPositionIF).TableNo;

        if(type==1){
            order_data.remove(pos);
            aDPimageF.addAll(order_data);
            Toast.makeText( context, "Accept Click", Toast.LENGTH_SHORT).show();

        }else {
            order_data.remove(pos);
            aDPimageF.addAll(order_data);
            Toast.makeText( context, "Reject Click", Toast.LENGTH_SHORT).show();
            String url = "http://192.168.70.1:3000/order_delete";
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);


            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    order_data.clear();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(stringRequest);


        }


    }


}
