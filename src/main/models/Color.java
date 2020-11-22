package main.models;

public enum Color {
    WHITE,
    BLACK,
    NULL_COLOR;

    private final int[] LIMITS = new int[]{5, 2};

    public boolean isInitialRow(final int row){
        switch(this){
            case WHITE:
                return row >= LIMITS[this.ordinal()];
            case BLACK:
                return row <= LIMITS[this.ordinal()];
            default:
            	return false;
        }
    }

    public static Color getInitialColor(final Coordinate coordinate) {
        if (coordinate.isBlack())
            for(Color color : Color.values())
                if (color.isInitialRow(coordinate.getRow()))
                    return color;
        return Color.NULL_COLOR;
    }
    
    public boolean isNull() {
    	return this == Color.NULL_COLOR;
    }
	
}