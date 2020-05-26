import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Shapes
{   
   /** Generates random shapes
    * @param shapes the array to fill with shapes
    * @param xmax the maximum x-position
    * @param ymax the maximum y-position
    */ 
   public static void fillWithRandom(Shape[] shapes, int xmax, int ymax)
   {
      for (int i = 0; i < shapes.length; i++)
      {
         int type = (int) (Math.random() * 3);
         int x = (int) (Math.random() * xmax);
         int y = (int) (Math.random() * ymax);
         if (type == 0)
         {
            shapes[i] = new Circle(x, y, 10);
         }
         else if (type == 1)
         {
            shapes[i] = new Rectangle(x, y, 30, 20);
         }
         else
         {
            shapes[i] = new Line(x, y, x + 10, y + 20);
         }
      }
   }
   
   /**
    * Moves each shape by a small amount.
    * @param shapes the shapes
    */ 
   public static void moveBySmallAmount(Shape[] shapes)
   {
      final int X_OFFSET = 4;
      final int Y_OFFSET = 2;
      for (Shape s : shapes)
      {
         s.move(X_OFFSET, Y_OFFSET);
      }
   }
   
   /**
    * Lines up shapes in rows.
    * @param shapes the shapes
    * @param margin the right margin at which a new row should start
    */ 
   public static void lineUp(Shape[] shapes, int margin)
   {
      final int DISTANCE = 40;
      int x = DISTANCE / 2;
      int y = DISTANCE / 2;
      for (Shape s : shapes)
      {
         int dx = x - s.getCenterX();
         int dy = y - s.getCenterY();
         s.move(dx, dy);
         x = x + DISTANCE;
         if (x >= margin)
         {
            x = DISTANCE / 2;
            y = y + DISTANCE;
         }
      }
      
   }
   
   public static void main(String[] args)
   {
      final int XSIZE = 400;
      final int YSIZE = 400;
      final int NSHAPES = 30;
      final Shape[] shapes = new Shape[NSHAPES];
      fillWithRandom(shapes, XSIZE, YSIZE);
      
      final JFrame frame = new JFrame();
      frame.add(new JComponent()
      {
         public void paintComponent(Graphics g)
         {
            for (Shape s : shapes)
            {
               s.draw(g);
            }
         }
      });
      
      JPanel panel = new JPanel();
      frame.add(panel, "North");
      JButton button1 = new JButton("Randomize");
      panel.add(button1);
      button1.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            fillWithRandom(shapes, XSIZE, YSIZE);
            frame.repaint();
         }
      });

      JButton button2 = new JButton("Move");
      panel.add(button2);
      button2.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {            
            moveBySmallAmount(shapes);
            frame.repaint();
         }
      });
      
      JButton button3 = new JButton("Line up");
      panel.add(button3);
      button3.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            lineUp(shapes, XSIZE);
            frame.repaint();
         }
      });
      

      frame.setSize(XSIZE, YSIZE);
      frame.setVisible(true);      
   }
}