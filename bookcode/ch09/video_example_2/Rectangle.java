import java.awt.Graphics;

public class Rectangle extends Shape
{
   private int xleft;
   private int ytop;
   private int width;
   private int height;

   public Rectangle(int x, int y, int w, int h)
   {
      xleft = x;
      ytop = y;
      width = w;
      height = h;
   }

   /**
      Moves this shape by the given amount.
      @param dx the amount to move by in x-direction
      @param dy the amount to move by in y-direction
   */
   public void move(int dx, int dy) 
   {
      xleft += dx;
      ytop += dy;
   }
 
   public int getCenterX()
   {
      return xleft + width / 2;
   }
   
   public int getCenterY()
   {
      return ytop + height / 2;
   }
   
   /**
      Draws this shape
      g the Graphics object for the window in which the shape is drawn
   */
   public void draw(Graphics g) 
   {
      g.drawRect(xleft, ytop, width, height);
   }
}
