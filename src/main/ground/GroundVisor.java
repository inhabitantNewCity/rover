package main.ground;

import errors.OutOfGroundException;
import utils.Point;

/**
 * Created by Владислав on 07.10.2015.
 */
public class GroundVisor {
    private Ground ground;

    public GroundVisor(Ground ground) {
        this.ground = ground;
    }

    public boolean checkCell(Point point) throws OutOfGroundException {
        GroundCell tmp = ground.getCell(point);
        if (tmp.getPoint().getX() == -1)
            throw new OutOfGroundException();
        if (tmp.isFree())
            return true;
        return false;
    }
}
