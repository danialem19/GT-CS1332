/**
   This program demonstrates how to print a triangle using recursion.
*/
public class TrianglePrinter
{
   public static void main(String[] args)
   {
      printTriangle(4);
   }

   /**
      Prints a triangle with a given side length.
      @param sideLength the length of the bottom row
   */
   public static void printTriangle(int sideLength)
   {
      if (sideLength < 1) { return; }
      printTriangle(sideLength - 1);

      // Print the row at the bottom

      for (int i = 0; i < sideLength; i++)
      {
         System.out.print("[]");
      }
      System.out.println();
   }
}
