import javax.swing.JFrame;

/**
   This program displays an editable bar chart.
*/
public class ChartViewer
{  
   public static void main(String[] args)
   {  
      JFrame frame = new ChartFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
