package test.models;

import java.util.ArrayList;
import java.util.regex.Pattern;

import main.models.Game;
import main.models.Pawn;
import main.models.Piece;
import main.models.Board;
import main.models.Color;
import main.models.Coordinate;
import main.models.Draught;

public class GameBuilder {

	private ArrayList<String> rows;
	private Color color;
	
	public GameBuilder() {
		this.rows = new ArrayList<String>();
		color = Color.WHITE;
	}
	
	public GameBuilder(String... strings) {
		this.rows = new ArrayList<String>();
		color = Color.WHITE;
		for (String string : strings) {
            if(Pattern.matches("[bB nN]{8}", string)){
            	this.rows.add(string);            	
            }
        }
	}
	public Game build() {
		Board board = new Board();
		this.completeBoard(board);
		Game game = new Game(board);
		if(color == Color.BLACK) {
			this.setColor(game, board);
		}
		return game;
	}

	private void completeBoard(Board board) {
		for(int i=0; i<this.rows.size(); i++) {
			String row = this.rows.get(i);
			for(int j=0; j < row.length(); j++) {
				String letter = row.substring(j, j+1);
				Color color = this.getColor(letter);
	            if (color != null) {
	                Piece piece = new Pawn(color);
	                if (letter.equals("B") || letter.equals("N")) {
	                	piece = new Draught(color);	                	
	                }
	                board.put(new Coordinate(i, j), piece);
	            }
			}
		}
	}
	
	private Color getColor(String string) {
		if(string.toLowerCase().equals("b")) {
			return Color.WHITE;
		} else if(string.toLowerCase().equals("n")) {
			return Color.BLACK;
		}
		return null;
	} 
	
	public GameBuilder color(Color color) {
		this.color = color;
		return this;
	}
	
	private void setColor(Game game, Board board) {
		board.put(new Coordinate(5, 1), new Pawn(Color.WHITE));
        game.move(new Coordinate(5, 1), new Coordinate(4, 0));
        board.remove(new Coordinate(4, 0));
	}
}
