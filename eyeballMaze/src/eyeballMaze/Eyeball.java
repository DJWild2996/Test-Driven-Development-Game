package eyeballMaze;

public class Eyeball {
	int row; 
	int column;
	Direction direction;
	
	public Eyeball(int row, int column, Direction direction) {
		this.row = row;
		this.column = column;
		this.direction = direction;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Direction getDirection() {
		return direction;
	}
	
	

}
