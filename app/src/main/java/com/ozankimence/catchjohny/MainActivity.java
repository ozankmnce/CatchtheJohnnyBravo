package com.ozankimence.catchjohny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int skor;

    Handler hn;
    Runnable run;

    TextView t1;
    TextView t2;
    ImageView i1;
    ImageView i2;
    ImageView i3;
    ImageView i4;
    ImageView i5;
    ImageView i6;
    ImageView i7;
    ImageView i8;
    ImageView i9;
    ImageView[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skor = 0;

        i1 = (ImageView) findViewById(R.id.imageView);
        i2 = (ImageView) findViewById(R.id.imageView1);
        i3 = (ImageView) findViewById(R.id.imageView2);
        i4 = (ImageView) findViewById(R.id.imageView3);
        i5 = (ImageView) findViewById(R.id.imageView4);
        i6 = (ImageView) findViewById(R.id.imageView5);
        i7 = (ImageView) findViewById(R.id.imageView6);
        i8 = (ImageView) findViewById(R.id.imageView7);
        i9 = (ImageView) findViewById(R.id.imageView8);
        img = new ImageView[]  {i1,i2,i3,i4,i5,i6,i7,i8,i9};

        time(t1);
        raiseScore(t2);
        hideScooby();



    }

    public void raiseScore(View v1) {

        t2 = (TextView) findViewById(R.id.textView2);
        t2.setText("Score: " +skor);
        skor++;

    }


    public void time(View v2) {
        t1 = (TextView) findViewById(R.id.textView1);
        t2 = (TextView) findViewById(R.id.textView2);
        new CountDownTimer(5000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                t1.setText("Time: " + millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                t1.setText("Time: 0");
               Toast.makeText(getApplicationContext(),"Time is Over !",Toast.LENGTH_SHORT).show();
                hn.removeCallbacks(run);
                for(ImageView loop: img) {
                    loop.setVisibility(View.INVISIBLE);
                }
                restartGame();


            }
        }.start();
    }


        public void hideScooby() {
        hn = new Handler();
        run = new Runnable() {
            @Override
            public void run() {

                for(ImageView loop: img) {

                    loop.setVisibility(View.INVISIBLE);
                }

                Random r = new Random();
                int a= r.nextInt(8-0);
                img[a].setVisibility(View.VISIBLE);
                hn.postDelayed(this,750);
            }
        };
        hn.post(run);

        }

    public void restartGame() {

        AlertDialog.Builder ab  = new AlertDialog.Builder(this);
        ab.setTitle("Yeniden Başlatılıyor..");
        ab.setMessage("Oyun Yeniden Başlasın mı?");
        ab.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i1 = getIntent();
                finish();
                startActivity(i1);

            }
        });

        ab.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Oyun Bitti",Toast.LENGTH_SHORT).show();
            }
        });
        ab.show();
    }



}

