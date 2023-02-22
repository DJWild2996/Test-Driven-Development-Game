package eyeballMaze;

public class Square {
	Square square; 
	int row; 
	int column;
	Shape shape;
	Color color;
	PlayableSquare PlayableSquare;
	BlankSquare BlankSquare;
	
	public Square(Square square, int row, int column, Shape shape, Color color) {
		super();
		this.square = square;
		this.row = row;
		this.column = column;
	}
	public Square(Shape shape, Color color) {
		super();
		this.color = color;
		this.shape = shape;
	}
	public Shape getShape() {
		return Shape.BLANK;
	}

	public Color getColor() {
		return Color.BLANK;
	}
	
	public Square getSquare() {
		return this.square;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
}
