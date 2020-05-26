import java.util.Scanner;

/**
   This program justifies paragraphs of text so that all lines
   have the same length.
*/
public class Justify
{
   /**
      Gets the starting position of the word following a given word.
      @param words a string containing words
      @param start the starting position of a word
      @return the starting position of the next word, or words.length()
      if there is no more word. 
   */
   public static int nextWord(String words, int start)
   {
      int i = start;
      while (i < words.length() && words.charAt(i) != ' ')
         i++;
      while (i < words.length() && words.charAt(i) == ' ')
         i++;
      return i;
   }

   /**
      Counts the words in a string
      @param words a string containing words
      @return the number of words in the string
   */
   public static int countWords(String words)
   {
      int count = 0;
      int next = 0;
      while (next < words.length())
      {
         next = nextWord(words, next);
         count++;
      }
      return count;
   }

   /**
      Formats a string by adding extra spaces so that the 
      string extends to a given margin.
      @param line a string
      @param margin the desired right margin
      @return the formatted line
   */
   public static String format(String line, int margin)
   {
      int spacesToAdd = margin - line.length();
      if (spacesToAdd <= 0) return line;
      int gaps = countWords(line) - 1;
      if (gaps <= 0) return line;
      int spacesPerGap = spacesToAdd / gaps;
      int extraSpaces = spacesToAdd % gaps;
      int start = 0;
      String result = "";
      boolean done = false;
      while (!done)
      {
         int next = nextWord(line, start);
         result = result + line.substring(start, next);
         if (next == line.length()) // Last word of the line
         {
            done = true;
         }
         else
         {
            for (int i = 1; i <= spacesPerGap; i++)
            {
               result = result + " ";
            }
            if (extraSpaces > 0)
            {
               result = result + " ";
               extraSpaces--;
            }
            start = next;
         }
      }
      return result;
   }
   
   public static void main(String[] args)
   {
      final int MARGIN = 40;

      Scanner in = new Scanner(System.in);
      System.out.println("Enter the words of a paragraph, Q to quit:");
      System.out.println("---------+---------+---------+---------+");
      String line = in.next();
      boolean done = line.equals("Q");
      while (!done)
      {
         String word = in.next();
         if (word.equals("Q")) 
         {
            System.out.println(line);
            done = true;
         }
         else 
         {
            if (line.length() + 1 + word.length() <= MARGIN)
            {
               line = line + " " + word;
            }
            else
            {
               System.out.println(format(line, MARGIN));
               line = word;
            }
         }
      }
      System.out.println("---------+---------+---------+---------+");
   }
}
