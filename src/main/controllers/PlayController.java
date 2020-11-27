package main.controllers;

import main.models.Color;
import main.models.Coordinate;
import main.models.Game;
import main.models.State;
import main.views.GameView;
import main.views.PlayView;
import main.models.Error;

public class PlayController extends InteractorController {

	private CancelController cancelController;
	private MoveController moveController;
	private PlayView playView;

	public PlayController(Game game, State state) {
		super(game, state);
		this.cancelController = new CancelController(game, state);
		this.moveController = new MoveController(game, state);
		this.playView = new PlayView();
	}

	public Error move(Coordinate... coordinates) {
		return this.moveController.move(coordinates);
	}

	public void cancel() {
		this.cancelController.cancel();
	}

	public Color getColor() {
		return this.game.getTurnColor();
	}

	public boolean isBlocked() {
		return this.game.isBlocked();
	}

	public void control() {
		Error error;
        do {
            error = null;
            playView.read(this.getColor());
            if (playView.isCanceledFormat())
                this.cancel();
            else if (!playView.isMoveFormat()) {
                error = Error.BAD_FORMAT;
                playView.writeError();
            } else {
                error = this.move(playView.getCoordinates());
                new GameView().write(this);
                if (error == null && this.isBlocked())
                	playView.writeLost();
            }
        } while (error != null);
	}
}