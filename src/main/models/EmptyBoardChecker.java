package main.models;

public class EmptyBoardChecker extends AbstractCheckerElement {

	public EmptyBoardChecker(Board board, Turn turn) {
		super(board, turn);
		this.setNext(new TurnChecker(board, turn));
	}

	public Error check(int pair, Coordinate[] coordinates) {
		if (this.board.isEmpty(coordinates[pair])) {
			return Error.EMPTY_ORIGIN;
		} else {
			return this.next.check(pair, coordinates);
		}
	}
}
