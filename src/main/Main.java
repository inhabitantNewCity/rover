package main;

import main.ground.Ground;
import main.ground.GroundVisor;
import main.rover.ProgrammableRover;
import main.rover.Rover;
import utils.Direction;
import utils.Point;
import utils.RoverCommandParser;
import utils.massagesystem.MessageToRover;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.LogManager;

/**
 * Created by Владислав on 07.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/recourse/logging.properties"));
           // LogManager.getLogManager().readConfiguration(
             //       Main.class.getResourceAsStream("src/recourse/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        HashMap<String, Object> map = new HashMap<>();

        Ground ground = new Ground();
        ground.init();

        GroundVisor groundVisor = new GroundVisor(ground);

        ProgrammableRover rover1 = new ProgrammableRover(groundVisor,map);
        MessageToRover.initMessageToRover(ProgrammableRover.getQueue());

        RoverCommandParser parser = new RoverCommandParser(map, ground);
        parser.run();

        while(rover1.isWorking()) {
            rover1.run();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ground.print();

        Rover rover = new Rover(groundVisor,map);
        rover.move();
        rover.lift();
        rover.land(new Point(0, 2));
        rover.turn(Direction.NORTH);

        ground.print();
    }
}
