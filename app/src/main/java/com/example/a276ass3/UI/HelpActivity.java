package com.example.a276ass3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.a276ass3.R;

public class HelpActivity extends AppCompatActivity {

    public static Intent makeLaunchIntent (Context context) {
        Intent intent = new Intent (context, HelpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}