import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
   This program demonstrates how to install an action listener
   as a local inner class.
*/
public class ButtonViewer
{  
   private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 100;

   public static void main(String[] args)
   {  
      JFrame frame = new JFrame();

      JButton button = new JButton("Click me!");
      final JLabel label = new JLabel("Hello, World!");

      JPanel panel = new JPanel();
      panel.add(button);
      panel.add(label);
      frame.add(panel);

      /**
         An action listener that changes the label text.
      */
      class ClickListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            label.setText("I was clicked.");
         }            
      }      

      ActionListener listener = new ClickListener();
      button.addActionListener(listener);
   
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
