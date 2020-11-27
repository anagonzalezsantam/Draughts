package main.models;

public abstract class AbstractCheckerElement {

	protected AbstractCheckerElement next;
	protected Board board;
	protected Turn turn;
	
	public AbstractCheckerElement(Board board, Turn turn) {
		this.board = board;
		this.turn = turn;
	}
	
	protected void setNext(AbstractCheckerElement abstractCheckerElement) {
		this.next = abstractCheckerElement;
	}
	
	public abstract Error check(int pair, Coordinate[] coordinates);
}
