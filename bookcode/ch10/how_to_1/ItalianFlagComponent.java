import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
   A component that shows two Italian flags.
*/
public class ItalianFlagComponent extends JComponent
{
   public void paintComponent(Graphics g)
   {
      drawItalianFlag(g, 10, 10, 100);
      drawItalianFlag(g, 10, 125, 150);
   }

   /**
      Draws an Italian flag at the given location and size
      @param g the graphics object
      @param xLeft the x coordinate of the top left corner
      @param yTop the y coordinate of the top left corner
      @param width the width of the flag
   */
   void drawItalianFlag(Graphics g, int xLeft, int yTop, int width)
   {
      g.setColor(Color.GREEN); 
      g.fillRect(xLeft, yTop, width / 3, width * 2 / 3);
      g.setColor(Color.RED);
      g.fillRect(xLeft + 2 * width / 3, yTop, width / 3, width * 2 / 3);
      g.setColor(Color.BLACK);
      g.drawLine(xLeft + width / 3, yTop, xLeft + width * 2 / 3, yTop);
      g.drawLine(xLeft + width / 3, yTop + width * 2 / 3, 
         xLeft + width * 2 / 3, yTop + width * 2 / 3);
   }
}

