package main;

import java.util.HashMap;
import java.util.Map;

import main.controllers.InteractorController;
import main.controllers.PlayController;
import main.controllers.ResumeController;
import main.controllers.StartController;
import main.models.Game;
import main.models.State;
import main.models.StateValue;

public class Draughts {

	private Game game;
	private State state;
	private Map<StateValue, InteractorController> controllers;

    private Draughts(){
    	this.game = new Game();
		this.state = new State();
        this.controllers = new HashMap<StateValue, InteractorController>();
		this.controllers.put(StateValue.INITIAL, new StartController(this.game, this.state));
		this.controllers.put(StateValue.IN_GAME, new PlayController(this.game, this.state));
		this.controllers.put(StateValue.FINAL, new ResumeController(this.game, this.state));
		this.controllers.put(StateValue.EXIT, null);
    }

    private void play() {
        InteractorController controller;
		do {
			controller = this.controllers.get(state.getValueState());
			if (controller != null)
				controller.control();
		} while (controller != null); 
    }

    public static void main(String[] args){
        new Draughts().play();
    }

}
