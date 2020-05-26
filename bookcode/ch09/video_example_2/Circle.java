import java.awt.Graphics;

public class Circle extends Shape
{
   private int centerX;
   private int centerY;
   private int radius;

   public Circle(int centerX, int centerY, int radius)
   {
      this.centerX = centerX;
      this.centerY = centerY;
      this.radius = radius;
   }

   /**
      Moves this shape by the given amount.
      @param dx the amount to move by in x-direction
      @param dy the amount to move by in y-direction
   */
   public void move(int dx, int dy) 
   {
      centerX += dx;
      centerY += dy;
   }
      
   public int getCenterX()
   {
      return centerX;
   }

   public int getCenterY()
   {
      return centerY;
   }

   /**
      Draws this shape
      g the Graphics object for the window in which the shape is drawn
   */
   public void draw(Graphics g) 
   {
      g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
   }
}
