import java.util.ArrayList;

/**
   A maze has its walls marked by * characters and corridors 
   by spaces. The maze is assumed not to have
   any cycles (i.e., corridors returning to their own start.)
*/
public class Maze
{
   private String[] cells;

   /**
      Constructs a maze from a string describing its contents.
      @param contents strings consisting of * and spaces
   */
   public Maze(String[] contents)
   {
      cells = contents;
   }

   /**
      Checks whether a position is an exit.
      @param row the row of the position
      @param column the column of the position
      @return true if the position is an exit
   */
   public boolean isExit(int row, int column)
   {
      return (row == 0 || row == cells.length - 1 
         || column == 0 || column == cells[row].length() - 1)
         && cells[row].charAt(column) == ' ';
   }

   /**
      Checks whether a position is within the maze and not a wall.
      @param row the row of the position
      @param column the column of the position
      @return true if the position is valid
   */
   public boolean isWall(int row, int column)
   {
      return cells[row].charAt(column) == '*';
   }
}
