package utils;

/**
 * Created by ��������� on 07.10.2015.
 */
public class Point {
    int x;
    int y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public  int getY(){
        return y;
    }
    public String toString(){
        return "x = " + x + " y = "+ y;
    }
}
