package main.rover;

/**
 * Created by Владислав on 10.10.2015.
 */
public class RoverCommandLift implements RoverCommand {
    @Override
    public void setParameters(Object[] parameters) {
        //
    }

    @Override
    public void executeCommand(ProgrammableRover rover) {
        rover.lift();
    }
}
