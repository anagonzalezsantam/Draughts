package test.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.models.Color;
import main.models.Coordinate;
import main.models.Draught;
import main.models.Error;
import main.models.Pawn;
import main.models.Piece;

public class PieceTest {

	private Piece whitePiece;
	private Piece blackPiece;
	private Piece whiteDraught;
	private Piece blackDraught;
	
	public PieceTest() {
		whitePiece = new Pawn(Color.WHITE);
		blackPiece = new Pawn(Color.BLACK);
		whiteDraught = new Draught(Color.WHITE);
		blackDraught = new Draught(Color.BLACK);
	}
	
	@Test
	public void testIsCorrectMovement() {
		assertEquals(Error.NOT_DIAGONAL, whitePiece.isCorrectMovement(null, 0, new Coordinate(1,1), new Coordinate(2,3)));
		ArrayList<Piece> lista_con_blancas = new ArrayList<>();
		lista_con_blancas.add(new Pawn(Color.WHITE));
		assertEquals(Error.COLLEAGUE_EATING, whitePiece.isCorrectMovement(lista_con_blancas, 0, new Coordinate(1,1), new Coordinate(3,3)));
	}
	
	@Test
	public void testIsCorrectDiagonalMovementPawn() {	
		assertEquals(Error.NOT_ADVANCED, whitePiece.isCorrectDiagonalMovement(1, 0, new Coordinate(1,1), new Coordinate(2,2)));
		assertEquals(Error.NOT_ADVANCED, blackPiece.isCorrectDiagonalMovement(1, 0, new Coordinate(2,2), new Coordinate(1,1)));
		assertEquals(Error.TOO_MUCH_ADVANCED, whitePiece.isCorrectDiagonalMovement(1, 0, new Coordinate(6,6), new Coordinate(2,2)));
		assertEquals(Error.TOO_MUCH_ADVANCED, blackPiece.isCorrectDiagonalMovement(1, 0, new Coordinate(2,2), new Coordinate(6,6)));
		assertEquals(Error.WITHOUT_EATING, whitePiece.isCorrectDiagonalMovement(2, 0, new Coordinate(3,3), new Coordinate(1,1)));
		assertEquals(Error.WITHOUT_EATING, blackPiece.isCorrectDiagonalMovement(2, 0, new Coordinate(1,1), new Coordinate(3,3)));
		assertEquals(null, whitePiece.isCorrectDiagonalMovement(1, 0, new Coordinate(3,3), new Coordinate(1,1)));
		assertEquals(null, blackPiece.isCorrectDiagonalMovement(1, 0, new Coordinate(1,1), new Coordinate(3,3)));
	}
	
	@Test
	public void testIsCorrectDiagonalMovementDraught() {
		assertEquals(Error.TOO_MUCH_EATINGS, whiteDraught.isCorrectDiagonalMovement(2, 0, new Coordinate(1,1), new Coordinate(2,3)));
		assertEquals(Error.TOO_MUCH_EATINGS, blackDraught.isCorrectDiagonalMovement(2, 0, new Coordinate(1,1), new Coordinate(2,3)));
		assertEquals(null, whiteDraught.isCorrectDiagonalMovement(1, 0, new Coordinate(1,1), new Coordinate(2,3)));
		assertEquals(null, blackDraught.isCorrectDiagonalMovement(1, 0, new Coordinate(1,1), new Coordinate(2,3)));
		
	}
	
	@Test
	public void testIsLimit() {
		assertTrue(whitePiece.isLimit(new Coordinate(0,1)));
		assertTrue(blackPiece.isLimit(new Coordinate(7,1)));
		assertFalse(blackPiece.isLimit(new Coordinate(0,1)));
		assertFalse(whitePiece.isLimit(new Coordinate(7,1)));
		assertFalse(whitePiece.isLimit(new Coordinate(3,3)));
		assertFalse(blackPiece.isLimit(new Coordinate(3,3)));
	}
	
	@Test
	public void testIsAdvanced() {
		assertTrue(whitePiece.isAdvanced(new Coordinate(5,2), new Coordinate(4,1)));
		assertTrue(blackPiece.isAdvanced(new Coordinate(4,1), new Coordinate(5,2)));
		assertFalse(whitePiece.isAdvanced(new Coordinate(1,1), new Coordinate(2,2)));
		assertFalse(whitePiece.isAdvanced(new Coordinate(5,2), new Coordinate(5,1)));
		assertFalse(blackPiece.isAdvanced(new Coordinate(2,2), new Coordinate(1,1)));
		assertFalse(blackPiece.isAdvanced(new Coordinate(3,3), new Coordinate(3,4)));
	}
	
}
