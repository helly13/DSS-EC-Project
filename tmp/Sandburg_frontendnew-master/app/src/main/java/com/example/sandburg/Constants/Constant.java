package com.example.sandburg.Constants;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sandburg.LoginActivity;
//import com.example.sandburg.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class Constant {
    public static final int SPLASH = 1000;

    public static final String URL = "http://192.168.0.1023000/";

    public static final String duplicate = "Duplicate entry";
    public static final String USER_AGENT = "User_Agent";
    public static final String LAST_META_DATE = "LAST_META_DATE";
    public static final String UPDATEAPPDATA = "updateappdata";

    public static void logout(final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to Logout??");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("Sandburg", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.commit();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

//    public static void logoutuser(final Context context)
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setMessage("Are you sure you want to Logout??");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                SharedPreferences sharedPreferences = context.getSharedPreferences("Sandburg", MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.remove("email");
//                editor.commit();
//
//                SharedPreferences sharedPreferences1 = context.getSharedPreferences("USERDATA", MODE_PRIVATE);
//
//                String email = sharedPreferences1.getString("email","");
//                String url = Constant.URL + "usersocietylogin" + "/" + secretaryPhoneNumber + "/" + userPhonenUmber;
//                RequestQueue requestQueue =Volley.newRequestQueue(context);
//                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                requestQueue.add(stringRequest);
//                SharedPreferences.Editor editor1 = sharedPreferences.edit();
//                editor1.remove("blockNumber");
//                editor.remove("flatName");
//                editor1.remove("userPhoneNumber");
//                editor1.remove("secretaryPhoneNumber");
//                editor1.remove("userName");
//                editor1.commit();
//
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                context.startActivity(intent);
//                ((Activity)context).finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//    }



}