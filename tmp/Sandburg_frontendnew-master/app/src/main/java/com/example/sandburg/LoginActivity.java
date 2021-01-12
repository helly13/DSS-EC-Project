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


public class LoginActivity extends AppCompatActivity{

    EditText edtUsernameLogin, edtPasswordLogin;
    Button btnLoginLogin, btnSignupLogin;
    TextView forgetPasswordLogin;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        setTitle("Login");

//        Log.e("Firebase", "token " + FirebaseInstanceId.getInstance().getToken());

//        textView = findViewById(R.id.txt_otp_login);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, LoginOTPActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        sharedPreferences = getSharedPreferences("Sandburg", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edtUsernameLogin = findViewById(R.id.edt_username_login);
        edtPasswordLogin = findViewById(R.id.edt_password_login);

        btnLoginLogin = findViewById(R.id.btn_login_login);
        btnSignupLogin = findViewById(R.id.btn_signup_login);

        forgetPasswordLogin=findViewById(R.id.forgotpasswordTextView_login);

//        btnSignupLogin.setOnClickListener(this);
//        btnLoginLogin.setOnClickListener(this);

        btnSignupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLoginLogin.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick (View v){
                final String username = edtUsernameLogin.getText().toString();
                final String password = edtPasswordLogin.getText().toString();

                boolean flag = true;
                if (com.example.sandburg.Constants.Validation.isEmpty(username)) {
                    edtUsernameLogin.setError("Enter Username");
                    flag = false;
                }
                if (com.example.sandburg.Constants.Validation.isEmpty(password)) {
                    edtPasswordLogin.setError("Enter Password");
                    flag = false;
                }

                if (flag) {
                    String url = Constant.URL + "custlogin";
                    final RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.e("Login","res :: "+response);
                                if(response.equals("null"))
                                    Toast.makeText(LoginActivity.this, "Invalid username or password or role", Toast.LENGTH_SHORT).show();

                                JSONArray jsonArray = new JSONArray(response);
                                if (jsonArray.length() == 0) {
                                    Log.e("Login", response + "wrongg...!!");
                                    Toast.makeText(LoginActivity.this, "Invalid username or password or role", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("Login", response + " HELLO Right...!!!");
                                    String email = "", type = "";

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        JSONObject customerObject = jsonArray.getJSONObject(i);
                                        email = customerObject.getString("Email");
                                        type=customerObject.getString("type");
//                                        password = customerObject.getString("Password");
//                                        editor.putString("password", password);
                                        editor.putString("type",type);
                                        editor.putString("email", email);
                                        editor.commit();

                                        Log.e("Login", "email ::" + email);
                                        Log.e("Login", "type ::" + type);


                                        Intent intent = new Intent(LoginActivity.this,CustomerHomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();

                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Login","Error :: "+error.toString());

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<>();
                            params.put("email", username);
                            params.put("password", password);
                            return params;

                        }
                    };
                    requestQueue.add(stringRequest);
                }

            }
        });

        forgetPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

}