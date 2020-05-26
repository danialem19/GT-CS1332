import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
   A frame that allows users to edit a bar chart.
*/
public class ChartFrame extends JFrame
{
   private static final int FRAME_WIDTH = 400;
   private static final int FRAME_HEIGHT = 400;
   
   private static final int CHART_WIDTH = 300;
   private static final int CHART_HEIGHT = 300;

   private static final String DEFAULT_LABEL = "Description";
   private static final double DEFAULT_VALUE = 100;   

   private JTextField labelField;
   private JTextField valueField;
   private JButton addButton;
   private JButton removeButton;
   private ChartComponent chart;
 
   class ChartMouseListener implements MouseListener
   {
      public void mousePressed(MouseEvent event)
      {
         int x = event.getX();
         int y = event.getY();
         chart.click(x, y);
         chart.requestFocus();
      }
      // Do-nothing methods
      public void mouseReleased(MouseEvent event) {}
      public void mouseClicked(MouseEvent event) {}
      public void mouseEntered(MouseEvent event) {}
      public void mouseExited(MouseEvent event) {}
   }

   class ChartKeyListener implements KeyListener
   {
      public void keyPressed(KeyEvent event)
      {
         String key = KeyStroke.getKeyStrokeForEvent(event).toString();
         key = key.replace("pressed ", "");
         if (key.equals("RIGHT")) { chart.growSelected(1); }
         else if (key.equals("LEFT")) { chart.growSelected(-1); }
         else if (key.equals("DOWN")) { chart.changeSelected(1); }
         else if (key.equals("UP")) { chart.changeSelected(-1); }
      }
      // Do-nothing methods
      public void keyReleased(KeyEvent event) {}
      public void keyTyped(KeyEvent event) {}
   }

   public ChartFrame()
   {  
      chart = new ChartComponent();
      chart.setPreferredSize(new Dimension(CHART_WIDTH, CHART_HEIGHT));
      chart.append(DEFAULT_LABEL, DEFAULT_VALUE);
      chart.addMouseListener(new ChartMouseListener());
      chart.addKeyListener(new ChartKeyListener());
      chart.setFocusable(true);
            
      createTextFields();
      createButtons();
      createPanel();

      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   private void createTextFields()
   {
      final int LABEL_FIELD_WIDTH = 20;
      labelField = new JTextField(LABEL_FIELD_WIDTH);
      labelField.setText(DEFAULT_LABEL);
      final int VALUE_FIELD_WIDTH = 10;
      valueField = new JTextField(VALUE_FIELD_WIDTH);
      valueField.setText("" + DEFAULT_VALUE);
   }
   
   class AddBarListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         String label = labelField.getText();
         double value = Double.parseDouble(valueField.getText());
         chart.append(label, value);
      }            
   }

   class RemoveBarListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         chart.removeSelected();
      }            
   }

   private void createButtons()
   {
      addButton = new JButton("Add");            
      addButton.addActionListener(new AddBarListener());
      removeButton = new JButton("Remove last");
      removeButton.addActionListener(new RemoveBarListener());
   }

   private void createPanel()
   {
      JPanel panel = new JPanel();
      panel.add(labelField);
      panel.add(valueField);
      panel.add(addButton);
      panel.add(removeButton);
      panel.add(chart);      
      add(panel);
   }
}
