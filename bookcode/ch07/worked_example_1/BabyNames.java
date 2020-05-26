import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
   This program displays the most common baby names. Half of boys and girls in
   the United States were given these names in the 1990s.
*/
public class BabyNames
{
   public static void main(String[] args) throws FileNotFoundException
   {  
      final double LIMIT = 50;
      Scanner in = new Scanner(new File("babynames.txt"));

      double boyTotal = 0;
      double girlTotal = 0;
      
      while (boyTotal < LIMIT || girlTotal < LIMIT)
      {
         int rank = in.nextInt();
         System.out.print(rank + " ");
            
         boyTotal = processName(in, boyTotal, LIMIT);
         girlTotal = processName(in, girlTotal, LIMIT);
            
         System.out.println();
      }
      in.close();
   }

   /**
      Reads name information, prints the name, and adjusts the total.
      @param in the input stream
      @param total the total percentage that should still be processed
      @param limit the cutoff for printing
      @return the adjusted total
   */
   public static double processName(Scanner in, double total, double limit)
   {
      String name = in.next();
      int count = in.nextInt();
      double percent = in.nextDouble();
      
      if (total < limit) { System.out.print(name + " "); }
      total = total + percent;
      return total;
   }
}	
