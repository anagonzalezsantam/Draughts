package main.views;

import java.util.ArrayList;
import java.util.List;

import main.controllers.InteractorController;
import main.models.Coordinate;
import main.models.Piece;

public class GameView extends SubView {

    public void write(InteractorController controller) {
        assert controller != null;
        final int DIMENSION = controller.getDimension();
        this.writeNumbersLine(DIMENSION);
        for (int i = 0; i < DIMENSION; i++) {
            List<Piece> lista = this.getPiecesRow(i, controller);
            this.writePiecesRow(i, lista);
        }
        this.writeNumbersLine(DIMENSION);
    }

    private List<Piece> getPiecesRow(int row, InteractorController controller) {
    	List<Piece> fila_piezas = new ArrayList<>();
    	for (int j = 0; j < controller.getDimension(); j++) {
            Piece piece = controller.getPiece(new Coordinate(row, j));
            fila_piezas.add(piece);
        }
    	return fila_piezas;
    }
    
    private void writeNumbersLine(final int DIMENSION) {
        this.console.write(" ");
        for (int i = 0; i < DIMENSION; i++)
            this.console.write((i + 1) + "");
        this.console.writeln();
    }

    private void writePiecesRow(int row, List<Piece> lista_piezas) {
        this.console.write((row + 1) + "");
        for (int j = 0; j < lista_piezas.size(); j++) {
            Piece piece = lista_piezas.get(j);
            if (piece == null)
                this.console.write(" ");
            else 
                this.console.write(piece.getCode());
        }
        this.console.writeln((row + 1) + "");
    }

}
