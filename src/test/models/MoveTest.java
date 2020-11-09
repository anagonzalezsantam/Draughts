package test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.models.Color;
import main.models.Coordinate;
import main.models.Game;

public class MoveTest {

	private Game game;
	private Game expectedGame;
	
	@Test
	public void testSimpleMoveWhitePawn() {
		game = new GameBuilder("        ",
							   "        ",
							   "        ",
							   "        ",
							   "        ",
							   "b       ",
							   "        ",
							   "        ").build();
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		expectedGame = new GameBuilder("        ",
									   "        ",
									   "        ",
									   "        ",
									   " b      ",
									   "        ",
									   "        ",
									   "        ").color(Color.BLACK).build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testSimpleMoveBlackPawn() {
		game = new GameBuilder("        ",
							   "        ",
							   " n      ",
							   "        ",
							   "        ",
							   "        ",
							   "        ",
							   "        ").color(Color.BLACK).build();
		game.move(new Coordinate(2,1), new Coordinate(3,0));
		expectedGame = new GameBuilder("        ",
									   "        ",
									   "        ",
									   "n       ",
									   "        ",
									   "        ",
									   "        ",
									   "        ").build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testSimpleMoveWhiteDraught() {
		game = new GameBuilder("        ",
							   "        ",
							   "        ",
							   "        ",
							   "        ",
							   "B       ",
							   "        ",
							   "        ").build();
		game.move(new Coordinate(5,0), new Coordinate(4,1));
		expectedGame = new GameBuilder("        ",
									   "        ",
									   "        ",
									   "        ",
									   " B      ",
									   "        ",
									   "        ",
									   "        ").color(Color.BLACK).build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testSimpleMoveBlackDraught() {
		game = new GameBuilder("        ",
							   "        ",
							   " N      ",
							   "        ",
							   "        ",
							   "        ",
							   "        ",
							   "        ").color(Color.BLACK).build();
		game.move(new Coordinate(2,1), new Coordinate(3,0));
		expectedGame = new GameBuilder("        ",
				"        ",
				"        ",
				"N       ",
				"        ",
				"        ",
				"        ",
				"        ").build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testEatWhitePawn() {
		game = new GameBuilder("        ",
							   "        ",
							   "        ",
							   "        ",
							   " n      ",
							   "b       ",
							   "        ",
							   "        ").build();
		game.move(new Coordinate(5,0), new Coordinate(3,2));
		expectedGame = new GameBuilder("        ",
					    			   "        ",
									   "        ",
									   "  b     ",
									   "        ",
									   "        ",
									   "        ",
									   "        ").color(Color.BLACK).build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testEatBlackPawn() {
		game = new GameBuilder("        ",
							   "        ",
							   " n      ",
							   "  b     ",
							   "        ",
							   "        ",
							   "        ",
							   "        ").color(Color.BLACK).build();
		game.move(new Coordinate(2,1), new Coordinate(4,3));
		expectedGame = new GameBuilder("        ",
									   "        ",
									   "        ",
									   "        ",
									   "   n    ",
									   "        ",
									   "        ",
									   "        ").build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testEatWhiteDraught() {
		game = new GameBuilder("        ",
							   "        ",
							   "   n    ",
							   "        ",
							   " n      ",
							   "B       ",
							   "        ",
							   "        ").build();
		game.move(new Coordinate(5,0), new Coordinate(3,2), new Coordinate(1,4));
		expectedGame = new GameBuilder("        ",
									   "    B   ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ",
									   "        ").color(Color.BLACK).build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testEatBlackDraught() {
		game = new GameBuilder ("        ",
								"        ",
								" N      ",
								"  b     ",
								"        ",
								"    b   ",
								"        ",
								"        ").color(Color.BLACK).build();
		game.move(new Coordinate(2,1), new Coordinate(4,3), new Coordinate(6,5));
		expectedGame = new GameBuilder ("        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"     N  ",
										"        ").build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testWhitePawnIntoDraught() {
		game = new GameBuilder ("        ",
								"b       ",
								"        ",
								"        ",
								"        ",
								"        ",
								"        ",
								"        ").build();
		game.move(new Coordinate(1,0), new Coordinate(0,1));
		expectedGame = new GameBuilder (" B      ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ").color(Color.BLACK).build();
		assertEquals(expectedGame, game);
	}
	
	@Test
	public void testBlackPawnIntoDraught() {
		game = new GameBuilder ("        ",
								"        ",
								"        ",
								"        ",
								"        ",
								"        ",
								" n      ",
								"        ").color(Color.BLACK).build();
		game.move(new Coordinate(6,1), new Coordinate(7,2));
		expectedGame = new GameBuilder ("        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"  N     ").build();
		assertEquals(expectedGame, game);
	}
	
}