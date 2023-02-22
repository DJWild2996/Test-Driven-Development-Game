package eyeballMaze;

public final class PlayableSquare extends Square {

	public PlayableSquare(Color color, Shape shape) {
		super(shape, color);
	}
	
	public Shape getShape() {
		return shape;
	}

	public Color getColor() {
		return color;
	}

}
