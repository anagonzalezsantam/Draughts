package main.models;

public class EmptyTargetChecker extends AbstractCheckerElement {

	public EmptyTargetChecker(Board board, Turn turn) {
		super(board, turn);
	}

	public Error check(int pair, Coordinate[] coordinates) {
		System.out.println(board.toString());
		if (!this.board.isEmpty(coordinates[pair + 1])) {
			return Error.NOT_EMPTY_TARGET; 
		} else {
			return null;
		}
	}
}
