import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;

/**
   This program draws a spiral.
*/
public class SpiralViewer
{
   public static void main(String[] args)
   {
      // Do not look at the code in the main method
      // Your code will go into the draw method below
       
      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 400;
      final int FRAME_HEIGHT = 400;

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JComponent component = new JComponent()
      {
          public void paintComponent(Graphics graph)
          {
              draw(graph);
          }
      };
      
      frame.add(component);

      frame.setVisible(true);
   }
   
   public static void draw(Graphics graph)
   {
       // Your code goes here. To draw a line from (x1, y1) to (x2, y2), call
       // graph.drawLine(x1, y1, x2, y2);
       
       int x1 = 100;
       int y1 = 100;
       int length = 10;
       
       for (int i = 1; i <= 10; i++)
       {
           // Right
           int x2 = x1 + length;
           int y2 = y1;
           graph.drawLine(x1, y1, x2, y2);           
           x1 = x2;
           y1 = y2;

           // Up
           y2 = y1 - length;           
           graph.drawLine(x1, y1, x2, y2);
           x1 = x2;
           y1 = y2;

           length = length + 10;

           // Left
           x2 = x1 - length;
           graph.drawLine(x1, y1, x2, y2);
           x1 = x2;
           y1 = y2;

           // Down
           y2 = y1 + length;
           graph.drawLine(x1, y1, x2, y2);
           x1 = x2;
           y1 = y2;
           
           length = length + 10;
       }
   }
}
