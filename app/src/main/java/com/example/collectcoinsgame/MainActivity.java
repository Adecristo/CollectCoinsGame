package com.example.collectcoinsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView lblSorce;
    private TextView lblStart;
    private ImageView Player;
    private ImageView Coin;
    private ImageView Ball;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private int PlayerSpeed = 20;
    private int Sorce = 0;

    private boolean holding = false;
    private boolean startFlag = false;

    private int frmMainHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblSorce =  (TextView) findViewById(R.id.lblSorce);
        lblStart =  (TextView) findViewById(R.id.lblStart);
        Player = (ImageView) findViewById(R.id.player);
        Coin = (ImageView) findViewById(R.id.coin);
        Ball = (ImageView) findViewById(R.id.ball);

        //Player.setVisibility(View.INVISIBLE);
        Coin.setVisibility(View.INVISIBLE);
        Ball.setVisibility(View.INVISIBLE);
        lblStart.setVisibility(View.INVISIBLE);
    }

    public void changePos(){
        int playerY = (int) Player.getY();
        int playerSize = (int)Player.getHeight();
        if(holding)
            playerY -= PlayerSpeed;
        else
            playerY += PlayerSpeed;
        if (playerY < 0) Player.setY(0);

        if (playerY > frmMainHeight - playerSize) playerY = frmMainHeight - playerSize;

        Player.setY(playerY);
    }

    public boolean onTouchEvent(MotionEvent me) {
        if (startFlag == false) {

            startFlag = true;

            FrameLayout frmMain = (FrameLayout) findViewById(R.id.frmMain);
            frmMainHeight = frmMain.getHeight();

            lblStart.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);


        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                holding = true;

            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                holding = false;
            }
        }
        return true;
    }
}

