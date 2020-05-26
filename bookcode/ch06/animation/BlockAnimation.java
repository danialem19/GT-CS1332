import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.Timer;

/*
   A drawing of a moving block.
*/
public class BlockAnimation
{  
   public static void draw(Graphics g, int frame)
   {  
      final int BLOCK_WIDTH = 20;
      final int BLOCK_HEIGHT = 20;
      g.fillRect(frame, 0, BLOCK_WIDTH, BLOCK_HEIGHT);
   }

   public static void main(String[] args)
   {
      // Do not look at the code in the main method
      // Your code will go into the draw method above
       
      JFrame frame = new JFrame();

      final int FRAME_WIDTH = 400;
      final int FRAME_HEIGHT = 400;

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      final JComponent component = new JComponent()
      {
         private int frame = 0;

         public void paintComponent(Graphics graph)
         {
            frame++;
            draw(graph, frame);
         }
      };

      ActionListener timerListener = new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            component.repaint();
         }
      };

      final int DELAY = 25; // 25 milliseconds between frames
      Timer t = new Timer(DELAY, timerListener);
      t.start();
      
      frame.add(component);
      frame.setVisible(true);
   }   
}
