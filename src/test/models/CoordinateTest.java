package test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import main.models.Coordinate;
import main.models.Direction;

public class CoordinateTest {

	@Test
	public void testGetInstance() {
		assertEquals(new Coordinate(6,2), Coordinate.getInstance("73"));
		assertNull(Coordinate.getInstance("99"));
		assertNull(Coordinate.getInstance("00"));
		assertNull(Coordinate.getInstance("1"));
		assertNull(Coordinate.getInstance("111"));
	}
	
	@Test
	public void testGetDirection() {
		assertEquals(Direction.NE, new Coordinate(4,4).getDirection(new Coordinate(5,5)));
		assertEquals(Direction.NW, new Coordinate(4,4).getDirection(new Coordinate(5,3)));
		assertEquals(Direction.SE, new Coordinate(4,4).getDirection(new Coordinate(3,5)));
		assertEquals(Direction.SW, new Coordinate(4,4).getDirection(new Coordinate(3,3)));
		assertNull(new Coordinate(4,4).getDirection(new Coordinate(0,3)));
	}
	
	@Test
	public void testIsOnDiagonal() {
		assertTrue(new Coordinate(4,4).isOnDiagonal(new Coordinate(5,5)));
		assertTrue(new Coordinate(3,4).isOnDiagonal(new Coordinate(4,5)));
		assertFalse(new Coordinate(3,4).isOnDiagonal(new Coordinate(3,4)));
		assertFalse(new Coordinate(3,4).isOnDiagonal(new Coordinate(3,5)));
	}
	
	@Test
	public void testGetDiagonalDistance() {
		assertEquals(1, new Coordinate(2,2).getDiagonalDistance(new Coordinate(3,3)));
		assertEquals(2, new Coordinate(2,4).getDiagonalDistance(new Coordinate(4,6)));
		assertEquals(2, new Coordinate(6,7).getDiagonalDistance(new Coordinate(4,5)));
		assertEquals(3, new Coordinate(2,1).getDiagonalDistance(new Coordinate(-1,-2)));
	}
	
	@Test
	public void testGetBetweenDiagonalCoordinate() {
		assertEquals(new Coordinate(3,3), new Coordinate(2,2).getBetweenDiagonalCoordinate(new Coordinate(4,4)));
		assertEquals(new Coordinate(2,4), new Coordinate(1,3).getBetweenDiagonalCoordinate(new Coordinate(3,5)));		
	}
	
	@Test
	public void testGetBetweenDiagonalCoordinates() {
		assertEquals(new ArrayList<Coordinate>(), new Coordinate(1,1).getBetweenDiagonalCoordinates(new Coordinate(2,2)));
		ArrayList<Coordinate> coordinates = new ArrayList<>();
		coordinates.add(new Coordinate(2,2));
		coordinates.add(new Coordinate(3,3));
		coordinates.add(new Coordinate(4,4));
		assertEquals(coordinates, new Coordinate(1,1).getBetweenDiagonalCoordinates(new Coordinate(5,5)));
	}
	
	@Test
	public void testGetDiagonalCoordinates() {
		ArrayList<Coordinate> diagonals = new ArrayList<>();
		diagonals.add(new Coordinate(3,3));
		diagonals.add(new Coordinate(1,3));
		diagonals.add(new Coordinate(1,1));
		diagonals.add(new Coordinate(3,1));
		assertEquals(diagonals, new Coordinate(2,2).getDiagonalCoordinates(1));
		
		ArrayList<Coordinate> diagonals2 = new ArrayList<>();
		diagonals2.add(new Coordinate(5,5));
		diagonals2.add(new Coordinate(1,5));
		diagonals2.add(new Coordinate(1,1));
		diagonals2.add(new Coordinate(5,1));
		assertEquals(diagonals2, new Coordinate(3,3).getDiagonalCoordinates(2));
		
		ArrayList<Coordinate> diagonals3 = new ArrayList<>();
		diagonals3.add(new Coordinate(2,3));
		assertEquals(diagonals3, new Coordinate(0,1).getDiagonalCoordinates(2));
	}
	
}
