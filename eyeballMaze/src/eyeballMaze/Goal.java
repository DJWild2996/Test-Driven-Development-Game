package eyeballMaze;

public class Goal {
	int row; 
	int column;
	boolean completed = false;
	
public Goal(int row, int column) {
		  this.row = row;
		  this.column = column;
	  }
	 
public int getRow() {
			
			return this.row;
		}

public int getColumn() {
	
			return this.column;
}

}
