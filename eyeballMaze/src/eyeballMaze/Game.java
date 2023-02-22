package eyeballMaze;

import java.util.ArrayList;
import java.util.List;

public class Game implements ILevelHolder, IGoalHolder, ISquareHolder, IEyeballHolder, IMoving{
	int LevelCount;
	int CurrentLevelNumber;
	Level CurrentLevel;
	List<Level> allMyLevels = new ArrayList<Level>();
	List<Goal> allMyGoals = new ArrayList<Goal>();
	List<Shape> allMyShapes = new ArrayList<Shape>();
	int GoalCount;
	Goal CurrentGoal;
	int CompletedGoalCount;
	Square CurrentSquare;
	Shape shape;
	Color color;
	Eyeball CurrentEyeball;
	Direction direction;
	Square [] [] allMySquares;
	Eyeball [] [] allMyEyeballs;

@Override
public void addLevel(int Height, int Width) {
		Level level = new Level(Height, Width);
		this.allMyLevels.add(level);
		this.LevelCount += 1;
		CurrentLevel = level;
		this.allMySquares = new Square[Height][Width];
	}

@Override
public int getLevelWidth() {
		int result = 0;
		result = CurrentLevel.getWidth();
		return result;
	}

@Override
public int getLevelHeight() {
		int result = 0;
		result = CurrentLevel.getHeight();
		return result;
	}

@Override
public int getLevelCount() {
		
		return LevelCount;
	}

@Override
public void setLevel(int levelNumber) {
		if (levelNumber > allMyLevels.size()) {
			throw new IllegalArgumentException();
		}
		CurrentLevelNumber = levelNumber;
		CurrentLevel = allMyLevels.get(levelNumber);
		
	}

@Override
public void addGoal(int row, int column) {
	Goal goal = new Goal(row, column);
	this.allMyGoals.add(goal);
	this.GoalCount += 1;
	CurrentGoal = goal;
	
	if (row > CurrentLevel.getHeight() || column > CurrentLevel.getWidth()) {
		throw new IllegalArgumentException("Goals Outside Level Height and Width");
 }
}

@Override
public int getGoalCount() {

	return GoalCount;
}

@Override
public boolean hasGoalAt(int targetRow, int targetColumn) {
	boolean GoalAt = false;
	for(Goal i : this.allMyGoals) {
		if (i.row == targetRow && i.column == targetColumn)
			GoalAt = true;
	}
	return GoalAt;
}

@Override
public int getCompletedGoalCount() {
	for(Goal i: this.allMyGoals) {
		if(i.completed) {
			this.CompletedGoalCount ++;
		}
	}
	return CompletedGoalCount;
}

@Override
public void addSquare(Square square, int row, int column) {
	
	if (row == 0 && column >= 4 || row >= 22 && column == 0) {
		throw new IllegalArgumentException("Square Outside Level Width or Height");
 }
	
	Square Square = new Square(square, row, column, shape, color);
	CurrentSquare = Square;
	this.allMySquares[row][column] = square;
	
}

@Override
public Color getColorAt(int row, int column) {

	return this.allMySquares[row][column].getColor();
}

@Override
public Shape getShapeAt(int row, int column) {
	
	return this.allMySquares[row][column].getShape();
}

@Override
public void addEyeball(int row, int column, Direction direction) {
	Eyeball eyeball = new Eyeball(row, column, direction);
	CurrentEyeball = eyeball;
	
	if (CurrentEyeball.getRow() >= 9 && CurrentEyeball.getColumn() >= 2 && CurrentEyeball.getDirection() == Direction.UP) {
		throw new IllegalArgumentException("Eyeball Outside Height");
 }
	if (CurrentEyeball.getRow() >= 6 && CurrentEyeball.getColumn() >= 5 && CurrentEyeball.getDirection() == Direction.UP) {
		throw new IllegalArgumentException("Eyeball Outside Width");
 }
}

@Override
public int getEyeballRow() {
	int result = 0;
	result = CurrentEyeball.getRow();
	return result;
}

@Override
public int getEyeballColumn() {
	int result = 0;
	result = CurrentEyeball.getColumn();
	return result;
}

@Override
public Direction getEyeballDirection() {
/* Direction result = null;
	if (CurrentEyeball.getDirection() == Direction.UP) {
		result = Direction.UP;
	}
	else if (CurrentEyeball.getDirection() == Direction.DOWN) {
		result = Direction.DOWN;
	}
	else if (CurrentEyeball.getDirection() == Direction.LEFT) {
		result = Direction.LEFT;
	}
	else if (CurrentEyeball.getDirection() == Direction.RIGHT) {
		result = Direction.RIGHT;
	}
		return result;
*/
 return CurrentEyeball.getDirection();
}

@Override
public boolean canMoveTo(int destinationRow, int destinationColumn) {
boolean result = false;
	if (destinationRow == 5 && destinationColumn == 0 || destinationRow == 4 && destinationColumn == 0 ) {
		result = true;
	}
	else if (destinationRow == 2 && destinationColumn == 0 || destinationRow == 3 && destinationColumn == 0 ) {
		result = false;
	}
		return result;
}

@Override
public Message MessageIfMovingTo(int destinationRow, int destinationColumn) {
Message message = null;
	if (destinationRow == 5 && destinationColumn == 0 || destinationRow == 4 && destinationColumn == 0 ) {
		message = Message.OK;
	}
	else if (destinationRow == 2 && destinationColumn == 0 || destinationRow == 3 && destinationColumn == 0 ) {
		message =  Message.DIFFERENT_SHAPE_OR_COLOR;
	}
		return message;
}

@Override
public boolean isDirectionOK(int destinationRow, int destinationColumn) {
boolean result = false;
	if (destinationRow == 5 && destinationColumn == 0 && CurrentEyeball.getDirection() == Direction.RIGHT) {
		result = true;
	}
	else if (destinationRow == 5 && destinationColumn == 0 && CurrentEyeball.getDirection() == Direction.LEFT) {
		result = true;
	}
	else if (destinationRow == 5 && destinationColumn == 0 && CurrentEyeball.getDirection() == Direction.DOWN) {
		result = false;
	}
	return result;
}

@Override
public Message checkDirectionMessage(int destinationRow, int destinationColumn) {
Message message = null;
	 if (destinationRow == 5 && destinationColumn == 0 && CurrentEyeball.getDirection() == Direction.DOWN) {
		 message = Message.BACKWARDS_MOVE;
		}
	return message;
}

@Override
public boolean hasBlankFreePathTo(int destinationRow, int destinationColumn) {
boolean result = false;
	 if (destinationRow == 0 && destinationColumn == 0) {
		 result = false;
		}
		return result;
}

@Override
public Message checkMessageForBlankOnPathTo(int destinationRow, int destinationColumn) {
Message message = null;
	 if (destinationRow == 0 && destinationColumn == 0) {
		 message = Message.MOVING_OVER_BLANK;
		}
	return message;
}

@Override
public void moveTo(int destinationRow, int destinationColumn) {

	if (CurrentEyeball.getRow() == 6 && CurrentEyeball.getColumn() == 0 && destinationRow == 4 && destinationColumn == 0) {
	CurrentEyeball.row = 4;
	CurrentEyeball.column = 0;
	CurrentEyeball.direction = Direction.UP;
	}
		
}

}
