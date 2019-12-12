package com.devshyeon.matchpickgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    BackPressCloseHandler backPressCloseHandler;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        backPressCloseHandler = new BackPressCloseHandler(this);
        getWindow().setWindowAnimations(android.R.style.Animation_Toast);

    }


    public void start(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {

        backPressCloseHandler.onBackPressed();
    }
}
