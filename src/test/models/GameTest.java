package test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.models.Color;
import main.models.Coordinate;
import main.models.Game;

public class GameTest {
	
	private GameBuilder initialGame;
	private Game game;
	
	public GameTest() {
		initialGame = new GameBuilder(" n n n n",
						              "n n n n ",
						              " n n n n",
						              "        ",
						              "        ",
						              "b b b b ",
						              " b b b b",
						              "b b b b ");
	}
	
	@BeforeEach
	public void before() {
		game = new Game();
	}
	
	@Test
	public void testCreateGame() {
		assertEquals(initialGame.build(), game);
	}
	
	@Test
	public void testResetGame() {
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		assertFalse(game.equals(initialGame));
		game.reset();
		assertEquals(initialGame.build(), game);
	}
	
	@Test
	public void testCancelGame() {
		assertEquals(Color.WHITE, game.getTurnColor());
		game.cancel();
		assertEquals(Color.BLACK, game.getTurnColor());
		GameBuilder restartedGame = new GameBuilder(" n n n n",
									                "n n n n ",
									                " n n n n",
									                "        ",
									                "        ",
									                "        ",
									                "        ",
									                "        ");
		assertEquals(restartedGame.color(Color.BLACK).build(), game);
	}
	
	@Test
	public void testIsBloqued() {
		GameBuilder game_bloqued;
		game_bloqued = new GameBuilder("        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "  n     ",
									   " n      ",
									   "b       ");
		assertTrue(game_bloqued.build().isBlocked());
		game_bloqued = new GameBuilder("        ",
									   "n       ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ");
		assertTrue(game_bloqued.build().isBlocked());
		game_bloqued = new GameBuilder("        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ");
		assertTrue(game_bloqued.build().isBlocked());
		assertFalse(initialGame.build().isBlocked());
	}

}
