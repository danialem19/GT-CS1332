import java.util.Scanner;

/**
 * Created by Danny on 2/18/17.
 */
public class tester {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        MaxPriorityQueue<Integer> mpq = new MaxPriorityQueue<>();
        System.out.println(mpq.toString());
        Scanner input = new Scanner(System.in);
        System.out.print("Pick 1:Add  2:Remove ");
        int in;
        while (input.hasNextInt()) {
            in = input.nextInt();
            if (in == 1) {
                System.out.print("Add what: ");
                input = new Scanner(System.in);
                mpq.enqueue(input.nextInt());
            } else {
                System.out.println("Removed: " + mpq.dequeue());
            }
            System.out.println(mpq.toString());
            System.out.print("Pick 1:Add  2:Remove ");
        }

//        MaxHeap<Integer> q = new MaxHeap<>();
//        q.add(0);
//        q.add(8);
//        q.add(7);
//        q.add(7);
//        q.add(5);
//        q.add(9);
//        q.add(1);
//        q.add(4);
//        q.add(3);
//
//        System.out.println(q.toString());
//        Scanner input = new Scanner(System.in);
//        System.out.print("Pick 1:Add  2:Remove ");
//        int in;
//        while (input.hasNextInt()) {
//            in = input.nextInt();
//            if (in == 1) {
//                System.out.print("Add what: ");
//                input = new Scanner(System.in);
//                q.add(input.nextInt());
//            } else {
//                System.out.println("Removed: " + q.remove());
//            }
//            System.out.println(q.toString());
//            System.out.print("Pick 1:Add  2:Remove ");
//        }
    }
}
