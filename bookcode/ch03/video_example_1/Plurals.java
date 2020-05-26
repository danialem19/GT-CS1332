import java.util.Scanner;

public class Plurals
{
   public static void main(String[] args)
   {
      System.out.print("Please enter a word: ");
      Scanner in = new Scanner(System.in);
      String word = in.next();
      int lastPos = word.length() - 1;
      char last = word.charAt(lastPos);
      char before = word.charAt(lastPos - 1);
      String plural;
      if (word.endsWith("ch") || word.endsWith("s")
         || word.endsWith("sh") || word.endsWith("x") 
         || word.endsWith("z"))
      {
         plural = word + "es";
      }
      else if (!(before == 'a' || before == 'e' || before == 'i'
            || before == 'o' || before == 'u') && last == 'y')
      {
         plural = word.substring(0, lastPos) + "ies";
      }
      else
      {
          plural = word + "s";
      }
      System.out.println("Plural: " + plural);
   }
}
