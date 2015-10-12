package utils;

import errors.OutOfGroundException;
import main.ground.Ground;
import main.ground.GroundCell;

/**
 * Created by Владислав on 07.10.2015.
 */
public class SimpleRoverStatsModule {
    private Ground ground;
    public SimpleRoverStatsModule(Ground ground){
        this.ground = ground;
    }
    public void setVisited(Point point) throws OutOfGroundException{
        GroundCell tmp = ground.getCell(point);
        if(tmp.getPoint().getX() == -1 )
            throw new OutOfGroundException();
        if(tmp.isVisited())
            return;
        tmp.setVisited(true);
        return;
    };
}
