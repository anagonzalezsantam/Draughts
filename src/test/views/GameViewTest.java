package test.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import static org.mockito.Mockito.times;

import main.controllers.StartController;
import main.models.Color;
import main.models.Coordinate;
import main.models.Game;
import main.models.State;
import main.utils.Console;
import main.views.GameView;
import test.models.GameBuilder;

public class GameViewTest {

	private ArgumentCaptor<String> rowsCaptor;
	@Mock 
	private Console console;
	@InjectMocks
	private GameView gameView;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
		rowsCaptor = ArgumentCaptor.forClass(String.class);
	}
	
	@Test
	public void testWriteInitialBoard() {
		StartController startController = new StartController(new Game(), new State());
		gameView.write(startController);
		Game expected = new GameBuilder(" n n n n",
						                "n n n n ",
						                " n n n n",
						                "        ",
						                "        ",
						                "b b b b ",
						                " b b b b",
						                "b b b b ").build();
		verify(this.console, times(90)).write(rowsCaptor.capture());
		Game capturedGame = this.captureGame(rowsCaptor).build();
		assertEquals(expected, capturedGame);
	}
	
	@Test
	public void testWriteAfterMovement() {
		Game game = new GameBuilder(" n n n n",
									"n n n n ",
									" n   n n",
									"  n     ",
									" b      ",
									"b   b b ",
									" b b b b",
									"b b b b ").build();
		game.move(new Coordinate(4,1), new Coordinate(2,3));
		StartController startController = new StartController(game, new State());
		gameView.write(startController);
		Game expected = new GameBuilder(" n n n n",
						                "n n n n ",
						                " n b n n",
						                "        ",
						                "        ",
						                "b   b b ",
						                " b b b b",
						                "b b b b ").color(Color.BLACK).build();
		verify(this.console, times(90)).write(rowsCaptor.capture());
		Game capturedGame = this.captureGame(rowsCaptor).color(Color.BLACK).build();
		assertEquals(expected, capturedGame);
		assertEquals(game, capturedGame);
	}
	
	@Test
	public void testWriteAfterPawnToDraught() {
		Game game = new GameBuilder("        ",
									"b       ",
									"        ",
									"        ",
									"        ",
									"        ",
									"        ",
									"        ").build();
		game.move(new Coordinate(1,0), new Coordinate(0,1));
		StartController startController = new StartController(game, new State());
		gameView.write(startController);
		Game expected = new GameBuilder(" B      ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ",
										"        ").color(Color.BLACK).build();
		verify(this.console, times(90)).write(rowsCaptor.capture());
		Game capturedGame = this.captureGame(rowsCaptor).color(Color.BLACK).build();
		assertEquals(expected, capturedGame);
		assertEquals(game, capturedGame);
	}
	
	private GameBuilder captureGame(ArgumentCaptor<String> stringList) {
		String linea = "";
		ArrayList<String> rows = new ArrayList<>();
		for(String s : stringList.getAllValues()) {
			linea += s;
			if(linea.length() == 9) {
				rows.add(linea);					
				linea = "";
			}
		}
		String[] nonNumericalRows = new String[8];
		for(int i = 1; i < rows.size() - 1; i++) {
			nonNumericalRows[i-1] = rows.get(i).replaceAll("[0-9]+", "");
		}
		
		return new GameBuilder(nonNumericalRows);
	}
}
