package main.rover;

import utils.ProgramFileAware;

/**
 * Created by Владислав on 07.10.2015.
 */
public interface RoverCommand {

    void setParameters(Object[] parameters);
    void executeCommand(ProgrammableRover rover);
}
