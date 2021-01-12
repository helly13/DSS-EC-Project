package com.example.sandburg;

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

public class SignupActivity extends AppCompatActivity {

    EditText edtNameSignup, edtPasswordSignup, edtConfirmSignup, edtMobileSignup, edtEmailSignup;
    Button btnsignup,btnback;
    boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Log.e("Signup","Iiiiiiiiiiiinnnnnn Signup");

        edtNameSignup = findViewById(R.id.edt_name_signup);
        edtPasswordSignup = findViewById(R.id.edt_password_signup);
        edtConfirmSignup = findViewById(R.id.edt_confirm_signup);
        edtMobileSignup = findViewById(R.id.edt_mobile_signup);
        edtEmailSignup=findViewById(R.id.edt_email_signup);


        btnsignup=findViewById(R.id.btn_signup);
        btnback=findViewById(R.id.btn_loginback_signup);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = edtNameSignup.getText().toString();
                final String password = edtPasswordSignup.getText().toString();
                String confirm = edtConfirmSignup.getText().toString();
                final String mobile = edtMobileSignup.getText().toString();
                final String email = edtEmailSignup.getText().toString();
                flag = true;


                if (Validation.isEmpty(name)) {
                    edtNameSignup.setError("Enter Name");
                    flag = false;
                }
                if (password.length() <= 7) {
                    edtPasswordSignup.setError("Enter password of atleast 8 characters");
                    flag = false;
                }
                if (!(password.equals(confirm))) {
                    edtConfirmSignup.setError("Password do not match");
                    flag = false;
                }
                if (Validation.isEmpty(mobile)) {
                    edtMobileSignup.setError("Enter Mobile Number");
                    flag = false;
                }
                if (mobile.length() != 10) {
                    edtMobileSignup.setError("Enter 10 digit mobile number");
                    flag = false;
                }
                if (Validation.isEmpty(email)) {
                    edtEmailSignup.setError("Enter Email");
                    flag = false;
                }

                if(flag)
                {
                    RequestQueue crequestQueue=Volley.newRequestQueue(SignupActivity.this);
                    String curl=Constant.URL+"cust/"+email+"/"+mobile;
                    StringRequest cstringRequest=new StringRequest(Request.Method.GET, curl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray cjsonArray=new JSONArray(response);
                                Log.e("Signup","Res :: "+response);
                                Log.e("Signup","len  :: "+cjsonArray.length());

                                if(cjsonArray.length()>0)
                                {
                                    Toast.makeText( SignupActivity.this, "Email or Mobile Number is already registered", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Log.e("Signup","Hellooo");
                                    RequestQueue requestQueue=Volley.newRequestQueue(SignupActivity.this);
                                    String surl=Constant.URL+"custsignup";
                                    StringRequest stringRequest=new StringRequest(Request.Method.POST, surl, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                                Log.e("Signup","Signupppp in completed");
                                                Toast.makeText( SignupActivity.this, "You have Successfully Registered...!!", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }){
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<>();
                                            params.put("mobile", mobile);
                                            params.put("name", name);
                                            params.put("email",email);
                                            params.put("password",password);
                                            return params;
                                        }

                                        @Override
                                        public Map<String, String> getHeaders() throws AuthFailureError {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put("Content-Type", "application/x-www-form-urlencoded");
                                            return params;
                                        }
                                    };
                                    requestQueue.add(stringRequest);


                                }

                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    crequestQueue.add(cstringRequest);
                }
            }
        });
    }
}
