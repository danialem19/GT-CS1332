import java.awt.Graphics;

public class Line extends Shape
{
   private int x1;
   private int y1;
   private int x2;
   private int y2;

   public Line(int xfrom, int yfrom, int xto, int yto)
   {
      x1 = xfrom;
      y1 = yfrom;
      x2 = xto;
      y2 = yto;
   }

   /**
      Moves this shape by the given amount.
      @param dx the amount to move by in x-direction
      @param dy the amount to move by in y-direction
   */
   public void move(int dx, int dy) 
   {
      x1 = x1 + dx;
      x2 = x2 + dx;
      y1 = y1 + dy;
      y2 = y2 + dy;
   }
   
   public int getCenterX()
   {
      return (x1 + x2) / 2;
   }

   public int getCenterY()
   {
      return (y1 + y2) / 2;
   }

   /**
      Draws this shape
      g the Graphics object for the window in which the shape is drawn
   */
   public void draw(Graphics g) 
   {
      g.drawLine(x1, y1, x2, y2);
   }
}
