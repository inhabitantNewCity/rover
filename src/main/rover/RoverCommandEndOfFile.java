package main.rover;

/**
 * Created by Владислав on 10.10.2015.
 */
public class RoverCommandEndOfFile implements RoverCommand {
    @Override
    public void setParameters(Object[] parameters) {

    }

    @Override
    public void executeCommand(ProgrammableRover rover) {
        rover.stopWorking();
    }
}
