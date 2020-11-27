package main.models;

public class TurnChecker extends AbstractCheckerElement {
	
	public TurnChecker(Board board, Turn turn) {
		super(board, turn);
		this.setNext(new EmptyTargetChecker(board, turn));
	}

	public Error check(int pair, Coordinate[] coordinates) {
		if (this.turn.getOppositeColor() == this.board.getColor(coordinates[pair])) {
			return Error.OPPOSITE_PIECE;
		} else {
			return this.next.check(pair, coordinates);
		}
	}
}
