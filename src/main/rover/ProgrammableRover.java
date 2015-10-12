package main.rover;

import main.ground.GroundVisor;
import utils.Direction;
import utils.Point;
import utils.ProgramFileAware;
import utils.massagesystem.Msg;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Владислав on 07.10.2015.
 */
public class ProgrammableRover extends Rover implements ProgramFileAware{

    private static final ConcurrentLinkedQueue<Msg> queue = new ConcurrentLinkedQueue<>();
    private boolean working = true;

    public ProgrammableRover(GroundVisor visor, HashMap<String,Object> hashMap) {
        super(visor,hashMap);
    }

    public static ConcurrentLinkedQueue<Msg> getQueue(){
        return queue;
    }
    public void stopWorking(){
        this.working = false;
    }
    public boolean isWorking(){
        return working;
    }
    @Override
    public void run() {
        Msg msg;
        while(isWorking()) {
            if(!(queue.isEmpty())) {
                msg = queue.remove();
                RoverCommand command = (RoverCommand) msg.getText();
                command.executeCommand(this);
            }else {
                try {
                   Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

