package com.joewagdy.mathtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HighScores extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        TextView easyHighScore = findViewById(R.id.easyScore);
        if(MainActivity.getEasyHighScore() == 0){
            easyHighScore.setText(R.string.no_high_score);
            easyHighScore.setClickable(true);
            easyHighScore.setOnClickListener(this);
        }else{
            easyHighScore.setClickable(false);
            easyHighScore.setText(MainActivity.getEasyHighScore() + "/" + MainActivity.getEasyHighScoreQues() +
                    " Right Answers\nin ");
            if (MainActivity.getEasyHighScoreTime()/60 >= 1){
                easyHighScore.setText(easyHighScore.getText() + Integer.toString(MainActivity.getEasyHighScoreTime()/60) + "m "
                + String.format("%02d",(int)MainActivity.getEasyHighScoreTime()%60) + "s");
            }else{
                easyHighScore.setText(easyHighScore.getText() + Integer.toString(MainActivity.getEasyHighScoreTime()) + "s");
            }
        }
        TextView medHighScore = findViewById(R.id.medScore);
        if(MainActivity.getMedHighScore() == 0){
            medHighScore.setText(R.string.no_high_score);
            medHighScore.setClickable(true);
            medHighScore.setOnClickListener(this);
        }else{
            medHighScore.setClickable(false);
            medHighScore.setText(MainActivity.getMedHighScore() + "/" + MainActivity.getMedHighScoreQues() +
                    " Right Answers\nin ");
            if (MainActivity.getMedHighScoreTime()/60 >= 1){
                medHighScore.setText(medHighScore.getText() + Integer.toString(MainActivity.getMedHighScoreTime()/60) + "m "
                        + String.format("%02d",(int)MainActivity.getMedHighScoreTime()%60) + "s");
            }else{
                medHighScore.setText(medHighScore.getText() + Integer.toString(MainActivity.getMedHighScoreTime()) + "s");
            }
        }
        TextView hardHighScore = findViewById(R.id.hardScore);
        if(MainActivity.getHardHighScore() == 0){
            hardHighScore.setText(R.string.no_high_score);
            hardHighScore.setClickable(true);
            hardHighScore.setOnClickListener(this);
        }else{
            hardHighScore.setText(MainActivity.getHardHighScore() + "/" + MainActivity.getHardHighScoreQues() +
                    " Right Answers\nin ");
            if (MainActivity.getHardHighScoreTime()/60 >= 1){
                hardHighScore.setText(hardHighScore.getText() + Integer.toString(MainActivity.getHardHighScoreTime()/60) + "m "
                        + String.format("%02d",(int)MainActivity.getHardHighScoreTime()%60) + "s");
            }else{
                hardHighScore.setText(hardHighScore.getText() + Integer.toString(MainActivity.getHardHighScoreTime()) + "s");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())){
            case "easyScore":
                MainActivity.setGameMode("EASY");
                break;
            case "medScore":
                MainActivity.setGameMode("MEDIUM");
                break;
            case "hardScore":
                MainActivity.setGameMode("HARD");
                break;
        }
        startActivity(new Intent(this,GameActivity.class));
    }
}
