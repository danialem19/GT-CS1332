import java.util.Scanner;

/**
   This program demonstrates the use of a heap as a priority queue.
*/
public class HeapDemo
{
   public static void main(String[] args)
   {

      MinHeap<Integer> q = new MinHeap<>();
      q.add(0);
      q.add(8);
      q.add(7);
      q.add(7);
      q.add(5);
      q.add(9);
      q.add(1);
      q.add(4);
      q.add(3);

      System.out.println(q.toString());
      Scanner input = new Scanner(System.in);
      System.out.print("Pick 1:Add  2:Remove ");
      int in;
      while (input.hasNextInt()) {
         in = input.nextInt();
         if (in == 1) {
            System.out.print("Add what: ");
            input = new Scanner(System.in);
            q.add(input.nextInt());
         } else {
            System.out.println("Removed: " + q.remove());
         }
         System.out.println(q.toString());
         System.out.print("Pick 1:Add  2:Remove ");
      }


   }
}
