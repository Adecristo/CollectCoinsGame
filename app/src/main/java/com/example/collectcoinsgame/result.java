package com.example.collectcoinsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText("Score: "+score);
    }
    public void tryAgain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}

