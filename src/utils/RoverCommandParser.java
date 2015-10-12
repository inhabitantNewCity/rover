package utils;

import errors.RoverCommandParserException;
import main.ground.Ground;
import main.rover.*;
import utils.massagesystem.MessageToRover;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by Владислав on 07.10.2015.
 */
public class RoverCommandParser implements Runnable {

    private final String fileName = "src/recourse/program.txt";
    private HashMap<String,Object> map;
    private Ground ground;
    private static Logger log;
    public RoverCommandParser(HashMap<String,Object> hashMap, Ground ground){
        map = hashMap;
        this.ground = ground;
    }

    private void parse(String str){
        if(str.equals("===")){
            System.out.println("parameters is setting");
            return;
        }
        if(str.equals("")){
            new MessageToRover(new RoverCommandEndOfFile());
            return;
        }
        int indexEndString = str.indexOf(" ");
        String tmp = str.substring(0,(indexEndString == -1)?(str.length()):indexEndString);
        //System.out.println(tmp);
        switch (tmp) {
            case "move":
               new  MessageToRover( new RoverCommandMove()).send();
                if(log != null){
                    log.info("Command move was fined");
                }
                break;
            case "turn":
                /*System.out.println("tuuurn");*/
                Direction direction = null;
                tmp = str.substring(indexEndString + 1,str.length());
                switch (tmp){
                    case "SOUGHT": direction = Direction.SOUGHT; break;
                    case "NORTH": direction = Direction.NORTH; break;
                    case "WEST": direction = Direction.WEST; break;
                    case "EAST": direction = Direction.EAST; break;
             }
                new MessageToRover((new RoverCommandTurn(direction))).send();
                if(log != null){
                    log.info("Command turn was fined");
                }break;
            case "lift":

                new  MessageToRover( new RoverCommandLift()).send();
                if(log != null){
                    log.info("Command lift was fined");
                }break;
            case "land":
                /*System.out.println("laaand");*/
                int indexCommas = str.indexOf(",");
                String fistComponent = str.substring(indexEndString + 1, indexCommas);
                String lastComponent = str.substring(indexCommas + 1,str.length());
                Point point = new Point(Integer.parseInt(fistComponent),Integer.parseInt(lastComponent));
                new  MessageToRover( new RoverCommandLand(point)).send();
                if(log != null){
                    log.info("Command land was fined");
                }break;
        }
    }
    private void parseLog(String str){

        if((str.substring(str.indexOf('=') + 2,str.indexOf(']'))).equals("true")){
          //TODO: job with log
            log = Logger.getLogger(RoverCommandParser.class.getName());
            System.out.println("true log");
        }
    }
    private void parseStats(String str){
        if((str.substring(str.indexOf('=') + 2,str.indexOf(']'))).equals("true")){
            //TODO: job with status
            SimpleRoverStatsModule statsModule = new SimpleRoverStatsModule(ground);
            map.put("status",statsModule);
            System.out.println("true status");
        }
    }
    public void run(){

        try {

            BufferedReader stream = new BufferedReader(new FileReader(fileName));

            parseLog(stream.readLine());
            parseStats(stream.readLine());

            String string;
            while ( ((string = stream.readLine()) != null)&&(!string.equals("")))
            {
                parse(string);
            }
            new MessageToRover( new RoverCommandEndOfFile()).send();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RoverCommandParserException();
        }

        System.out.println();
    }
}
