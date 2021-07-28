package com.joewagdy.mathtrainer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private final String gameMode = MainActivity.getGameMode();
    TextView question;
    TextView scoreText;
    TextView timeText;
    Spinner timerSetter;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultTextView;
    TextView highScoreTextView;
    Button playButton;
    ArrayList<Integer> answers = new ArrayList<>();
    private CountDownTimer countDown;
    private int correctAnswerPos;
    private int correctAnswer;
    private int timer = 30;
    private int score;
    private int numOfQues;
    private char[] operations = {'+', '-', 'x', '/'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        question = findViewById(R.id.question);
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timerText);
        timerSetter = findViewById(R.id.spinner);
        button0 = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        highScoreTextView = findViewById(R.id.highScoreTextView);
        playButton = findViewById(R.id.playButton);
        ArrayList<String> timerOptions = new ArrayList<>();
        timerOptions.add("30s");
        timerOptions.add("1m");
        timerOptions.add("1m 30s");
        timerOptions.add("2m");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timerOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timerSetter.setAdapter(adapter);
        timerSetter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        timer = 30;
                        break;
                    case 1:
                        timer = 60;
                        break;
                    case 2:
                        timer = 90;
                        break;
                    case 3:
                        timer = 120;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void play(View view) {
        if (playButton.getText().equals(getResources().getString(R.string.start))) {
            timerSetter.setVisibility(View.INVISIBLE);
            timeText.setText(timerSetter.getSelectedItem().toString());
            timeText.setVisibility(View.VISIBLE);
            playButton.setText(R.string.stop);
            button0.setEnabled(true);
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            countDown = new CountDownTimer((timer * 1000) + 100, 1000) {
                int secLeft = timer;

                @Override
                public void onTick(long millisUntilFinished) {
                    if (secLeft > 59) {
                        timeText.setText(Integer.toString(secLeft / 60) + "m "
                                + Integer.toString(secLeft % 60) + "s");
                    } else {
                        timeText.setText(secLeft + "s");
                    }
                    secLeft--;
                }

                @Override
                public void onFinish() {
                    checkHighScore();
                    button0.setEnabled(false);
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    timeText.setText("Finshed");
                    resultTextView.setText("Your Score\n" + score + "/" + numOfQues
                            + " in " + timerSetter.getSelectedItem());
                    resultTextView.setTextColor(getResources().getColor(R.color.black));
                    switch (gameMode) {
                        case "EASY":
                            highScoreTextView.setText("High score: " + MainActivity.getEasyHighScore() + "/"
                                    + MainActivity.getEasyHighScoreQues() + " in ");
                            if (MainActivity.getEasyHighScoreTime()/60 >= 1){
                                highScoreTextView.setText(highScoreTextView.getText() + Integer.toString(MainActivity.getEasyHighScoreTime()/60) + "m "
                                        + String.format("%02d",(int)MainActivity.getEasyHighScoreTime()%60) + "s");
                            }else{
                                highScoreTextView.setText(highScoreTextView.getText() + Integer.toString(MainActivity.getEasyHighScoreTime()) + "s");
                            }
                            break;
                        case "MEDIUM":
                            highScoreTextView.setText("High score: " + MainActivity.getMedHighScore() + "/"
                                    + MainActivity.getMedHighScoreQues() + " in ");
                            if (MainActivity.getMedHighScoreTime()/60 >= 1){
                                highScoreTextView.setText(highScoreTextView.getText() + Integer.toString(MainActivity.getMedHighScoreTime()/60) + "m "
                                        + String.format("%02d",(int)MainActivity.getMedHighScoreTime()%60) + "s");
                            }else{
                                highScoreTextView.setText(highScoreTextView.getText() + Integer.toString(MainActivity.getMedHighScoreTime()) + "s");
                            }
                            break;
                        case "HARD":
                            highScoreTextView.setText("High score: " + MainActivity.getHardHighScore() + "/"
                                    + MainActivity.getHardHighScoreQues() + " in ");
                            if (MainActivity.getHardHighScoreTime()/60 >= 1){
                                highScoreTextView.setText(highScoreTextView.getText() + Integer.toString(MainActivity.getHardHighScoreTime()/60) + "m "
                                        + String.format("%02d",(int)MainActivity.getHardHighScoreTime()%60) + "s");
                            }else{
                                highScoreTextView.setText(highScoreTextView.getText() + Integer.toString(MainActivity.getHardHighScoreTime()) + "s");
                            }
                            break;
                    }
                    highScoreTextView.setVisibility(View.VISIBLE);
                    playButton.setText(R.string.play_again);
                }
            };
            countDown.start();
            generateQuestion();
        } else {
            resetGame();
        }
    }

    @SuppressLint("SetTextI18n")
    private void generateQuestion() {
        answers.clear();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        correctAnswerPos = rand.nextInt(4);
        int operationPos = 0;
        switch (gameMode) {
            case "EASY":
                break;
            case "MEDIUM":
                operationPos = rand.nextInt(2);
                break;
            case "HARD":
                operationPos = rand.nextInt(4);
                break;
        }
        question.setText(String.valueOf(a) + ' ' +operations[operationPos] + ' ' + String.valueOf(b));
        switch (operationPos) {
            case 0:
                correctAnswer = a + b;
                break;
            case 1:
                if (a > b) {
                    correctAnswer = a - b;
                } else {
                    question.setText(String.valueOf(b) + ' ' + operations[operationPos] + ' ' + String.valueOf(a));
                    correctAnswer = b - a;
                }
                break;
            case 2:
                correctAnswer = a * b;
                break;
            case 3:
                while (a == 0) {
                    a = rand.nextInt(21);
                }
                while (b == 0) {
                    b = rand.nextInt(21);
                }
                while (a % b != 0 && b % a != 0) {
                    if (a > b) {
                        a = rand.nextInt(21);
                        while (a == 0) {
                            a = rand.nextInt(21);
                        }
                    } else {
                        b = rand.nextInt(21);
                        while (b == 0) {
                            b = rand.nextInt(21);
                        }
                    }
                }
                if (a > b) {
                    question.setText(String.valueOf(a) + ' ' + operations[operationPos] + ' ' + String.valueOf(b));
                    correctAnswer = a / b;
                } else {
                    question.setText(String.valueOf(b) + ' ' + operations[operationPos] + ' ' + String.valueOf(a));
                    correctAnswer = b / a;
                }
                break;
        }
        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerPos) {
                answers.add(correctAnswer);
            } else {
                int incorrectAnswer;
                if (operationPos == 2) {
                    incorrectAnswer = rand.nextInt(401);
                    while (incorrectAnswer == correctAnswer) {
                        incorrectAnswer = rand.nextInt(401);
                    }
                } else {
                    incorrectAnswer = rand.nextInt(41);
                    while (incorrectAnswer == correctAnswer) {
                        incorrectAnswer = rand.nextInt(41);
                    }
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));
    }

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view) {
        if (view.getTag().equals(Integer.toString(correctAnswerPos))) {
            resultTextView.setTextColor(getResources().getColor(R.color.green));
            resultTextView.setText(R.string.correct);
            score++;
        } else {
            resultTextView.setTextColor(getResources().getColor(R.color.red));
            resultTextView.setText(R.string.wrong);
        }
        numOfQues++;
        scoreText.setText(String.valueOf(score) + operations[3] + String.valueOf(numOfQues));
        generateQuestion();
    }

    private void resetGame(){
        if(countDown != null){
            countDown.cancel();
        }
        countDown = null;
        timeText.setVisibility(View.INVISIBLE);
        timerSetter.setVisibility(View.VISIBLE);
        scoreText.setText(R.string.zeroScore);
        question.setText(R.string.question);
        button0.setText("A");
        button1.setText("B");
        button2.setText("C");
        button3.setText("D");
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        resultTextView.setText(null);
        highScoreTextView.setVisibility(View.INVISIBLE);
        playButton.setText(R.string.start);
        score = 0;
        numOfQues = 0;
    }

    private void checkHighScore(){
        switch (gameMode) {
            case "EASY":
                if(MainActivity.getEasyHighScore() != 0) {
                    if ((float)score / timer > (float)MainActivity.getEasyHighScore() / MainActivity.getEasyHighScoreTime()) {
                        MainActivity.setEasyHighScore(score);
                        MainActivity.setEasyHighScoreQues(numOfQues);
                        MainActivity.setEasyHighScoreTime(timer);
                        saveSharedPreferences();
                    }
                }else{
                    MainActivity.setEasyHighScore(score);
                    MainActivity.setEasyHighScoreQues(numOfQues);
                    MainActivity.setEasyHighScoreTime(timer);
                    saveSharedPreferences();
                }
                break;
            case "MEDIUM":
                if(MainActivity.getMedHighScore() != 0) {
                    if ((float)score / timer > (float)MainActivity.getMedHighScore() / MainActivity.getMedHighScoreTime()) {
                        MainActivity.setMedHighScore(score);
                        MainActivity.setMedHighScoreQues(numOfQues);
                        MainActivity.setMedHighScoreTime(timer);
                        saveSharedPreferences();
                    }
                }else{
                    MainActivity.setMedHighScore(score);
                    MainActivity.setMedHighScoreQues(numOfQues);
                    MainActivity.setMedHighScoreTime(timer);
                    saveSharedPreferences();
                }
                break;
            case "HARD":
                if(MainActivity.getHardHighScore() != 0) {
                    if ((float)score / timer > (float)MainActivity.getHardHighScore() / MainActivity.getHardHighScoreTime()) {
                        MainActivity.setHardHighScore(score);
                        MainActivity.setHardHighScoreQues(numOfQues);
                        MainActivity.setHardHighScoreTime(timer);
                        saveSharedPreferences();
                    }
                }else{
                    MainActivity.setHardHighScore(score);
                    MainActivity.setHardHighScoreQues(numOfQues);
                    MainActivity.setHardHighScoreTime(timer);
                    saveSharedPreferences();
                }
                break;
        }
    }

    private void saveSharedPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("highScores",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("easyHighScore", MainActivity.getEasyHighScore());
        editor.putInt("easyHighScoreTime", MainActivity.getEasyHighScoreTime());
        editor.putInt("easyHighScoreQues", MainActivity.getEasyHighScoreQues());
        editor.putInt("medHighScore", MainActivity.getMedHighScore());
        editor.putInt("medHighScoreTime", MainActivity.getMedHighScoreTime());
        editor.putInt("medHighScoreQues", MainActivity.getMedHighScoreQues());
        editor.putInt("hardHighScore", MainActivity.getHardHighScore());
        editor.putInt("hardHighScoreTime", MainActivity.getHardHighScoreTime());
        editor.putInt("hardHighScoreQues", MainActivity.getHardHighScoreQues());
        editor.apply();
    }
}