package main.controllers;

import main.models.Game;
import main.models.State;
import main.views.View;

public class ResumeController extends InteractorController {

	private View view;
	
	public ResumeController(Game game, State state) {
        super(game, state);
        this.view = new View();
	}

	public void next() {
        this.state.next();
	}

	public void reset() {
		this.state.reset();
		this.game.reset();
	}

    public void control() {
        if (view.resumeViewRead())
            this.reset();
        else
            this.next();
    }

}
