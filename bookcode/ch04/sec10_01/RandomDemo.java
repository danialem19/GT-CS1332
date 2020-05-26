/**
   This program prints ten random numbers between 0 and 1.
*/
public class RandomDemo
{
   public static void main(String[] args)
   {  
      for (int i = 1; i <= 10; i++)
      {  
         double r = Math.random();
         System.out.println(r);
      }
   }
}
