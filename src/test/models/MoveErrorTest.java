package test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.models.Coordinate;
import main.models.Game;
import main.models.Error;

public class MoveErrorTest {

	private Game game;
	
	@BeforeEach
	public void before() {
		game = new Game();		
	}
	
	@Test
	public void testOppositePiece() {
		assertEquals(Error.OPPOSITE_PIECE, game.move(new Coordinate(2,1), new Coordinate(1,2)));
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		assertEquals(Error.OPPOSITE_PIECE, game.move(new Coordinate(5,2), new Coordinate(6,1)));
	}
	
	@Test
	public void testEmptyOrigin() {
		assertEquals(Error.EMPTY_ORIGIN, game.move(new Coordinate(5,1), new Coordinate(4,2)));
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		assertEquals(Error.EMPTY_ORIGIN, game.move(new Coordinate(2,0), new Coordinate(3,1)));
	}
	
	@Test
	public void testNotDiagonal() {
		assertEquals(Error.NOT_DIAGONAL, game.move(new Coordinate(5,0), new Coordinate(4,0)));
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		assertEquals(Error.NOT_DIAGONAL, game.move(new Coordinate(2,1), new Coordinate(2,2)));
	}
	
	@Test
	public void testNotEmptyTarget() {
		assertEquals(Error.NOT_EMPTY_TARGET, game.move(new Coordinate(6,1), new Coordinate(5,0)));
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		assertEquals(Error.NOT_EMPTY_TARGET, game.move(new Coordinate(1,0), new Coordinate(2,1)));
	}
	
	@Test
	public void testCollegueEating() {
		assertEquals(Error.COLLEAGUE_EATING, game.move(new Coordinate(6,1), new Coordinate(4,3)));
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		assertEquals(Error.COLLEAGUE_EATING, game.move(new Coordinate(1,0), new Coordinate(3,2)));
	}
}
