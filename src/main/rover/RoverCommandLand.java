package main.rover;

import utils.Point;

/**
 * Created by Владислав on 10.10.2015.
 */
public class RoverCommandLand implements RoverCommand {
    private Point point;
    public RoverCommandLand(Point point){
        this.point = point;
    }
    @Override
    public void setParameters(Object[] parameters) {
        point = (Point)(parameters[0]);
    }

    @Override
    public void executeCommand(ProgrammableRover rover) {
        rover.land(point);
    }
}
