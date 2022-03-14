package com.christiencdev.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameSubtract extends AppCompatActivity {
    //initiate buttons method variables
    TextView score;
    TextView life;
    TextView time;
    TextView question;
    EditText answer;
    Button ok;
    Button next;

    //java class to generate random numbers for game
    Random random = new Random();
    int num1;
    int num2;

    //user and correct answer
    int userAnswer;
    int correctAnswer;

    //user life and score
    int userScore = 0;
    int userLife = 3;

    //timer
    CountDownTimer timer;

    //private static variables are often used for constants, in this case, our minute long timer
    private static final long START_TIMER_IN_MS = 10000;

    //determines if timer is running or not
    Boolean timer_running;
    long time_left_in_ms = START_TIMER_IN_MS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //assign method variables to components
        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextAnswer);
        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);

        //we call the gameContinue(0 method in onCreate so it runs when opened
        gameContinue();

        //button click listeners
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we first get user answer as string converted to integer
                userAnswer = Integer.valueOf(answer.getText().toString());

                //pauses timer when user answers
                pauseTimer();

                //we check user answer to correct answer
                if(userAnswer == correctAnswer)
                {
                    //we up their score and show it was correct
                    userScore = userScore + 10;
                    //""+ will convert the score to an integer value
                    score.setText(""+userScore);
                    question.setText("Congrats");
                }
                else
                {
                    //we decrease their life and show it was false
                    userLife = userLife - 1;
                    life.setText(""+userLife);
                    question.setText("False");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //will clear answer and generate a new question
                answer.setText("");
                gameContinue();

                //resets timer on next question
                resetTimer();

                //if we run out of lives, we open up the result activity and display user results
                if (userLife <= 0)
                {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GameSubtract.this, Result.class);

                    //will add the score
                    intent.putExtra("score", userScore);
                    startActivity(intent);
                    finish();
                }
                //if we still have lives, game continues
                else
                {
                    gameContinue();
                }
            }
        });
    }

    //method containing game logic to ask questions
    public void gameContinue()
    {
        //generate random numbers between 0 and 100
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);

        //generate answer for question
        correctAnswer = num1 - num2;

        //sets the text for the question in textview
        question.setText(num1 + " - " + num2);

        //starts timer
        startTimer();
    }

    //timer method
    public void startTimer()
    {
        //countdown in seconds for our 60 seconds
        timer = new CountDownTimer(time_left_in_ms, 1000) {
            @Override
            public void onTick(long l) {
                //l = millis until finished
                time_left_in_ms = l;
                updateText();
            }

            //if time runs out we stop the timer/restart it until lives are finished
            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife - 1;
                question.setText(""+userLife);
                life.setText("Sorry, time sup");
            }
        }.start();

        timer_running = true;
    }

    //timer methods to make timer functional
    public void updateText()
    {
        //convert the seconds into integers left
        int second = (int)(time_left_in_ms / 1000) % 60;

        //then we convert this value to string as two digits (02d) and show it in TextView
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);

    }

    public void pauseTimer()
    {
        //cancel timer
        timer.cancel();
        timer_running = false;
    }

    public void resetTimer()
    {
        //reset timer
        time_left_in_ms = START_TIMER_IN_MS;
        updateText();
    }
}