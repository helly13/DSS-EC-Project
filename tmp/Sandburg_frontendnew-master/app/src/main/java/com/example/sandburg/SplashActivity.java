package com.example.sandburg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sandburg.Constants.*;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        sharedPreferences = getSharedPreferences("Sandburg", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String email = sharedPreferences.getString("email","");
//                type = type.toLowerCase();
                Intent intent;
//                Log.e("LOGIN",type + " SPLASH");

                if(email.length()>0)
                {
                    intent = new Intent(SplashActivity.this,CustomerHomeActivity.class);
                }
                else
                {
                  //  intent = new Intent(SplashActivity.this, LoginActivity.class);
                   intent = new Intent(SplashActivity.this, ListMenu.class);
                // intent = new Intent(SplashActivity.this, AddMenuActivity.class);
                }
//                if(type.equals("secretary"))
//                {
//                    intent = new Intent(SplashActivity.this,SecretaryActivity.class);
//                }
//                else if(type.equals("user"))
//                {
//                    SharedPreferences sharedPreferences = getSharedPreferences("USERDATA",MODE_PRIVATE);
//                    String userNumber = sharedPreferences.getString("userPhoneNumber","");
//                    if(userNumber.equals(""))
//                    {
//                        intent = new Intent(SplashActivity.this, UserActivity.class);
//                    }
//                    else
//                    {
//                        intent = new Intent(SplashActivity.this,HomeActivity.class);
//                    }
//
//                }
//                else if(type.equalsIgnoreCase("watchman"))
//                {
//                    intent = new Intent(SplashActivity.this,WatchmanHomeActivity.class);//watchman intent here
//                }
//                else
//                {
//                    intent = new Intent(SplashActivity.this, LoginActivity.class);
//                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        }, Constant.SPLASH);

    }


//}
}
