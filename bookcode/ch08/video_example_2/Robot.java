public class Robot
{
   private int row;
   private int column;
   private int direction;
   private Maze maze;
  
   private static final int NORTH = 0;
   private static final int EAST = 1;
   private static final int SOUTH = 2;
   private static final int WEST = 3;
   private static final int DIRECTIONS = 4;

   public Robot(Maze aMaze, int startingRow, int startingColumn)
   {
      maze = aMaze;
      row = startingRow;
      column = startingColumn;
      direction = NORTH;
   }

   private static int nextRow(int aRow, int aDirection)
   {
      if (aDirection == NORTH) { return aRow - 1; }
      else if (aDirection == SOUTH) { return aRow + 1; }
      else { return aRow; }
   }

   private static int nextColumn(int aColumn, int aDirection)
   {
      if (aDirection == WEST) { return aColumn - 1; }
      else if (aDirection == EAST) { return aColumn + 1; }
      else { return aColumn; }
   }
   
   public void move()
   {
      if (direction == NORTH) { row--; }
      else if (direction == SOUTH) { row++; }
      if (direction == WEST) { column--; }
      else if (direction == EAST) { column++; }
   }

   public void turnRight()
   {
      direction = (direction + 1) % DIRECTIONS;
   }

   public void turnLeft()
   {
      turnRight();
      turnRight();
      turnRight();
   }

   public boolean seesWall()
   {
      int rowAhead = row;
      int columnAhead = column;
      if (direction == NORTH) { rowAhead--; }
      else if (direction == SOUTH) { rowAhead++; }
      if (direction == WEST) { columnAhead--; }
      else if (direction == EAST) { columnAhead++; }
      return maze.isWall(rowAhead, columnAhead);
   }

   public boolean seesExit()
   {
      return maze.isExit(row, column);
   }

   public String toString()
   {
      return "Robot[row=" + row + ",column=" + column 
         + ",direction=" + "NESW".charAt(direction) + "]";
   }   
}
