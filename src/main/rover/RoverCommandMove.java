package main.rover;

import utils.ProgramFileAware;

/**
 * Created by Владислав on 09.10.2015.
 */
public class RoverCommandMove implements RoverCommand {
    private static final int thisNumber = 1;
    @Override
    public void setParameters(Object[] parameters) {
        //return new Object[0];
    }

    @Override
    public void executeCommand(ProgrammableRover rover) {
        rover.move();
    }


}
