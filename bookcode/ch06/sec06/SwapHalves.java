import java.util.Arrays;

public class SwapHalves
{
   /**
      Swaps the elements of an array at given positions.
      @param a the array 
      @param i the first position
      @param j the second position
   */
   public static void swap(int[] a, int i, int j)
   {
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }

   public static void main(String[] args)
   {
      int[] values = { 9, 13, 21, 4, 11, 7, 1, 3 };

      int i = 0;
      int j = values.length / 2;
      while (i < values.length / 2)
      {
         swap(values, i, j);
         i++;
         j++;
      }

      System.out.println(Arrays.toString(values));
   }
}
