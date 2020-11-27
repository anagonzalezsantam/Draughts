package main.controllers;

import main.models.Coordinate;
import main.models.Game;
import main.models.Piece;
import main.models.State;

public abstract class InteractorController extends Controller {

	protected InteractorController(Game game, State state) {
		super(game, state);
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}

	public abstract void control();

}
