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


public class ResetPasswordActivity extends AppCompatActivity{

    EditText edtotp,edtpassword,edtconfirm_password;
    String email,type;
    Button btnresetpwd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd_activity);

        setTitle("Reset Password");


        edtotp=findViewById(R.id.edt_otp_resetpwd);
        edtpassword=findViewById(R.id.edt_newpassword_reset);
        edtconfirm_password=findViewById(R.id.edt_confirmpwd_resetpwd);
        btnresetpwd=findViewById(R.id.btn_resetpwd_resetpwd);

        sharedPreferences = getSharedPreferences("Sandburg", MODE_PRIVATE);
        email=sharedPreferences.getString("email","");

        Log.e("ResetPassword","iinnnnnnnnnnnnn reset");

//        Log.e("ResetPassword","emaill :: "+email);

        btnresetpwd.setOnClickListener(new View.OnClickListener() {
            boolean flag=true;

            @Override
            public void onClick(View v) {

                final String otp=edtotp.getText().toString();
                final String newpassword=edtpassword.getText().toString();
                final String cpassword=edtconfirm_password.getText().toString();

                Log.e("ResetPassword","OTP ::"+otp);
                Log.e("ResetPassword","P ::"+newpassword);
                Log.e("ResetPassword","CP ::"+cpassword);

                if(Validation.isEmpty(edtotp.getText().toString())){
                    edtotp.setError("Enter OTP");
                    flag=false;
                }
                if(Validation.isEmpty(edtpassword.getText().toString())){
                    edtpassword.setError("Enter New Password");
                    flag=false;
                }
                if(Validation.isEmpty(edtconfirm_password.getText().toString())){
                    edtconfirm_password.setError("Enter Confirm Password");
                    flag=false;
                }
                if (edtpassword.getText().toString().length() <= 7) {
                    edtpassword.setError("Enter password of atleast 8 characters");
                    flag = false;
                }
                if (!(edtpassword.getText().toString().equals(edtconfirm_password.getText().toString()))) {
                    edtconfirm_password.setError("Password do not match");
                    flag = false;
                }
                if(flag){
                    resetPassword();
                }
            }
        });

    }

    private void resetPassword() {
        final String otp=edtotp.getText().toString();
        final String newpassword=edtpassword.getText().toString();
        final String cpassword=edtconfirm_password.getText().toString();


        Log.e("ResetPassword","updatefun");

        String url=Constant.URL+"resetpassword";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("ResetPassword","Res :: "+response);
                if(response.toLowerCase().equals("null"))
                    Toast.makeText( ResetPasswordActivity.this, "Please Enter correct OTP", Toast.LENGTH_SHORT).show();
                else if(response.toLowerCase().equals("no mail found"))
                    Toast.makeText( ResetPasswordActivity.this, "Please Enter correct Email", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(ResetPasswordActivity.this, " Password Sucessfully Updated", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ResetPasswordActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ResetPassword","Error :: "+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("password",newpassword);
                params.put("otp",otp);
                params.put("confirm_password",cpassword);
                params.put("type","customer");
                params.put("email",email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ResetPasswordActivity.this);
        requestQueue.add(stringRequest);
//        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
//            @Override
//            public void onRequestFinished(Request<Object> request) {
////                Intent intent=new Intent(changepassword.this,HomeFragment.class);
////                startActivity(intent);
//            }
//        });

    }
}
