package test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.controllers.PlayController;
import main.models.Color;
import main.models.Coordinate;
import main.models.Game;
import main.models.State;
import main.models.StateValue;
import test.models.GameBuilder;

public class PlayControllerTest {
	
	private PlayController playController;
	private State state;
	
	@BeforeEach
	public void before() {
		Game game = new GameBuilder("        ",
								    "        ",
								    "        ",
								    "        ",
								    "        ",
								    "  n     ",
								    "        ",
								    "b       ").build();
		state = new State();
		state.next(); //StateValue = IN_GAME
		playController = new PlayController(game,state);
	}
	
	@Test
	public void testIncorrectMovement() {
		assertEquals(Color.WHITE, playController.getColor());
		playController.move(new Coordinate(1,1), new Coordinate(2,2));
		assertEquals(Color.WHITE, playController.getColor());
	}
	
	@Test
	public void testCorrectMovement() {
		assertEquals(Color.WHITE, playController.getColor());
		playController.move(new Coordinate(7,0), new Coordinate(6,1));
		assertEquals(Color.BLACK, playController.getColor());
		assertFalse(playController.isBlocked());
		playController.move(new Coordinate(5,2), new Coordinate(7,0));
		assertTrue(playController.isBlocked());
	}
	
	@Test
	public void testCancel() {
		assertEquals(Color.WHITE, playController.getColor());
		assertEquals(StateValue.IN_GAME, state.getValueState());
		playController.cancel();
		assertEquals(Color.BLACK, playController.getColor());
		assertEquals(StateValue.FINAL, state.getValueState());
	}
}
