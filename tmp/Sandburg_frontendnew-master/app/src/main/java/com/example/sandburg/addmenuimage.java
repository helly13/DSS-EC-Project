package com.example.sandburg;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class addmenuimage extends AppCompatActivity implements View.OnClickListener
{
    private Button uploadbn,choosebn;

private ImageView imageview2;
private final int IMG_REQUEST=1;
private Bitmap bitmap;
/*private String UploadUrl =*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choosebn = findViewById(R.id.btn_choosebn);
        uploadbn = findViewById(R.id.btn_uploadbn);
        imageview2 = findViewById(R.id.imageView2);
        choosebn.setOnClickListener(this);
        uploadbn.setOnClickListener(this);

}


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_choosebn:
                selectimage();
                break;

            case R.id.btn_uploadbn:
                uploadimage();
                break;
        }
    }

    private void selectimage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            Uri path=data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageview2.setImageBitmap(bitmap);
                imageview2.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void uploadimage()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Toast.makeText(addmenuimage.this, Response, Toast.LENGTH_SHORT).show();
                            imageview2.setImageResource(0);
                            imageview2.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError
    {
        Map<String,String> params = new HashMap<>();
       /* params.put("name", Attributes.Name.getText().toString().trim());*/
        params.put("image", imageToString(bitmap) );


        return params;
    }
};
        volleysingleton.getInstance(addmenuimage.this).addToRequestQue(stringRequest);

    }



    private String imageToString(Bitmap bitmap)

    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }}