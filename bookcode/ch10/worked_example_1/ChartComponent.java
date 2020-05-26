import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
   A component that draws a chart.
*/
public class ChartComponent extends JComponent
{
   private ArrayList<Bar> bars;
   private double maxValue;

   /**
      Constructs an empty chart.
   */
   public ChartComponent()
   {
      bars = new ArrayList<Bar>();
      maxValue = 1;
   }

   /**
      Appends a bar to this chart.
      @param label the label for the bar
      @param value the value of the bar
   */
   public void append(String label, double value)
   {
      bars.add(new Bar(label, value));
      if (value > maxValue) { maxValue = value; }
      repaint();
   }

   /**
      Removes the last bar of this chart.
   */
   public void removeLast()
   {
      int n = bars.size();
      if (n == 0) { return; }
      bars.remove(n - 1);
      repaint();      
   }

   public void paintComponent(Graphics g)
   {  
      final int GAP = 5;

      int y = GAP;
      double scale = getWidth() / maxValue;
      for (Bar b : bars)
      {
         b.draw(g, y, scale);
         y = y + Bar.HEIGHT + GAP;
      }
   }
}
