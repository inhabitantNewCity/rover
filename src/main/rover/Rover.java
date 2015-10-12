package main.rover;

import action.*;
import errors.OutOfGroundException;
import main.ground.GroundVisor;
import utils.Direction;
import utils.Point;
import utils.SimpleRoverStatsModule;

import java.util.HashMap;


/**
 * Created by Владислав on 07.10.2015.
 */
public class Rover implements Landable, Liftable, Moveable, Turnable{
    private Point location = new Point(0,0);
    private Direction direction = Direction.SOUGHT;
    private boolean flight = false;
    private GroundVisor view;
    private HashMap<String,Object> map;

    public Rover(GroundVisor visor,HashMap<String,Object> hashMap){
        map = hashMap;
        view = visor;
    }

    @Override
    public void land(Point point) {
        try {
            boolean check = view.checkCell(point);
            if (check) {
                location = point;
                flight = false;
                System.out.println("I go to x = " + location.getX()+ " y = " + location.getY());
                if(map.containsKey("status")){
                    SimpleRoverStatsModule status = (SimpleRoverStatsModule) map.get("status");
                    status.setVisited(location);
                }
            }
            else lift();
        }
        catch (OutOfGroundException e){
            lift();
            e.printStackTrace();
        }

    }

    @Override
    public void lift() {
        if(!flight)
            flight = true;
        System.out.println("I fly");
    }

    @Override
    public void move() {
        try {
            switch (direction) {
                case SOUGHT:
                    moveSought();
                    break;
                case NORTH:
                    moveNorth();
                    break;
                case WEST:
                    moveWest();
                    break;
                case EAST:
                    moveEast();
                    break;
            }
        }catch (OutOfGroundException e){
            lift();
            e.printStackTrace();
        }

    }


    private void moveSought() throws OutOfGroundException{
        if(view.checkCell(new Point(location.getX(), (location.getY() + 1)))) {
            location.setY(location.getY() + 1);
            System.out.println("I go to " + location);
            if(map.containsKey("status")){
                SimpleRoverStatsModule status = (SimpleRoverStatsModule) map.get("status");
                status.setVisited(location);
            }
        }
        else lift();
    }


    private void moveNorth() throws OutOfGroundException{
        if(view.checkCell(new Point(location.getX(), (location.getY() - 1)))) {
            location.setY(location.getY() + 1);
            System.out.println("I go to " + location);
            if(map.containsKey("status")){
                SimpleRoverStatsModule status = (SimpleRoverStatsModule) map.get("status");
                status.setVisited(location);
            }
        }
        else lift();
    }

    private void moveWest() throws OutOfGroundException{
        if(view.checkCell(new Point((location.getX() - 1), location.getY()))) {
            location.setY(location.getY() + 1);
            System.out.println("I go to " + location);
            if(map.containsKey("status")){
                SimpleRoverStatsModule status = (SimpleRoverStatsModule) map.get("status");
                status.setVisited(location);
            }
        }
        else lift();
    }

    private void moveEast() throws OutOfGroundException{
        if(view.checkCell(new Point((location.getX() + 1), location.getY() + 1))) {
            location.setY(location.getY() + 1);
            System.out.println("I go to " + location);
            if(map.containsKey("status")){
                SimpleRoverStatsModule status = (SimpleRoverStatsModule) map.get("status");
                status.setVisited(location);
            }
        }
        else lift();
    }

    @Override
    public void turn(Direction direction) {
        this.direction = direction;
        System.out.println("I turn to " + direction );
    }
}
