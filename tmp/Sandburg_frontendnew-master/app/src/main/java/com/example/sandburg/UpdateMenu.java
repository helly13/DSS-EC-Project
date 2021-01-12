package com.example.sandburg;

import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.sandburg.ListMenu.catid;
import static com.example.sandburg.ListMenu.catname;
import static com.example.sandburg.ListMenu.item_name;

import static com.example.sandburg.ListMenu.price;
import static com.example.sandburg.ListMenu.stat;
import static com.example.sandburg.ListMenu.type1;


public class UpdateMenu<bundle, bundle1> extends AppCompatActivity {
   //
static Context context;
    EditText edtUCategoryId,edtUCategoryName,edtUItemName,edtUPrice,edtUStatus,edtUType;
    Button Ubutton;
    String catid1,catname1,price1,itemname1,stat1,type2;

@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_menu);




    edtUCategoryId = findViewById(R.id.edt_ucategoryid);
    edtUCategoryName = findViewById(R.id.edt_ucatname);
    edtUItemName = findViewById(R.id.edt_uitemname);
    edtUPrice = findViewById(R.id.edt_uprice);
    edtUStatus = findViewById(R.id.edt_ustatus);
    edtUType = findViewById(R.id.edt_utype);

    Ubutton = findViewById(R.id.btn_uaddbn);


    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
        if (bundle.containsKey("catid")) {
            catid1 = bundle.getString("catid");
             catname1 = bundle.getString("catname");
              price1 = bundle.getString("price");
              itemname1 = bundle.getString("item_name");
              stat1 = bundle.getString("stat");
              type2 = bundle.getString("type1");
        }
    }



    edtUCategoryId.setText(catid1);
    edtUCategoryName.setText(catname1);
    edtUPrice.setText(price1);
edtUItemName.setText(itemname1);
edtUStatus.setText(stat1);
edtUType.setText(type2);

    Ubutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final String ucategoryId = edtUCategoryId.getText().toString();
            final String ucategoryName = edtUCategoryName.getText().toString();
            final String uitemName = edtUItemName.getText().toString();
            final String uprice = edtUPrice.getText().toString();
            final String ustatus = edtUStatus.getText().toString();
            final String utype = edtUType.getText().toString();

            String url = "http://192.168.70.1:3000/menu_update";
            RequestQueue requestQueue = Volley.newRequestQueue(UpdateMenu.this);



            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("addmenu",response + " hello");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("error",  "");

                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("itemname",uitemName);
                    params.put("price",uprice);
                    params.put("stat",ustatus);
                    params.put("foodtype",utype);
                    params.put("catname",ucategoryName);
                    params.put("catid",ucategoryId);
                    Log.e("catid","ID " + params.get("catid"));
                    return params;
                }
            };

            requestQueue.add(stringRequest);

        }
    });

}
}
