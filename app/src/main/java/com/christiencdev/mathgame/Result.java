package com.christiencdev.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    //initiate buttons method variables
    TextView result;
    Button exit;
    Button restart;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //assign method variables to components
        result = findViewById(R.id.textViewScore);
        exit = findViewById(R.id.buttonExit);
        restart = findViewById(R.id.buttonRestart);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent is used as the binder or glue between activities 9such as Game and result)
                //this intent will glue Result to MainActivity
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);

                //will close the old activity
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //will close the activity
                finish();
            }
        });
    }
}