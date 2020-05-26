import java.util.Scanner;

public class PunchRecipe
{
   public static void main(String[] args)
   {      
      Scanner in = new Scanner(System.in);
      System.out.print("Number of people: ");
      int people = in.nextInt();
      final int OZ_PER_PERSON = 9;
      int amount = people * OZ_PER_PERSON;
      final int OZ_PER_CAN = 12;
      int cans = (amount - 1) / OZ_PER_CAN;
      cans++;
      System.out.println("Cans: " + cans);
   }
}
