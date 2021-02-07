package com.diyar.quizshow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Random;

import static android.graphics.Color.RED;
//ca-app-pub-6313549492070266/6256803507
public class MainActivity4 extends AppCompatActivity {
    private AdView mAdView;
    Button answer1 , answer2 , answer3 , answer4 ,answer5;
    TextView score ,questions, timeText ;
    private  g4Questions mQuestions = new g4Questions();
    private String mAnswer ;
    private int mScore =0 ;
    private int g4QuestionsLength =mQuestions.mQuestions.length;

    Random r ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        r = new Random();

        answer1 = findViewById(R.id.button);
        answer2 = findViewById(R.id.button2);
        answer3 = findViewById(R.id.button3);
        answer4 = findViewById(R.id.button4);
        answer5 = findViewById(R.id.button5);

        score = findViewById(R.id.score);
        questions=findViewById(R.id.question);
        timeText =findViewById(R.id.timeText);

        updateQuestion(r.nextInt(g4QuestionsLength));

        new CountDownTimer(260000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time :" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity4.this);
                alertDialogBuilder
                        .setMessage("Süreniz  Bitti ! Puanınınz :" +mScore + " puan" )
                        .setCancelable(false)
                        .setPositiveButton("Yeni Oyun",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));

                                    }
                                })
                        .setNegativeButton("Ana menüye dön",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);

                                    }
                                });
                AlertDialog alertDialog =alertDialogBuilder.create();
                alertDialog.show();


            }
        }.start();

        answer1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if(answer1.getText() == mAnswer){
                    mScore ++;
                    score.setText("Score :" + mScore);
                    updateQuestion(r.nextInt(g4QuestionsLength));
                }else {
                    answer1.setBackgroundColor(RED);
                    gameOver();

                }

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer2.getText() == mAnswer){
                    mScore ++;

                    answer2.clearAnimation();
                    score.setText("Score :" + mScore);
                    updateQuestion(r.nextInt(g4QuestionsLength));
                }else {
                    answer2.setBackgroundColor(RED);
                    gameOver();
                }

            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer3.getText() == mAnswer){
                    mScore ++;

                    answer3.clearAnimation();
                    score.setText("Score :" + mScore);
                    updateQuestion(r.nextInt(g4QuestionsLength));
                }else {
                    answer3.setBackgroundColor(RED);
                    gameOver();
                }

            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer4.getText() == mAnswer){
                    mScore ++;

                    score.setText("Score :" + mScore);
                    updateQuestion(r.nextInt(g4QuestionsLength));
                }else {
                    answer4.setBackgroundColor(RED);
                    gameOver();
                }

            }
        });

        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer5.getText() == mAnswer){
                    mScore ++;
                    score.setText("Score :" + mScore);
                    updateQuestion(r.nextInt(g4QuestionsLength));
                }else {
                    answer5.setBackgroundColor(RED);
                    gameOver();
                }
            }
        });



    }
    private void updateQuestion(int num) {
        questions.setText(mQuestions.getQuestion(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));
        answer5.setText(mQuestions.getChoice5(num));

        mAnswer = mQuestions.getCorrectAnswers(num);
    }
    public void gameOver() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity4.this);
        alertDialogBuilder
                .setMessage("Oyun Bitti " +"\n"+ "Puanınınz :" +mScore + " puan " + "\n" +"Doğru Cevap :"+ mAnswer)
                .setCancelable(false)
                .setPositiveButton("Yeni Oyun",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),MainActivity4.class));

                            }
                        })
                .setNegativeButton("Ana menüye dön",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);

                            }
                        });
        AlertDialog alertDialog =alertDialogBuilder.create();
        alertDialog.show();
    }

}