import javax.swing.JFrame;

/**
   This program displays the babynames frame.
*/
public class BabyNamesViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new BabyNamesFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
