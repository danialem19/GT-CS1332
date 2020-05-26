import java.util.*;

/**
 * Created by Danny on 1/14/17.
 */

public class tester {
    private static ArrayList<Integer> col =  new ArrayList(Arrays.asList(10, 1, 15, 22, 12, 13, 11, 23, 6));
    private static BST<Integer> tree = new BST(col);

    /**
     *
     * @return
     */
    public static int sumOddLevels() {
        return sumoOddLevels(tree.getRoot());
    }

    /** Returns sum of node values
     * @param root the parent node
     * @return
     */
    private static int sumoOddLevels(BSTNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int result = root.getData() + sumoOddLevels(root.getLeft()) + sumoOddLevels(root.getRight());
        return result;
    }

    /**
     * Returns sum of odd levels
     * @param n start from root
     * @return
     */
    public static int addOddslev(BSTNode<Integer> n) {
        int sum = 0;
        if (n == null)
            return 0;
        if (n.getLeft() != null) {
            sum = sum + n.getLeft().getData() + addOddslev(n.getLeft().getLeft()) + addOddslev(n.getLeft().getRight());
        }
        if (n.getRight() != null) {
            sum = sum + n.getRight().getData() + addOddslev(n.getRight().getLeft()) + addOddslev(n.getRight().getRight());
        }
        return sum;
    }


    /**
     *
     * @param n
     */
    public static void addOdds(BSTNode<Integer> n) {
        Queue<BSTNode<Integer>> nodequeue = new LinkedList<>();
        if (n != null)
            nodequeue.add(n);
        while (!nodequeue.isEmpty()) {
            BSTNode<Integer> next = nodequeue.remove();
            if (next.getLeft() != null) {
                System.out.print(next.getLeft().getData() + " ");
                if (next.getLeft().getLeft() != null)
                    nodequeue.add(next.getLeft().getLeft());
                if (next.getLeft().getRight() != null)
                    nodequeue.add(next.getLeft().getRight());
            }
            if (next.getRight() != null) {
                System.out.print(next.getRight().getData() + " ");
                if (next.getRight().getLeft() != null)
                    nodequeue.add(next.getRight().getLeft());
                if (next.getRight().getRight() != null)
                    nodequeue.add(next.getRight().getRight());
            }
        }
    }
    /**
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
        System.out.println("  6) Sum");

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
                tree.remove(in.nextInt());
            } else if (selc == 3) {
                System.out.println("The root is " + tree.getRoot().getData());
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
                System.out.println("Sum " + sumOddLevels());
                addOdds(tree.getRoot());
                System.out.println();
                System.out.println("Sum " + addOddslev(tree.getRoot()));
                System.out.println();
            }
            System.out.println("Height: " + tree.height());
            System.out.println("Pick a Task");
            System.out.print("    1) Add ");
            System.out.print("  2) Remove");
            System.out.print("  3) Get root ");
            System.out.print("  4) Check value ");
            System.out.print("  5) Traversals");
            System.out.println("  6) Sum");
        }
    }
}
