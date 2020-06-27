package com.example.a276ass3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a276ass3.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtons();
    }

    private void setButtons() {
        Button playButton = (Button) findViewById(R.id.Main_PlayButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PlayGameActivity.makeLaunchIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        Button optionsButton = (Button) findViewById(R.id.Main_OptionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionsActivity.makeLaunchIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }




}