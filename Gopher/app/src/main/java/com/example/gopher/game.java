package com.example.gopher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class game extends AppCompatActivity {

    int G_POS;
    boolean over = false;
    final static int W = 2;

    int arr1[] = new int[100];
    int arr2[] = new int[100];
    int count1=1;
    int count2=1;

    final int[] p1 = {
            R.id.t1,  R.id.t2,  R.id.t3,  R.id.t4,  R.id.t5,  R.id.t6,  R.id.t7,  R.id.t8,  R.id.t9,  R.id.t10,
            R.id.t11, R.id.t12, R.id.t13, R.id.t14, R.id.t15, R.id.t16, R.id.t17, R.id.t18, R.id.t19, R.id.t20,
            R.id.t21, R.id.t22, R.id.t23, R.id.t24, R.id.t25, R.id.t26, R.id.t27, R.id.t28, R.id.t29, R.id.t30,
            R.id.t31, R.id.t32, R.id.t33, R.id.t34, R.id.t35, R.id.t36, R.id.t37, R.id.t38, R.id.t39, R.id.t40,
            R.id.t41, R.id.t42, R.id.t43, R.id.t44, R.id.t45, R.id.t46, R.id.t47, R.id.t48, R.id.t49, R.id.t50,
            R.id.t51, R.id.t52, R.id.t53, R.id.t54, R.id.t55, R.id.t56, R.id.t57, R.id.t58, R.id.t59, R.id.t60,
            R.id.t61, R.id.t62, R.id.t63, R.id.t64, R.id.t65, R.id.t66, R.id.t67, R.id.t68, R.id.t69, R.id.t70,
            R.id.t71, R.id.t72, R.id.t73, R.id.t74, R.id.t75, R.id.t76, R.id.t77, R.id.t78, R.id.t79, R.id.t80,
            R.id.t81, R.id.t82, R.id.t83, R.id.t84, R.id.t85, R.id.t86, R.id.t87, R.id.t88, R.id.t89, R.id.t90,
            R.id.t91, R.id.t92, R.id.t93, R.id.t94, R.id.t95, R.id.t96, R.id.t97, R.id.t98, R.id.t99, R.id.t100
    };

    final int[] p2 = {
            R.id.t101,  R.id.t102, R.id.t103, R.id.t104, R.id.t105, R.id.t106, R.id.t107, R.id.t108, R.id.t109, R.id.t110,
            R.id.t111,  R.id.t112, R.id.t113, R.id.t114, R.id.t115, R.id.t116, R.id.t117, R.id.t118, R.id.t119, R.id.t120,
            R.id.t121,  R.id.t122, R.id.t123, R.id.t124, R.id.t125, R.id.t126, R.id.t127, R.id.t128, R.id.t129, R.id.t130,
            R.id.t131,  R.id.t132, R.id.t133, R.id.t134, R.id.t135, R.id.t136, R.id.t137, R.id.t138, R.id.t139, R.id.t140,
            R.id.t141,  R.id.t142, R.id.t143, R.id.t144, R.id.t145, R.id.t146, R.id.t147, R.id.t148, R.id.t149, R.id.t150,
            R.id.t151,  R.id.t152, R.id.t153, R.id.t154, R.id.t155, R.id.t156, R.id.t157, R.id.t158, R.id.t159, R.id.t160,
            R.id.t161,  R.id.t162, R.id.t163, R.id.t164, R.id.t165, R.id.t166, R.id.t167, R.id.t168, R.id.t169, R.id.t170,
            R.id.t171,  R.id.t172, R.id.t173, R.id.t174, R.id.t175, R.id.t176, R.id.t177, R.id.t178, R.id.t179, R.id.t180,
            R.id.t181,  R.id.t182, R.id.t183, R.id.t184, R.id.t185, R.id.t186, R.id.t187, R.id.t188, R.id.t189, R.id.t190,
            R.id.t191,  R.id.t192, R.id.t193, R.id.t194, R.id.t195, R.id.t196, R.id.t197, R.id.t198, R.id.t199, R.id.t100
    };

    TextView feedback2;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            int what = msg.what ;
            switch (what) {
                case W:
                    TextView pp2;
                    pp2 = findViewById(p2[msg.arg1]);
                    pp2.setText(Integer.toString(count2));
                    count2++;
                    feedback2 = findViewById(R.id.feedback2);
                    final int res = Closeness(msg.arg1);
                    if (res == 0) {
                        feedback2.setText("SUCCESS");
                    } else if (res == 1) {
                        feedback2.setText("NEAR MISS");
                    } else if (res == 2) {
                        feedback2.setText("CLOSE GUESS");
                    } else {
                        feedback2.setText("COMPLETE MISS");
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        G_POS = (int)(Math.random()*100)%100;
        TextView pp1;
        TextView pp2;
        pp1 = findViewById(p1[G_POS]);
        pp2 = findViewById(p2[G_POS]);
        pp1.setBackgroundResource(R.drawable.gopher_image);
        pp2.setBackgroundResource(R.drawable.gopher_image);

        Thread player1 = new Thread(new Runnable1());
        player1.start();
        Thread player2 = new Thread(new Runnable2());
        player2.start();

        for (int i = 0; i < 100; i++) {
            arr1[i]=0;
            arr2[i]=0;
        }
        Button stopbutton =  findViewById(R.id.winner);

        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }

    TextView result;
    TextView feedback1;

    class Runnable1 implements Runnable{
        @Override
        public void run() {
            while(over==false) {

                int thispos = (int) (Math.random() * 100);
                thispos += (thispos % 2 == 0 ? 1 : 0);
                final int res = Closeness(thispos);
                int finalThispos = thispos;


                if (arr1[finalThispos]==0) {
                    arr1[finalThispos]=1;
                    mHandler.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        public void run() {
                            TextView pp1;
                            pp1 = findViewById(p1[finalThispos]);
                            pp1.setText(Integer.toString(count1));
                            count1++;
                            feedback1 = findViewById(R.id.feedback1);
                            final int res = Closeness(finalThispos);
                            if (res == 0) {
                                feedback1.setText("SUCCESS");
                            } else if (res == 1) {
                                feedback1.setText("NEAR MISS");
                            } else if (res == 2) {
                                feedback1.setText("CLOSE GUESS");
                            } else {
                                feedback1.setText("COMPLETE MISS");
                            }
                        }
                    });
                    Handler hp1 = new Handler(Looper.getMainLooper()) {
                        public void handleMessage(Message msg) {
                            int what = msg.what;
                            switch (res) {
                                case 0:
                                    mHandler.post(new Runnable() {
                                        public void run() {
                                            over = true;
                                            result = findViewById(R.id.result);
                                            result.setText("Player 1");

                                        }
                                    });
                            }
                        }
                    };

                    if (res == 0) {
                        over = true;
                        mHandler.post(new Runnable() {
                            public void run() {
                                result = findViewById(R.id.result);
                                result.setText("Player 1");
                            }
                        });
                        break;
                    }
                try { Thread.sleep(2000); }
                catch (InterruptedException e) {
                    System.out.println("Thread interrupted!");
                }
                }
            }

        }
    }

    class Runnable2 implements Runnable{
        @Override
        public void run() {
            while(over==false) {

                int thispos=(int) (Math.random()*100)/2;
                thispos=thispos*2;
                final int res = Closeness(thispos);
                int finalThispos = thispos;

                if (arr2[finalThispos]==0) {
                    arr2[finalThispos]=1;
                Message m = mHandler.obtainMessage(game.W) ;
                m.arg1 = finalThispos;
                mHandler.sendMessage(m);
                Handler hp2 = new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message msg) {
                        int what = msg.what;
                        switch (res) {
                            case 0:
                                mHandler.post(new Runnable() {
                                    public void run() {
                                        over = true;
                                        result = findViewById(R.id.result);
                                        result.setText("Player 2");

                                    }
                                });
                        }
                    }
                };

                if (res == 0) {
                    over = true;
                    mHandler.post(new Runnable() {
                        public void run() {
                            result = findViewById(R.id.result);
                            result.setText("Player 2");
                        }
                    });
                    break;
                }

                try { Thread.sleep(2000); }
                catch (InterruptedException e) {
                    System.out.println("Thread interrupted!");
                }
                }
            }

        }
    }

    public int Closeness(int position) {

        int Row = position/10;
        int Col = position%10;
        int G_Row = G_POS/10;
        int G_Col = G_POS%10;

        if (position == G_POS) {
            return 0;
        } else if ((G_Row==Row+1 || G_Row==Row-1 || G_Row==Row) && (G_Col==Col+1 || G_Col==Col-1 || G_Col==Col)) {
            return 1;
        } else if ((G_Row==Row+1 || G_Row==Row-1 || G_Row==Row || G_Row==Row+2 || G_Row==Row-2) && (G_Col==Col+1 || G_Col==Col-1 || G_Col==Col || G_Col==Col+2 || G_Col==Col-2)) {
            return 2;
        } else
            return 3;
    }
}