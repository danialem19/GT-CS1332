import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Danny on 3/10/17.
 */
public class tester {
    //private static ArrayList<Integer> col =  new ArrayList(Arrays.asList(10, 1, 15, 22, 12, 13, 11, 23));
    //private static ArrayList<Integer> col =  new ArrayList(Arrays.asList(0, 1, 3, 4, 5, 6, 7, 8, -1));
    private static ArrayList<Integer> col =  new ArrayList(Arrays.asList(10, 12, 11));
    private static AVL<Integer> tree = new AVL<>(col);

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int selc;
        System.out.println("Height: " + tree.height());
        System.out.println("Pick a Task");
        System.out.print("    1) Add ");
        System.out.print("  2) Remove");
        System.out.print("  3) Get root ");
        System.out.print("  4) Check value ");
        System.out.print("  5) Traversals");
        System.out.print("  6) Size ");
        System.out.print("  7) GetRoot");
        System.out.println("  8) Balance and Height");

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            selc = in.nextInt();
            if (selc == 1) {
                System.out.println("ENTER THE ELEMENT ");
                in = new Scanner(System.in);
                tree.add(in.nextInt());
            } else if (selc == 2) {
                System.out.println("ENTER THE ELEMENT ");
                in = new Scanner(System.in);
                System.out.println(tree.remove(in.nextInt()) + " is removed");
            } else if (selc == 3) {
                System.out.println("The root is " + tree.getRoot());
            } else if (selc == 4) {
                System.out.println("ENTER ELEMENT ");
                in = new Scanner(System.in);
                System.out.println("Found: " + tree.contains(in.nextInt()));
            } else if (selc == 5) {
                System.out.println("PostOrder:  " + tree.postorder());
                System.out.println("PreOrder:   " + tree.preorder());
                System.out.println("InOrder:    " + tree.inorder());
                System.out.println("LevelOrder: " + tree.levelorder());
                System.out.println();
            } else if (selc == 6) {
                System.out.println("Size is:  " + tree.size());
            } else if (selc == 7) {
                System.out.println("The root is:  " + tree.getRoot());
            } else if (selc == 8) {
                //tree.levelorder1();
            }
            System.out.println("Height: " + tree.height());
            System.out.println("Pick a Task");
            System.out.print("    1) Add ");
            System.out.print("  2) Remove");
            System.out.print("  3) Get root ");
            System.out.print("  4) Check value ");
            System.out.print("  5) Traversals");
            System.out.print("  6) Size ");
            System.out.print("  7) GetRoot");
            System.out.println("  8) Balance and Height");;
        }
    }
}
