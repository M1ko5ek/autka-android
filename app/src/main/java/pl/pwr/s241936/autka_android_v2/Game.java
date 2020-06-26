package pl.pwr.s241936.autka_android_v2;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;

import android.view.View;

import java.util.concurrent.ThreadLocalRandom;


public class Game extends View   {

    Runnable runnable;
    Handler handler;
    private Paint p, p1, p2,p3,p4;
    private int width = 1080;
    private int hight = 1920;
    private boolean out_of_map = false;
    private boolean game_over = false;
    private boolean start = false;
    private int speed = 5;
    private int points = 0;


    private MainActivity main = new MainActivity();
    private Car player = new Car(width/2,hight-(hight/5),width/2+50,hight-(hight/5)+100);;
    private Car enemy1 = new Car(340,0,500,250);
    private Car enemy2 = new Car(740,0,500,350);

    int x = player.get_x_pos();
    int y = player.get_y_pos();




    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        handler = new android.os.Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };


        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.GREEN);
        p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setColor(Color.BLACK);
        p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p2.setColor(Color.RED);
        p3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p3.setColor(Color.BLUE);
        p4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p4.setColor(Color.WHITE);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,width,hight+100,p);
        canvas.drawRect(240,0,840,hight+100,p1);
        canvas.drawRect(player.get_x_pos(), player.get_y_pos(), player.get_x_pos()+75, player.get_y_pos()+150,p3); // (pozycja x, pozycja y, szerokość, wysokość)


        handler.postDelayed(runnable,speed);


        if(main.update_x() < -2.5 ){

            x=player.get_x_pos()+5;
        }
        if(main.update_x() > 2.5  ){
            x=player.get_x_pos()-5;
        }

        player.set_x_pos(x);
        player.set_y_pos(y);
        canvas.drawRect(player.get_x_pos(), player.get_y_pos(), player.get_x_pos()+75, player.get_y_pos()+150,p3);


        if(out_of_map == true) {
            int x = ThreadLocalRandom.current().nextInt(240, 640 );
            int y = ThreadLocalRandom.current().nextInt(665, 840-75 );
            enemy1.set_x_pos(x);
            enemy2.set_x_pos(y);
        }

        canvas.drawRect(enemy1.get_x_pos(), enemy1.get_y_pos(), enemy1.get_x_pos()+75,  enemy1.get_y_pos()+150,p2);
        canvas.drawRect(enemy2.get_x_pos(), enemy2.get_y_pos(), enemy2.get_x_pos()+75, enemy2.get_y_pos()+150,p2);


        enemy_movement(enemy1, enemy2);

        if(points == 10)
        {
            speed = 2;
        }

    }
    void enemy_movement(Car e1, Car e2)
    {
        Car enemy1 = e1;
        Car enemy2 = e2;

        if (out_of_map == false) {
            int x = enemy1.get_y_pos();
            int y = x + 5;
            enemy1.set_y_pos(y);
            enemy2.set_y_pos(y);

            if (enemy1.get_y_pos() == hight+50) {
                out_of_map = true;
            }

        } else if (out_of_map == true) {
            enemy1.set_y_pos(0);
            enemy2.set_y_pos(0);
            points++;
            if (speed > 3) {
                speed--;
            }
            out_of_map = false;


        }
    }
}
