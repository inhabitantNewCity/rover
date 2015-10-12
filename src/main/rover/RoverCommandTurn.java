package main.rover;

import utils.Direction;

/**
 * Created by Владислав on 10.10.2015.
 */
public class RoverCommandTurn implements RoverCommand {
    Direction direction;
    public RoverCommandTurn(Direction direction){

        this.direction = direction;
    }
    @Override
    public void setParameters(Object[] parameters) {
        direction = (Direction)parameters[0];
    }

    @Override
    public void executeCommand(ProgrammableRover rover) {
        rover.turn(direction);
    }
}
