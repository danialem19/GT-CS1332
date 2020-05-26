import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class CrossWordFrame extends JFrame
{
   private ArrayList<JTextField> letters;
   private ArrayList<String> words;
   private JPanel letterPanel;
   private JPanel mainPanel;
   private JButton moreButton;
   private JButton fewerButton;
   private JButton searchButton;
   private JTextArea wordArea;
   private static final int INITIAL_LETTERS = 5;
   private static final int FRAME_WIDTH = 400;
   private static final int FRAME_HEIGHT = 250;

   public CrossWordFrame()
   {
      mainPanel = new JPanel();
      createLetters();
      createButtons();
      wordArea = new JTextArea(20, 20);
      mainPanel.add(new JScrollPane(wordArea));
      add(mainPanel);
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
      readWords();
   }

   class MoreButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         JTextField letter = new JTextField(1);
         letters.add(letter);
         letterPanel.add(letter);         
         revalidate();
      }
   }

   class FewerButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         if (letters.size() > 0)
         {
            JTextField letter = letters.remove(letters.size() - 1);
            letterPanel.remove(letter);         
            revalidate();
         }
      }
   }

   class SearchButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         String pattern = "";
         for (JTextField letter : letters)
         {
            String text = letter.getText();
            if (text.length() == 0) { pattern = pattern + "?"; }
            else { pattern = pattern + text.substring(0, 1); }
         }
         wordArea.setText("");
         for (String word : words)
         {
            if (matches(pattern, word))
            {
               wordArea.append(word + "\n");
            }
         }
      }

      private boolean matches(String pattern, String word)
      {
         if (pattern.length() != word.length()) { return false; }
         for (int i = 0; i < pattern.length(); i++)
         {
            char p = pattern.charAt(i);
            char w = word.charAt(i);
            if (p != w && p != '?') { return false; }
         }
         return true;
      }
   }

   private void createLetters()
   {
      letters = new ArrayList<JTextField>();
      letterPanel = new JPanel();
      for (int i = 1; i <= INITIAL_LETTERS; i++)
      {
         JTextField letter = new JTextField(1);
         letters.add(letter);
         letterPanel.add(letter);
      }
      mainPanel.add(letterPanel);
   }

   private void createButtons()
   {
      moreButton = new JButton("+");
      ActionListener listener = new MoreButtonListener();
      moreButton.addActionListener(listener);
      mainPanel.add(moreButton);
      fewerButton = new JButton("-");
      listener = new FewerButtonListener();
      fewerButton.addActionListener(listener);
      mainPanel.add(fewerButton);
      searchButton = new JButton("?");
      listener = new SearchButtonListener();
      searchButton.addActionListener(listener);
      mainPanel.add(searchButton);
   }

   private void readWords()
   {
      try
      {
         words = new ArrayList<String>();
         Scanner in = new Scanner(new File("words"));
         while (in.hasNext()) { words.add(in.next()); }
         in.close();
      }
      catch (FileNotFoundException ex)
      {
         JOptionPane.showMessageDialog(null, "words file not found");
      }     
   }
}
