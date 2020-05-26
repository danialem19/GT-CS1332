import java.awt.Color;
import java.awt.Graphics;

/**
   A bar of a bar chart.
*/
public class Bar
{
   private String label;
   private double value;

   public static final int HEIGHT = 15;

   /**
      Constructs a bar with a given label and value.
      @param aLabel the label for this bar
      @param aValue the value of this bar
   */
   public Bar(String aLabel, double aValue)
   {
      label = aLabel;
      value = aValue;
   }

   public void setValue(double newValue)
   {
      value = newValue;
   }

   public double getValue()
   {
      return value;
   }

   /**
      Draws this bar.
      @param g the Graphics object
      @param y the top of the bar
      @param scale the horizontal scale factor for fitting the bar into the component
      @param selected true if this bar is selected
   */
   public void draw(Graphics g, int y, double scale, boolean selected)
   {
      final int GAP = 2;
      if (selected) 
      {
         g.setColor(Color.RED);
      }
      g.fillRect(0, y, (int) (value * scale), HEIGHT);
      g.setColor(Color.WHITE);
      g.drawString(label, GAP, y + HEIGHT - GAP);
      g.setColor(Color.BLACK);
   }
}
