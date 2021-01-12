package com.example.sandburg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class LocalActivity extends AppCompatActivity {

    public FragmentActivity getActivity() {
        return this;
    }
}
