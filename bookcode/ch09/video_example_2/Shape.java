import java.awt.Graphics;

public class Shape
{
   /**
      Moves this shape by the given amount.
      @param dx the amount to move by in x-direction
      @param dy the amount to move by in y-direction
   */
   public void move(int dx, int dy) {}
      
   /**
      Gets the x-position of the center of this shape.
      @return the x-position of the center 
   */
   public int getCenterX() { return 0; }
   
   /**
      Gets the y-position of the center of this shape.
      @return the y-position of the center 
   */
   public int getCenterY() { return 0; }


   /**
      Draws this shape
      g the Graphics object for the window in which the shape is drawn
   */
   public void draw(Graphics g) {}
}
