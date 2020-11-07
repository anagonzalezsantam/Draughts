package main.controllers;

import main.models.Coordinate;
import main.models.Game;
import main.models.State;
import main.models.Error;

class MoveController extends Controller {

	private static final int MINIMUM_COORDINATES = 2;

    protected MoveController(Game game, State state) {
        super(game, state);
    }

    public Error move(Coordinate... coordinates) {
        assert coordinates.length >= MoveController.MINIMUM_COORDINATES;
		for(Coordinate coordinate: coordinates)
			assert coordinate != null;
		Error error = this.game.move(coordinates);
		if (this.game.isBlocked())
			this.state.next();
		return error;
	}

}