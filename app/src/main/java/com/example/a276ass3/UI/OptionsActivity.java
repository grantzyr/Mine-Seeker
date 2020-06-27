package com.example.a276ass3.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.a276ass3.R;

public class OptionsActivity extends AppCompatActivity {

    public static Intent makeLaunchIntent (Context context) {
        Intent intent = new Intent (context, OptionsActivity.class);
        return intent;
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        Spinner boardSize = findViewById(R.id.Options_BoardSizeSpinner);
        String[] boardSizeOptions = new String[]{"4 x 6", "5 x 10", "6 x 15"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, boardSizeOptions);
        boardSize.setAdapter(adapter1);

        Spinner numOfMines = findViewById(R.id.Options_MinesSpinner);
        String[] numOfMineOptions = new String[]{"6", "10", "15", "20"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, numOfMineOptions);
        numOfMines.setAdapter(adapter2);



    }

}