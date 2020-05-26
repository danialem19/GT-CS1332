import java.util.Scanner;

/**
   This program gives a description of an earthquake, given the Richter scale magnitude.
*/
public class EarthquakeStrength
{
   public static void main(String[] args)
   {  
      Scanner in = new Scanner(System.in);
      System.out.print("Enter a magnitude on the Richter scale: ");
      double richter = in.nextDouble();
      String description = getDescription(richter);
      System.out.println(description);
   }

   /**
      Gets the description of an earthquake for a given magnitude
      on the Richter scale.
      @param richter the magnitude on the Richter scale
      @return a description of the damage
   */
   public static String getDescription(double richter)
   {
      if (richter >= 8.0)
      {
         return "Most structures fall";
      }
      if (richter >= 7.0)
      {
         return "Many buildings destroyed";
      }
      if (richter >= 6.0)
      {
         return "Many buildings considerably damaged, some collapse";
      }
      if (richter >= 4.5)
      {
         return "Damage to poorly constructed buildings";
      }
      return "No destruction of buildings";
   }

   /**
      Gets the description of an earthquake for a given magnitude
      on the Richter scale. This implementation uses a single return
      statement.
      @param richter the magnitude on the Richter scale
      @return a description of the damage
   */
   public static String getDescription2(double richter)
   {
      String result = "No destruction of buildings";
      if (richter >= 8.0)
      {
         result = "Most structures fall";
      }
      else if (richter >= 7.0)
      {
         result = "Many buildings destroyed";
      }
      else if (richter >= 6.0)
      {
         result = "Many buildings considerably damaged, some collapse";
      }
      else if (richter >= 4.5)
      {
         result = "Damage to poorly constructed buildings";
      }
      return result;
   }
}

