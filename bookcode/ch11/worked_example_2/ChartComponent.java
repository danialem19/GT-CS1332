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
   private int selected;

   private static final int GAP = 5;

   /**
      Constructs an empty chart.
   */
   public ChartComponent()
   {
      bars = new ArrayList<Bar>();
      maxValue = 1;
      selected = 0;
   }

   /**
      Appends a bar to this chart.
      @param label the label for the bar
      @param value the value of the bar
   */
   public void append(String label, double value)
   {
      bars.add(new Bar(label, value));
      selected = bars.size() - 1;
      if (value > maxValue) { maxValue = value; }
      repaint();
   }

   /**
      Process a mouse press at a given location.
      @param x the x-coordinate of the mouse press
      @param y the y-coordinate of the mouse press
   */
   public void click(int x, int y)
   {
      int row = y / (GAP + Bar.HEIGHT);
      double value = x * maxValue / getWidth();

      if (row == selected)
      {
         bars.get(row).setValue(value);
      }
      else if (row >= bars.size())
      {
         bars.add(new Bar("", value));
         selected = bars.size() - 1;
      }
      else 
      {
         selected = row;
      }
      repaint();
   }

   /**
      Grows or shrinks the selected bar.
      @param increment the amount (in pixels) by which to adjust the bar
   */
   public void growSelected(int increment)
   {
      if (bars.size() == 0) { return; }
      Bar b = bars.get(selected);
      double value = b.getValue();
      value = value + increment * maxValue / getWidth();
      b.setValue(value);
      repaint();
   }

   /**
      Change the selected bar.
      @param increment +1 or -1 to select the next or previous bar.
   */
   public void changeSelected(int increment)
   {
      if (bars.size() == 0) { return; }
      selected = selected + increment;
      // Wrap around at the ends
      int n = bars.size();
      if (selected >= n) { selected = selected - n; }
      if (selected < 0) { selected = selected + n; }
      repaint();
   }

   /**
      Removes the currently selected bar.
   */
   public void removeSelected()
   {
      if (bars.size() == 0) { return; }
      bars.remove(selected);
      if (selected == bars.size()) 
      {
         selected--;
      }
      repaint();      
   }

   public void paintComponent(Graphics g)
   {  
      int y = GAP;
      double scale = getWidth() / maxValue;
      for (int i = 0; i < bars.size(); i++)
      {
         Bar b = bars.get(i);
         b.draw(g, y, scale, i == selected);
         y = y + Bar.HEIGHT + GAP;
      }
   }
}
