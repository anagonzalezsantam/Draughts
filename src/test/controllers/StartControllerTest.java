package test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.controllers.StartController;
import main.models.Game;
import main.models.State;
import main.models.StateValue;
import test.models.GameBuilder;

public class StartControllerTest {

	private StartController startController;
	private State state;
	
	@BeforeEach
	public void before() {
		Game game = new GameBuilder().build();
		state = new State();
		startController = new StartController(game, state);
	}
	
	@Test
	public void testStart() {
		assertEquals(StateValue.INITIAL, state.getValueState());
		startController.start();
		assertEquals(StateValue.IN_GAME, state.getValueState());
	}
}
