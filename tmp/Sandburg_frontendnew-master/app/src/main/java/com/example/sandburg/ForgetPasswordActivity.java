package com.example.sandburg;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sandburg.Constants.*;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText email;
    Button btnSendOTP;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpwd_activity);

        setTitle("Forget Password");

        email = findViewById(R.id.forgetpwd_email);
        btnSendOTP = findViewById(R.id.send_otp_forgetpwd);

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                if (Validation.isEmpty(email.getText().toString())) {
                    email.setError("Enter Email");
                    flag = false;
                }

                if (flag) {
                    RequestQueue requestQueue = Volley.newRequestQueue(ForgetPasswordActivity.this);
                    String url = Constant.URL + "forgetpassword";

                    sharedPreferences = getSharedPreferences("Sandburg", MODE_PRIVATE);
                    editor = sharedPreferences.edit();



                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

//                            Log.e("ForgetPassword","otp email "+email.getText().toString());
                            editor.putString("email",email.getText().toString());
                            editor.commit();

                            Intent intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
//                           finish();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ForgetPassword", "Error :: " + error.toString());
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<>();
                            params.put("email", email.getText().toString());
                            return params;
                        }
                    };

                    requestQueue.add(stringRequest);

                }
            }
        });


    }
}
