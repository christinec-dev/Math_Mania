package com.christiencdev.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //initiate buttons method variables
    Button addition;
    Button subtraction;
    Button multi;
    Button divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign method variables to components
        addition = findViewById(R.id.buttonAdd);
        subtraction = findViewById(R.id.buttonSub);
        multi = findViewById(R.id.buttonMulti);
        divide = findViewById(R.id.buttonDiv);

        //add click events to buttons, this will open game for addition
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent is used as the binder or glue between activities such as Game and result)
                //opens new activity (page) which is Game
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);

                //closes the activity when not used or new activity starts
                finish();
            }
        });

        //add click events to buttons, this will open game for subtraction
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameSubtract.class);
                startActivity(intent);

                //closes the activity when not used or new activity starts
                finish();
            }
        });

        //add click events to buttons, this will open game for multiplication
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameMultiplication.class);
                startActivity(intent);

                //closes the activity when not used or new activity starts
                finish();
            }
        });

        //add click events to buttons, this will open game for division
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameDivide.class);
                startActivity(intent);

                //closes the activity when not used or new activity starts
                finish();
            }
        });
    }
}