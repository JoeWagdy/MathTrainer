package com.joewagdy.mathtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    private static String mGameMode;
    private static int mEasyHighScore;
    private static int mEasyHighScoreTime;
    private static int mEasyHighScoreQues;
    private static int mMedHighScore;
    private static int mMedHighScoreTime;
    private static int mMedHighScoreQues;
    private static int mHardHighScore;
    private static int mHardHighScoreTime;
    private static int mHardHighScoreQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSharedPreferences();
    }

    public void startGame(View view){
        switch (getResources().getResourceEntryName(view.getId())){
            case "easyButton":
                mGameMode = "EASY";
                break;
            case "medButton":
                mGameMode = "MEDIUM";
                break;
            case "hardButton":
                mGameMode = "HARD";
                break;
        }
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
    public void highScores(View view){
        startActivity(new Intent(this,HighScores.class));
    }

    private void loadSharedPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("highScores",Context.MODE_PRIVATE);
        int defaultValue = getResources().getInteger(R.integer.easy_high_score_default_key);
        mEasyHighScore = sharedPref.getInt("easyHighScore", defaultValue);
        defaultValue = getResources().getInteger(R.integer.easy_high_score_time_default_key);
        mEasyHighScoreTime = sharedPref.getInt("easyHighScoreTime", defaultValue);
        defaultValue = getResources().getInteger(R.integer.easy_high_score_ques_default_key);
        mEasyHighScoreQues = sharedPref.getInt("easyHighScoreQues", defaultValue);
        defaultValue = getResources().getInteger(R.integer.med_high_score_default_key);
        mMedHighScore = sharedPref.getInt("medHighScore", defaultValue);
        defaultValue = getResources().getInteger(R.integer.med_high_score_time_default_key);
        mMedHighScoreTime = sharedPref.getInt("medHighScoreTime", defaultValue);
        defaultValue = getResources().getInteger(R.integer.hard_high_score_default_key);
        mMedHighScoreQues = sharedPref.getInt("medHighScoreQues", defaultValue);
        defaultValue = getResources().getInteger(R.integer.hard_high_score_default_key);
        mHardHighScore = sharedPref.getInt("hardHighScore", defaultValue);
        defaultValue = getResources().getInteger(R.integer.hard_high_score_time_default_key);
        mHardHighScoreTime = sharedPref.getInt("hardHighScoreTime", defaultValue);
        defaultValue = getResources().getInteger(R.integer.hard_high_score_ques_default_key);
        mHardHighScoreQues = sharedPref.getInt("hardHighScoreQues", defaultValue);
    }

    public static void setGameMode(String mGameMode) {
        MainActivity.mGameMode = mGameMode;
    }

    public static String getGameMode() {
        return mGameMode;
    }

    public static int getEasyHighScore() {
        return mEasyHighScore;
    }

    public static int getEasyHighScoreTime() {
        return mEasyHighScoreTime;
    }

    public static int getEasyHighScoreQues() {
        return mEasyHighScoreQues;
    }

    public static int getMedHighScore() {
        return mMedHighScore;
    }

    public static int getMedHighScoreTime() {
        return mMedHighScoreTime;
    }

    public static int getMedHighScoreQues() {
        return mMedHighScoreQues;
    }

    public static int getHardHighScore() {
        return mHardHighScore;
    }

    public static int getHardHighScoreTime() {
        return mHardHighScoreTime;
    }

    public static int getHardHighScoreQues() {
        return mHardHighScoreQues;
    }


    public static void setEasyHighScore(int easyHighScore) {
        mEasyHighScore = easyHighScore;
    }

    public static void setEasyHighScoreTime(int easyHighScoreTime) {
        mEasyHighScoreTime = easyHighScoreTime;
    }

    public static void setEasyHighScoreQues(int easyHighScoreQues) {
        mEasyHighScoreQues = easyHighScoreQues;
    }

    public static void setMedHighScore(int medHighScore) {
        mMedHighScore = medHighScore;
    }

    public static void setMedHighScoreTime(int medHighScoreTime) {
        mMedHighScoreTime = medHighScoreTime;
    }

    public static void setMedHighScoreQues(int medHighScoreQues) {
        mMedHighScoreQues = medHighScoreQues;
    }

    public static void setHardHighScore(int hardHighScore) {
        mHardHighScore = hardHighScore;
    }

    public static void setHardHighScoreTime(int hardHighScoreTime) {
        mHardHighScoreTime = hardHighScoreTime;
    }

    public static void setHardHighScoreQues(int hardHighScoreQues) {
        mHardHighScoreQues = hardHighScoreQues;
    }
}
