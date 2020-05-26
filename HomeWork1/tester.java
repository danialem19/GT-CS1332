import java.util.Scanner;
/**
 * Created by Danny on 1/14/17.
 */
public class tester {
    public static void main(String[] args) {
        int selc;
        Scanner in = new Scanner(System.in);
        ArrayList<String> arryList = new ArrayList();

        System.out.println("Pick a Task");
        System.out.print("    1) Add at Back");
        System.out.print("    2) Add at Front");
        System.out.print("    3) Add at Index");
        System.out.print("    4) Remove from Back");
        System.out.println("    5) Remove from Front");
        System.out.print("    6) Remove at Index");
        System.out.print("     7) Get Element");
        System.out.print("    8) Is it Empty");
        System.out.print("    9) What is the Size");
        System.out.println("    10) Clear Everything");

        while (in.hasNextInt()) {
            selc = in.nextInt();
            //in = new Scanner(System.in);

            if (selc == 1) {
                System.out.println("ENTER THE ELEMENT ");
                in = new Scanner(System.in);
                arryList.addToBack(in.next());
            } else if (selc == 2) {
                System.out.println("ENTER THE ELEMENT ");
                in = new Scanner(System.in);
                arryList.addToFront(in.next());
            } else if (selc == 3) {
                System.out.println("ENTER THE POSITION ");
                in = new Scanner(System.in);
                int pos = in.nextInt();

                System.out.println("ENTER THE VALUE ");
                in = new Scanner(System.in);
                String value = in.next();
                arryList.addAtIndex(pos, value);
            } else if (selc == 4) {
                System.out.println("Removed Back: " + arryList.removeFromBack());
            } else if (selc == 5) {
                System.out.println("Removed Front: " + arryList.removeFromFront());
            } else if (selc == 6) {
                System.out.println("ENTER THE POSITION ");
                in = new Scanner(System.in);
                arryList.removeAtIndex(in.nextInt());
            } else if (selc == 7) {
                System.out.println("ENTER THE POSITION ");
                in = new Scanner(System.in);
                int pos = in.nextInt();
                System.out.println("Element at postion " + pos + " is " + arryList.get(pos));
            } else if (selc == 8) {
                System.out.println("The ArrayList is Empty? " + arryList.isEmpty());
            } else if (selc == 9) {
                System.out.println("Ths size of the ArrayList is? " + arryList.size());
            } else if (selc == 10) {
                arryList.clear();
                System.out.println("ArrayList Cleared");
            }
            System.out.println("Your Elements: " + arryList.toString());
            System.out.println("Pick a Task");
            System.out.print("    1) Add at Back");
            System.out.print("    2) Add at Front");
            System.out.print("    3) Add at Index");
            System.out.print("    4) Remove from Back");
            System.out.println("    5) Remove from Front");
            System.out.print("    6) Remove at Index");
            System.out.print("     7) Get element");
            System.out.print("    8) Is it Empty");
            System.out.print("    9) What is the Size");
            System.out.println("    10) Clear Everything");
        }
    }
}
