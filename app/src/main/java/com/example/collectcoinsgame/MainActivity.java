package com.example.collectcoinsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lblSorce;
    private TextView lblStart;
    private ImageView Player;
    private ImageView Coin;
    private ImageView Ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblSorce =  (TextView) findViewById(R.id.lblSorce);
        lblStart =  (TextView) findViewById(R.id.lblStart);
        Player = (ImageView) findViewById(R.id.player);
        Coin = (ImageView) findViewById(R.id.coin);
        Ball = (ImageView) findViewById(R.id.ball);

        Player.setVisibility(View.INVISIBLE);
        Coin.setVisibility(View.INVISIBLE);
        Ball.setVisibility(View.INVISIBLE);
        //lblSorce.setVisibility(View.INVISIBLE);
    }
}

public boolean onTouchEvent(MotionEvent me) {
        if (me.getAction() == MotionEvent.ACTION_DOWN)
            Player.setY(Player.getY() - 20);
        return true;
    }
}