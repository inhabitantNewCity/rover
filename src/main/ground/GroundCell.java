package main.ground;

import utils.Point;

/**
 * Created by Владислав on 07.10.2015.
 */
public class GroundCell {

    private Point point;
    private boolean free = true;
    private boolean visited = false;

    GroundCell(Point point){
        this.point = point;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Point getPoint(){
        return point;
    }
    public void setFree(boolean free){
        this.free = free;
    }
    public boolean isFree(){
        return free;
    }
    public boolean isYou(Point point){
        Point tmp = this.getPoint();
        if((tmp.getX() == point.getX()) && (tmp.getY() == point.getY()))
            return true;
        return false;
    }
    public String toString(){
        return "x = " + point.getX() + " y = " + point.getY() + " free = " + free + " visited = " + visited;
    }
}
