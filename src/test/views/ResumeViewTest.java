package test.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import main.views.View;
import main.controllers.ResumeController;
import main.models.Game;
import main.models.State;

public class ResumeViewTest {

	@Mock
	private View resumeView;
	@Mock
	private Game game;
	@Mock
	private State state;
	@InjectMocks
	private ResumeController resumeController;	

	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testResumeYes() {
		when(this.resumeView.resumeViewRead()).thenReturn(true);
		this.resumeController.control();
		verify(this.game).reset();
		verify(this.state).reset();
	}
	
	@Test
	public void testResumeNo() {
		when(this.resumeView.resumeViewRead()).thenReturn(false);
		this.resumeController.control();
		verify(this.state).next();
	}
	
}
