public class GameOfLife
{
   private static GameOfLifeViewer viewer;

   /**
      Counts the live neighbors of a given location.
      @param cells the array of cells
      @param i the row index of the location
      @param j the column index of the location
      @return the number of live neighbors
   */
   public static int countNeighbors(boolean[][] cells, int i, int j)
   {
      int count = 0;
      for (int di = -1; di <= 1; di++)
      {
         for (int dj = -1; dj <= 1; dj++)
         {
            if (di != 0 || dj != 0) // Don't count the given location
            {
               int row = i + di;
               int column = j + dj;
               if (0 <= row && row < cells.length
                     // row index valid
                  && 0 <= column && column < cells[0].length
                     // column index valud
                  && cells[row][column]) // cell is live
               {
                  count++;
               }
            }
         }
      }
      return count;
   }

   public static boolean[][] nextGeneration(boolean[][] current)
   {
      int rows = current.length;
      int columns = current[0].length;

      boolean[][] next = new boolean[rows][columns];

      for (int i = 0; i < rows; i++)
      {
         for (int j = 0; j < columns; j++)
         {
            int count = countNeighbors(current, i, j);
            if (!current[i][j] && count == 3) 
               // Birth
            {
               next[i][j] = true;
            }
            else if (current[i][j] && count >= 2 && count <= 3) 
               // Survival
            {
               next[i][j] = true;
            }
         }
      }      
      return next;
   }

   /**
      Places a glider in the middle of the cells.
      @param cells the cells to be populated
   */
   public static void init(boolean[][] cells)
   {
      // Place the initial configuration in the middle

      int i = cells.length / 2;
      int j = cells[0].length / 2;

      cells[i - 1][j-1] = true;
      cells[i - 1][j] = true;
      cells[i - 1][j + 1] = true;
      cells[i][j + 1] = true;
      cells[i + 1][j] = true;      
   }

   /**
      Places an "R-pentomino" in the middle of the cells.
      @param cells the cells to be populated
   */
   public static void init2(boolean[][] cells)
   {
      // Place the initial configuration in the middle

      int i = cells.length / 2;
      int j = cells[0].length / 2;

      cells[i - 1][j] = true;
      cells[i - 1][j + 1] = true;
      cells[i][j] = true;
      cells[i][j - 1] = true;
      cells[i + 1][j] = true;      
   }

   public static void display(boolean[][] cells)
   {
      int rows = cells.length;
      int columns = cells[0].length;

      for (int i = 0; i < rows; i++)
      {
         for (int j = 0; j < columns; j++)
         {
            if (cells[i][j]) 
            {
               System.out.print("o");
            }
            else
            {
               System.out.print(".");
            }
         }
         System.out.println();
      }
      System.out.println();

      if (viewer == null)
      {
         viewer = new GameOfLifeViewer(rows, columns);
      }
      viewer.update(cells);
   }

   public static void main(String[] args)
   {
      final int ROWS = 20;
      final int COLUMNS = 40;
      final int ROUNDS = 320;
      boolean[][] cells = new boolean[ROWS][COLUMNS];

      init2(cells);
      display(cells);
      
      for (int round = 1; round <= ROUNDS; round++)
      {
         cells = nextGeneration(cells);
         display(cells);
      }      
   }
}
