package test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import main.models.Coordinate;
import main.models.Direction;

public class DirectonTest {

	@Test
    public void testIsOnDirection(){
        assertTrue (Direction.NE.isOnDirection(new Coordinate(3,3)));
        assertFalse(Direction.NE.isOnDirection(new Coordinate(0,0)));
        assertTrue (Direction.SE.isOnDirection(new Coordinate(-2,2)));
        assertFalse(Direction.SE.isOnDirection(new Coordinate(2,-2)));
        assertTrue (Direction.SW.isOnDirection(new Coordinate(-4,-4)));
        assertFalse(Direction.SW.isOnDirection(new Coordinate(-1,-4)));
        assertTrue (Direction.NW.isOnDirection(new Coordinate(8,-8)));
        assertFalse(Direction.NW.isOnDirection(new Coordinate(3,3)));
    }
	
	@Test
	public void testGetDistance() {
		assertEquals(new Coordinate(1,1), Direction.NE.getDistanceCoordinate(1));
		assertEquals(new Coordinate(2,-2), Direction.NW.getDistanceCoordinate(2));
		assertEquals(new Coordinate(-3,3), Direction.SE.getDistanceCoordinate(3));
		assertEquals(new Coordinate(0,0), Direction.SW.getDistanceCoordinate(0));
	}

}
