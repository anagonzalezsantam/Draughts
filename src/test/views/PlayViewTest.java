package test.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;

import main.controllers.PlayController;
import main.models.Color;
import main.models.Coordinate;
import main.models.Game;
import main.models.State;
import main.utils.Console;
import main.views.PlayView;
import test.models.GameBuilder;

public class PlayViewTest {

	@Mock 
	private PlayController playController;
	@Mock 
	private Console console;
	@InjectMocks
	private PlayView playView;
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCorrectMovement() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(console.readString("Mueven las blancas: ")).thenReturn("61.52");
		playView.interact(playController);
		verify(playController).move(new Coordinate(5,0), new Coordinate(4,1));		
	}
	
	@Test
	public void testDoubleCorrectMovement() {
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString("Mueven las negras: ")).thenReturn("31.42.53");
		playView.interact(playController);
		verify(playController).move(new Coordinate(2,0), new Coordinate(3,1), new Coordinate(4,2));		
	}
	
	@Test
	public void testCancel() {
		when(playController.getColor()).thenReturn(Color.WHITE, Color.BLACK);
		when(console.readString(anyString())).thenReturn("-1");
		playView.interact(playController);
		playView.interact(playController);
		verify(playController, times(2)).cancel();
	}
	
	@Test
	public void testBadFormatIncorrectLength() {
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString("Mueven las negras: ")).thenReturn("3.42").thenReturn("31.42");
		playView.interact(playController);
		verify(console).writeln("Error!!! Formato incorrecto");	
		verify(playController).move(new Coordinate(2,0), new Coordinate(3,1));	
	}
	
	@Test
	public void testBadFormatIncorrectNumber() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(console.readString("Mueven las blancas: ")).thenReturn("69.52").thenReturn("61.52");
		playView.interact(playController);
		verify(console).writeln("Error!!! Formato incorrecto");	
		verify(playController).move(new Coordinate(5,0), new Coordinate(4,1));	
	}
	
	@Test
	public void testBadFormatWrongCharacters() {
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString("Mueven las negras: ")).thenReturn("31,42").thenReturn("@3.#3").thenReturn("aa.bb").thenReturn("31.42");
		playView.interact(playController);
		verify(console, times(3)).writeln("Error!!! Formato incorrecto");	
		verify(playController).move(new Coordinate(2,0), new Coordinate(3,1));	
	}
	
	@Test
	public void testCorrectMovementThenLostMessage() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(playController.isBlocked()).thenReturn(true);
		when(console.readString("Mueven las blancas: ")).thenReturn("54.43");
		playView.interact(playController);
		verify(playController).move(new Coordinate(4,3), new Coordinate(3,2));
		verify(console).writeln("Derrota!!! No puedes mover tus fichas!!!");
	}
	
}
