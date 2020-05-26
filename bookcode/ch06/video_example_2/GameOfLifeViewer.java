import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GameOfLifeViewer extends JFrame
{
   private boolean[][] cells;

   public GameOfLifeViewer(final int rows, final int columns)
   {
      final int CELL_SIZE = 8;      
      final Dimension preferredSize = new Dimension(
         columns * CELL_SIZE,
         rows * CELL_SIZE);

      add(new JComponent() 
         {
            public void paintComponent(Graphics g)
            {
               for (int i = 0; i < cells.length; i++)
               {
                  for (int j = 0; j < cells[0].length; j++)
                  {
                     if (cells[i][j])
                     {
                        g.fillRect(j * CELL_SIZE, i * CELL_SIZE,
                           CELL_SIZE - 1, CELL_SIZE - 1);
                     }
                  }                  
               }
            }
            
            public Dimension getPreferredSize()
            {
               return preferredSize;
            }
         }); 
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

   public void update(boolean[][] newCells)
   {
      cells = newCells;
      repaint();
      try
      {
         Thread.sleep(100);         
      }
      catch (InterruptedException ex)
      {
      }
   }
}
