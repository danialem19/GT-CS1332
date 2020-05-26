public class Digits
{
   /**
      Computes the sum of the digits of a number.
      @param n an integer >= 0
      @return the sum of the digits of n
   */
   public static int digitSum(int n)
   {
      if (n == 0) { return 0; } // Special case for terminating the recursion
      return digitSum(n / 10) + n % 10; // General case
   }

   public static void main(String[] args)
   {
      System.out.println("Digit sum: " + digitSum(1729));
      System.out.println("Expected: 19");
      System.out.println("Digit sum: " + digitSum(1000));
      System.out.println("Expected: 1");
      System.out.println("Digit sum: " + digitSum(9));
      System.out.println("Expected: 9");
      System.out.println("Digit sum: " + digitSum(0));
      System.out.println("Expected: 0");
   }
}
