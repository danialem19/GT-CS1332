import java.util.Scanner;

public class ReadTime
{
   /**
      Prompts a user to enter a value within a given range until the user
      provides a valid input.
      @param low the smallest allowable input
      @param high the largest allowable input
      @return the value provided by the user (between low and high, inclusive)
   */
   public static int readIntBetween(int low, int high)
   {
      int input;
      Scanner in = new Scanner(System.in);
      do
      {
         System.out.print("Enter a value between " + low
            + " and " + high + ": ");
         input = in.nextInt();
      }
      while (input < low || input > high);
      return input;
   }

   public static void main(String[] args)
   {
      System.out.println("Please enter a time; hours, then minutes.");
      int hours = readIntBetween(0, 23);
      int minutes = readIntBetween(0, 59);
      System.out.printf("You entered %d hours and %d minutes.%n",
         hours, minutes);
   }
}
