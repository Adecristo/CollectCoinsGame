package com.example.collectcoinsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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
    private FrameLayout frmMain;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private int PlayerSpeed = 20;
    private int CoinSpeed = 5;
    private int Sorce = 0;

    private boolean holding = false;
    private boolean startFlag = false;

    private int frmMainHeight;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblSorce =  (TextView) findViewById(R.id.lblSorce);
        lblStart =  (TextView) findViewById(R.id.lblStart);
        Player = (ImageView) findViewById(R.id.player);
        Coin = (ImageView) findViewById(R.id.coin);
        Ball = (ImageView) findViewById(R.id.ball);
        frmMain = (FrameLayout) findViewById(R.id.frmMain);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        lblStart.setVisibility(View.INVISIBLE);
        Coin.setX(-80);
        Coin.setY(-80);

    }

    public void changePos(){
        int playerY = (int) Player.getY();
        int playerSize = (int)Player.getHeight();
        int coinX = (int) Coin.getX();
        int coinY = (int) Coin.getY();

        //coin  move
        coinX -= CoinSpeed;
        if (coinX < 0) {
            coinX = screenWidth + 20;
            coinY = (int) Math.floor(Math.random() * (frmMainHeight - Coin.getHeight()));
        }
        Coin.setX(coinX);
        Coin.setY(coinY);

        //player move
        if(holding)
            playerY -= PlayerSpeed;
        else
            playerY += PlayerSpeed;
        if (playerY < 0) playerY=0;

        if (playerY > frmMainHeight - playerSize) playerY = frmMainHeight - playerSize;

        Player.setY(playerY);
    }

    public boolean onTouchEvent(MotionEvent me) {
        if (startFlag == false) {

            startFlag = true;

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

