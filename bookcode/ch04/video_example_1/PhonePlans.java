import java.util.Scanner;

public class PhonePlans
{
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Minutes used: ");
      int minutesUsed = in.nextInt();
      System.out.print("Messages used: ");
      int messagesUsed = in.nextInt();
      System.out.print("Megabyte used: ");
      int megabyteUsed = in.nextInt();    

      String cheapestPlanName = "";
      double cheapestPlanCost = 0;
      boolean done = false;
      while (!done)
      {
         System.out.print("Plan name (one word, Q to quit): ");
         String planName = in.next();
         if (planName.equals("Q")) 
         {
            done = true;
         }
         else
         {
            System.out.print("Phone cost: ");
            double phoneCost = in.nextDouble();
            System.out.print("Monthly fee: ");
            double monthlyFee = in.nextDouble();         
            System.out.print("Free minutes: ");
            int freeMinutes = in.nextInt();
            System.out.print("Free messages: ");
            int freeMessages = in.nextInt();
            System.out.print("Free megabyte: ");
            int freeMegabyte = in.nextInt();
            System.out.print("Fee for each additional minute: ");
            double additionalMinuteFee = in.nextDouble();
            System.out.print("Fee for each additional message: ");
            double additionalMessageFee = in.nextDouble();
            System.out.print("Fee for each additional megabyte: ");
            double additionalMegabyteFee = in.nextDouble();

            final int MONTHS_IN_CONTRACT = 24;
            double monthlyCost = phoneCost / MONTHS_IN_CONTRACT + monthlyFee;

            if (minutesUsed > freeMinutes)
            {
               monthlyCost = monthlyCost 
                  + (minutesUsed - freeMinutes) * additionalMinuteFee;
            }

            if (messagesUsed > freeMessages)
            {
               monthlyCost = monthlyCost 
                  + (messagesUsed - freeMessages) * additionalMessageFee;
            }

            if (megabyteUsed > freeMegabyte)
            {
               monthlyCost = monthlyCost 
                  + (megabyteUsed - freeMegabyte) * additionalMegabyteFee;
            }

            if (cheapestPlanName.equals("") || cheapestPlanCost > monthlyCost)
            {
               cheapestPlanName = planName;
               cheapestPlanCost = monthlyCost;
            }
            System.out.println("Monthly cost of this plan: " + monthlyCost);
         }
      }
      System.out.println("Cheapest plan: " + cheapestPlanName);
      System.out.println("Monthly cost: " + cheapestPlanCost);
   }
}
