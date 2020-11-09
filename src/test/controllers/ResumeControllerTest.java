package test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.controllers.ResumeController;
import main.models.Game;
import main.models.State;
import main.models.StateValue;
import test.models.GameBuilder;

public class ResumeControllerTest {

	private ResumeController resumeController;
	private State state;
	private Game game;
	
	@BeforeEach
	public void before() {
		game = new GameBuilder ("        ",
								"        ",
								"        ",
								"        ",
								"        ",
								"        ",
								" b      ",
								"n       ").build();
		state = new State();
		state.next(); //State = IN_GAME
		state.next(); //State = FINAL
		resumeController = new ResumeController(game, state);
	}
	
	@Test
	public void testNext() {
		assertEquals(StateValue.FINAL, state.getValueState());
		resumeController.next();
		assertEquals(StateValue.EXIT, state.getValueState());
	}
	
	@Test
	public void testRestart() {
		assertEquals(StateValue.FINAL, state.getValueState());
		resumeController.reset();
		assertEquals(StateValue.INITIAL, state.getValueState());
		Game resetedGame = new GameBuilder(" n n n n",
										   "n n n n ",
										   " n n n n",
										   "        ",
										   "        ",
										   "b b b b ",
										   " b b b b",
										   "b b b b ").build();
		assertEquals(resetedGame, game);
	}
}
