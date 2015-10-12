package main.ground;


import utils.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Владислав on 07.10.2015.
 */
public class Ground {
    private ArrayList<GroundCell> field = new ArrayList<>();
    public void init(){
               
        Random random = new Random();
        int length = random.nextInt(6) + 4;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <length ; j++) {
                //GroundCell tmp = new GroundCell(new Point(i,j));
                field.add(new GroundCell(new Point(i,j)));
            }
        }
        Random busyCell = new Random();
        int numberBusyCell = busyCell.nextInt( (int)(length / 2 * length) );
        System.out.println(numberBusyCell);
        for (int i = 0; i < numberBusyCell ; i++) {
            field.get(busyCell.nextInt(length * length)).setFree(false);
        }
    }
    public GroundCell getCell(Point point){
        for(GroundCell cell : field) {
            if(cell.isYou(point))
                return cell;
        }
        return new GroundCell(new Point(-1,-1));
    }
    public void print(){
        for (GroundCell cell: field){
            System.out.println(cell.toString());
        }
    }
}
