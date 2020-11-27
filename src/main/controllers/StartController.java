package main.controllers;

import main.models.Game;
import main.models.State;
import main.views.GameView;
import main.views.View;

public class StartController extends InteractorController {

	private View view;
	
	public StartController(Game game, State state) {
        super(game, state);
        view = new View();
	}
   
	public void start() {
		this.state.next();
	}
    
    public void control() {
    	view.startViewWrite();
    	new GameView().write(this);
    	start();
    }

}
