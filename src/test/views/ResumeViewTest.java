package test.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import main.views.ResumeView;
import main.controllers.ResumeController;
import main.models.Game;
import main.models.State;
import main.models.StateValue;
import main.utils.YesNoDialog;
import test.models.GameBuilder;

public class ResumeViewTest {

	private State state;
	@Mock
	private ResumeController resumeController;	
	@Mock
	private YesNoDialog yesNoDialog;
	@InjectMocks
	private ResumeView resumeView;

	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
		Game game = new GameBuilder().build();
		this.state = new State();
		this.state.next(); // StateValue IN_GAME
		this.state.next(); // StateValue FINISHED
		this.resumeController = spy(new ResumeController(game, state));
	}
	
	@Test
	public void testResumeYes() {
		when(this.yesNoDialog.read(anyString())).thenReturn(true);
		this.resumeView.interact(resumeController);
		verify(this.resumeController).reset();
		assertEquals(StateValue.INITIAL, this.state.getValueState());
	}
	
	@Test
	public void testResumeNo() {
		when(this.yesNoDialog.read(anyString())).thenReturn(false);
		this.resumeView.interact(resumeController);
		verify(this.resumeController).next();
		assertEquals(StateValue.EXIT, this.state.getValueState());
	}
	
}
