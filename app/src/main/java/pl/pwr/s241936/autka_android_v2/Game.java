package pl.pwr.s241936.autka_android_v2;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;

import android.view.View;



public class Game extends View   {

    Runnable runnable;
    Handler handler;
    private Paint p, p1, p2,p3,p4;
    private int width = 1080;
    private int hight = 1920;
    private boolean out_of_map = false;
    private boolean game_over = false;
    private boolean start = false;
    private int speed = 6;
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

        canvas.drawRect(0,0,width,hight,p);
        canvas.drawRect(240,0,840,hight,p1);
        canvas.drawRect(player.get_x_pos(), player.get_y_pos(), player.get_x_pos()+75, player.get_y_pos()+150,p3); // (pozycja x, pozycja y, szerokość, wysokość)
        canvas.drawRect(enemy1.get_x_pos(), enemy1.get_y_pos(), enemy1.get_x_pos()+75,  enemy1.get_y_pos()+150,p2);
        canvas.drawRect(enemy2.get_x_pos(), enemy2.get_y_pos(), enemy2.get_x_pos()+75, enemy2.get_y_pos()+150,p2);

        handler.postDelayed(runnable,speed);


        if(main.update_x() < -2.5 ){

            x=player.get_x_pos()+5;


        }
        if(main.update_x() > 2.5  ){
            x=player.get_x_pos()-5;


        }

        enemy1.set_y_pos(enemy1.get_y_pos()+5);
        enemy2.set_y_pos(enemy2.get_y_pos()+5);
        canvas.drawRect(enemy1.get_x_pos(), enemy1.get_y_pos(), enemy1.get_x_pos()+75,  enemy1.get_y_pos()+150,p2);
        canvas.drawRect(enemy2.get_x_pos(), enemy2.get_y_pos(), enemy2.get_x_pos()+75, enemy2.get_y_pos()+150,p2);

        player.set_x_pos(x);
        player.set_y_pos(y);

        canvas.drawRect(player.get_x_pos(), player.get_y_pos(), player.get_x_pos()+75, player.get_y_pos()+150,p3);
    }
}
