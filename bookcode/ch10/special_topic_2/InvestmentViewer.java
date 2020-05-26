import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
   This program shows the growth of an investment.
*/
public class InvestmentViewer
{  
   private static final int FRAME_WIDTH = 300;
   private static final int FRAME_HEIGHT = 100;
   
   private static final double INTEREST_RATE = 5;
   private static final double INITIAL_BALANCE = 1000;   

   public static void main(String[] args)
   {  
      final BankAccount account = new BankAccount();
      account.deposit(INITIAL_BALANCE);

      JFrame frame = new JFrame();

      JButton button = new JButton("Add Interest");
      final JLabel resultLabel = new JLabel("Balance: " + INITIAL_BALANCE);
      JPanel panel = new JPanel();
      panel.add(button);
      panel.add(resultLabel);
      frame.add(panel);

      /**
         Adds interest to the balance and updates the display.
      */
      class AddInterestListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            double interest = account.getBalance() * INTEREST_RATE / 100;
            account.deposit(interest);
            resultLabel.setText("Balance: " + account.getBalance());
         }            
      }
      
      ActionListener listener = new AddInterestListener();
      button.addActionListener(listener);

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}
