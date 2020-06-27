package pl.pwr.s241936.autka_android_v2;

public class Car {

    private int x1_pos;
    private int y1_pos;
    private int car_width = 75;
    private int car_hight = 150;

    public Car(int x1, int y1) {
        this.x1_pos = x1;
        this.y1_pos = y1;
    }

    public int get_x_pos() {
        return x1_pos;
    }

    public int get_y_pos() {
        return y1_pos;
    }

    public int get_car_width(){
        return car_width;
    }

    public int get_car_hight(){
        return car_hight;
    }

    public void set_x_pos(int x) {
        this.x1_pos = x;
    }

    public void set_y_pos(int y) {
        this.y1_pos = y;
    }
}