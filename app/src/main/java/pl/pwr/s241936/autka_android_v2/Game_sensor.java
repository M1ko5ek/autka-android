package pl.pwr.s241936.autka_android_v2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

public class Game_sensor extends View   {

    Runnable runnable;
    Handler handler;
    private Paint p, p1, p2,p3,p4;
    private int width = 1080;
    private int hight = 1920;
    private boolean out_of_map = false;
    private boolean out_of_map2 = false;
    private boolean game_over = false;
    private boolean start = false;
    private int speed = 7;
    private int points = 0;

    private MainActivity2 main = new MainActivity2();
    private Car player = new Car(width/2,hight-(hight/5));
    private Car enemy1 = new Car(340,0);   // x1 y1
    private Car enemy2 = new Car(650,0);
    private Car enemy3 = new Car(500,-hight/2);
    int x = player.get_x_pos();
    int y = player.get_y_pos();

    public Game_sensor(Context context, AttributeSet attrs) {
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
        p4.setTextSize(64);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,width,hight+100,p);
        canvas.drawRect(240,0,840,hight+100,p1);
        canvas.drawRect(player.get_x_pos(), player.get_y_pos(), player.get_x_pos()+player.get_car_width(), player.get_y_pos()+player.get_car_hight(),p3);

        handler.postDelayed(runnable, 1);

        if (start == false) {
            canvas.drawText("TAP TO START", 335, 1000, p4);
        }

        if(start == true && game_over == false)
        {
            player.set_x_pos(x);
            player.set_y_pos(y);
            canvas.drawRect(player.get_x_pos(), player.get_y_pos(), player.get_x_pos()+player.get_car_width(), player.get_y_pos()+player.get_car_hight(),p3);
        }

        control();

        canvas.drawRect(enemy1.get_x_pos(), enemy1.get_y_pos(), enemy1.get_x_pos()+enemy1.get_car_width(), enemy1.get_y_pos()+enemy1.get_car_hight(),p2);
        canvas.drawRect(enemy2.get_x_pos(), enemy2.get_y_pos(), enemy2.get_x_pos()+enemy2.get_car_width(), enemy2.get_y_pos()+enemy2.get_car_hight(),p2);
        canvas.drawRect(enemy3.get_x_pos(), enemy3.get_y_pos(), enemy3.get_x_pos()+enemy3.get_car_width(), enemy3.get_y_pos()+enemy3.get_car_hight(),p2);

        colision(enemy1);
        colision(enemy2);
        colision(enemy3);

        if(start == true && game_over==false)
        {
            enemy_movement();
        }

        add_points();

        if(points == 10)
        {
            speed = 20;
        }

        if(game_over==true)
        {
            canvas.drawText("GAME OVER", 375, 1000, p4);
            canvas.drawText("Points: " + points,425,1100,p4);
        }
    }

    void control()
    {
        if(main.update_x() < -2.5 && player.get_x_pos() < 840-75){

            x=player.get_x_pos()+5;
        }
        if(main.update_x() > 2.5 && player.get_x_pos() > 240  ){
            x=player.get_x_pos()-5;
        }
    }

    void enemy_movement()
    {
        if(out_of_map == true) {
            int random1 = new Random().nextInt(225) + 240;
            int random2 = new Random().nextInt(225) + 540;
            enemy1.set_x_pos(random1);
            enemy2.set_x_pos(random2);
        }

        if (out_of_map == false) {

            enemy1.set_y_pos(enemy1.get_y_pos()+speed);
            enemy2.set_y_pos(enemy2.get_y_pos()+speed);
            if (enemy1.get_y_pos() >= hight) {
                out_of_map = true;
            }

        } else if (out_of_map == true) {
            enemy1.set_y_pos(0);
            enemy2.set_y_pos(0);

            if (speed < 20) {
                speed++;
            }
            out_of_map = false;
        }

        if(out_of_map2 == false)
        {
            enemy3.set_y_pos(enemy3.get_y_pos()+speed);
            if (enemy3.get_y_pos() >= hight) {
                out_of_map2 = true;
            }
        } else if (out_of_map2 == true)
        {
            enemy3.set_x_pos(player.get_x_pos());
            enemy3.set_y_pos(0);
            out_of_map2 = false;
        }
    }

    void colision(Car e) {

        int x_player = player.get_x_pos();
        int y_player = player.get_y_pos();
        int x_enemy = e.get_x_pos();
        int y_enemy = e.get_y_pos();


        if (x_player >= x_enemy && x_player <= x_enemy + e.get_car_width() && y_player >= y_enemy && y_player <= y_enemy + e.get_car_hight() ||
                x_player + player.get_car_width() >= x_enemy && x_player <= x_enemy + e.get_car_width() && y_player + player.get_car_hight()>= y_enemy && y_player <= y_enemy + e.get_car_hight()) {
            game_over = true;
        }
    }

   void add_points()
    {
        if(out_of_map == true) {
            points++;
        }
        if(out_of_map2 == true)
        {
            points++;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        start = true;
        return true;
    }
}
