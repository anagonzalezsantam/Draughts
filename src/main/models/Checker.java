package main.models;

public class Checker {
	private AbstractCheckerElement chainOfCheckers;
		
	public Checker(Board board, Turn turn) {
		this.chainOfCheckers = new EmptyBoardChecker(board, turn);
	}

	public Error check(int pair, Coordinate[] coordinates) {		
		return this.chainOfCheckers.check(pair, coordinates);
	}
	
}
