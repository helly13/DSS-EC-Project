package com.example.sandburg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sandburg.Constants.Constant;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class AddMenuActivity extends AppCompatActivity {

    EditText edtCategoryId,edtCategoryName,edtItemName,edtPrice,edtStatus,edtType;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);

        edtCategoryId = findViewById(R.id.edt_categoryid);
        edtCategoryName = findViewById(R.id.edt_catname);
        edtItemName = findViewById(R.id.edt_itemname);
        edtPrice = findViewById(R.id.edt_price);
        edtStatus = findViewById(R.id.edt_status);
        edtType = findViewById(R.id.edt_type);

        button = findViewById(R.id.btn_addbn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String categoryId = edtCategoryId.getText().toString();
                final String categoryName = edtCategoryName.getText().toString();
                final String itemName = edtItemName.getText().toString();
                final String price = edtPrice.getText().toString();
                final String status = edtStatus.getText().toString();
                final String type = edtType.getText().toString();

                String url = "http://192.168.70.1:3000/menuinsert";
                RequestQueue requestQueue = Volley.newRequestQueue(AddMenuActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                      edtCategoryId.setText("HELLO");
                        Log.e("addmenu",response + " hello");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("itemname",itemName);
                        params.put("price",price);
                        params.put("stat",status);
                        params.put("foodtype",type);
                        params.put("catname",categoryName);
                        params.put("catid",categoryId);
                        return params;
                    }
                };

                requestQueue.add(stringRequest);


            }
        });


    }



}