package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.ImageButton;
import android.content.Intent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int balls = 0;
    int strikes = 0;
    boolean lockOut = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button strikeButton = findViewById(R.id.strikeBtn);
        Button ballButton = findViewById(R.id.ballBtn);
        Button resetButton = findViewById(R.id.reset);
        Button exitButton = findViewById(R.id.exit);
        ImageButton infoButton = findViewById(R.id.infoBtn);

        infoButton.setOnClickListener(this);
        strikeButton.setOnClickListener(this);
        ballButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {


        TextView strikeT = findViewById(R.id.strikeCnt);
        TextView ballsT = findViewById(R.id.ballCnt);
        switch (v.getId()){

            case R.id.strikeBtn:
                if (strikes <= 2 && lockOut != true) {
                    strikes = strikes + 1;
                    strikeT.setText("" + strikes);
                    if (strikes == 3){
                        lockOut = true;
                        showAlertDialog(v, "strike");
                    }
                }
                break;

            case R.id.ballBtn:
                if (balls <= 3 && lockOut != true) {
                    balls = balls + 1;
                    ballsT.setText("" + balls);
                    if (balls == 4){
                        lockOut = true;
                        showAlertDialog(v, "ball");
                    }
                }


                break;

            case R.id.reset:
                balls = 0;
                strikes = 0;
                lockOut = false;
                ballsT.setText("0");
                strikeT.setText("0");
                break;

            case R.id.exit:
                finish();
                break;

            case R.id.infoBtn:
                Intent intent = new Intent(this, ActivityInfo.class);
                startActivity(intent);

                break;

        }

    }
    public void showAlertDialog(View v, String ballStrike){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        if (ballStrike.equals("ball")){
            alert.setMessage("Take first base");
            alert.setTitle("Batter was walked!");

        }
        else if (ballStrike.equals("strike")){
            alert.setMessage("Better luck next time");
            alert.setTitle("Batter was struck out!");
        }
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alert.create().show();
    }


}
