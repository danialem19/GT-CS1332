import javax.swing.JFrame;

/**
   This program displays the crossword frame.
*/
public class CrossWordViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new CrossWordFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
