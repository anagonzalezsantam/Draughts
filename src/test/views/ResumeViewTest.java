package test.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.views.ResumeView;
import main.controllers.ResumeController;
import main.models.Game;
import main.models.State;
import main.models.StateValue;
import test.models.GameBuilder;

public class ResumeViewTest {

	private ResumeView resumeView;
	private ResumeController resumeController;
	private State state;

	@BeforeEach
	public void before() {
		Game game = new GameBuilder().build();
		state = new State();
		state.next();
		state.next();
		resumeController = new ResumeController(game, state);
	}
	
	@Test
	public void testResumeYes() {
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream("y".getBytes()));
		this.resumeView = new ResumeView();
		this.resumeView.interact(resumeController);
		assertEquals(StateValue.INITIAL, state.getValueState());
		System.setIn(stdin);
	}
	
	@Test
	public void testResumeNo() {
		InputStream stdin = System.in;
		System.setIn(new ByteArrayInputStream("n".getBytes()));
		this.resumeView = new ResumeView();
		this.resumeView.interact(resumeController);
		assertEquals(StateValue.EXIT, state.getValueState());
		System.setIn(stdin);		
	}
	
}
