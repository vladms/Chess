package com.vladbonta.myapplication.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.vladbonta.myapplication.views.ChessboardView;
import com.vladbonta.myapplication.R;


public class StartMenuActivity extends AppCompatActivity {
    Button startButton;
    Button optionsButton;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        startButton = (Button) findViewById(R.id.btnStart);
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ChessboardView chessBoard = new ChessboardView(getApplicationContext());
                setContentView(chessBoard);
            }

        });

        optionsButton = (Button) findViewById(R.id.btnOptions);
        optionsButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }

        });

        exitButton = (Button) findViewById(R.id.btnExit);
        exitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showDialog("EXIT", "Are you sure you want to leave?");
            }

        });

    }

public void showDialog(String title, String content){
    AlertDialog alertDialog = new AlertDialog.Builder(StartMenuActivity.this).create();
    alertDialog.setTitle(title);
    alertDialog.setMessage(content);
    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
    alertDialog.show();
}

}
