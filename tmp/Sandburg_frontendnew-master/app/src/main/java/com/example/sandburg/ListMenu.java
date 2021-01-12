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
import com.example.sandburg.Constants.AddData1;
import com.example.sandburg.Constants.CategoryData;
import com.example.sandburg.Constants.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.example.sandburg.Constants.Utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class ListMenu extends LA1 {

    static ArrayList<MenuModel> menu_data = new ArrayList<>();

    RecyclerView recycler_view1;
    static Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        context =getActivity1();
        recycler_view1 = findViewById(R.id.recycler_view1);
        WebserviceCall();
    }

    private void WebserviceCall() {
        if (Utils.isInternetConnected(getActivity1())) {
            try {
                RequestParams params = new RequestParams();
                params.put("", "");
                AsyncHttpClient clientPhotos = new AsyncHttpClient();
                clientPhotos.setTimeout(60 * 1000);
                clientPhotos.setEnableRedirects(true);
                clientPhotos.setUserAgent(Utils.getPref(getActivity1(), Constant.USER_AGENT, ""));
                clientPhotos.get(getActivity1(), "http://192.168.70.1:3000/view1", params, new MyUpdateAppDataResponseHandler());

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
               AddData1[] testCase = gson.fromJson(response, AddData1[].class);
                    for (int i = 0; i < testCase.length; i++) {
                        //String test = testCase[i].Menu.Item_name;
                       for(CategoryData catgoryData: testCase[i].categoryList)
                        {
                            Log.d("List", "List" +catgoryData.toString());
                            Log.d("List","List" + testCase[i].Item_name);
                            Log.d("List","List" + testCase[i].Price);
                            Log.d("List","List" + testCase[i].Status);
                            Log.d("List","List" + testCase[i].Type_of_Food);

                            menu_data.add(new MenuModel(catgoryData.Category_Id,catgoryData.Category_Name, testCase[i].Price,testCase[i].Item_name,testCase[i].Status, testCase[i].Type_of_Food));

                        }

                    }
//

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
    static Adp_Menu aDPimageF;
    static List<CategoryData> catlist;
    static String catid, catname, price, item_name, stat, type1;


    private  void setRecycleView() {
        recycler_view1.setLayoutManager(new GridLayoutManager(context, 1));
        aDPimageF = new Adp_Menu(context);
        aDPimageF.addAll(menu_data);
        recycler_view1.setAdapter(aDPimageF);
        recycler_view1.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
//                    catlist = menu_data.get(rvimPositionIF).categoryList;
                  catid = menu_data.get(rvimPositionIF).CatId;
                  catname = menu_data.get(rvimPositionIF).CatName;
                    price = menu_data.get(rvimPositionIF).Price;
                    item_name = menu_data.get(rvimPositionIF).itemName;
                    stat = menu_data.get(rvimPositionIF).Stat;
                    type1 = menu_data.get(rvimPositionIF).Type;
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


     static void CliclItems(int pos, int type) {

        catid = menu_data.get(rvimPositionIF).CatId;
        catname = menu_data.get(rvimPositionIF).CatName;
        price = menu_data.get(rvimPositionIF).Price;
        item_name = menu_data.get(rvimPositionIF).itemName;
        stat = menu_data.get(rvimPositionIF).Stat;
        type1 = menu_data.get(rvimPositionIF).Type;

        if(type==1){

            Toast.makeText( context, "Accect Click", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(context, UpdateMenu.class);
            intent1.putExtra("catid",catid);
            intent1.putExtra("catname",catname);
            intent1.putExtra("price",price);
            intent1.putExtra("item_name",item_name);
            intent1.putExtra("stat",stat);
            intent1.putExtra("type1",type1);

            context.startActivity(intent1);


        }else {
            menu_data.remove(pos);
            aDPimageF.addAll(menu_data);
            Toast.makeText( context, "Reject Click", Toast.LENGTH_SHORT).show();

            String url = "http://192.168.70.1:3000/menu_delete";
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(context);


            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    menu_data.clear();
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
