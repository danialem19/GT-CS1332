import java.util.Scanner;

/**
 * Created by Danny on 2/25/17.
 */
public class tester {
    /**
     * Main method
     */
    public static void main(String[] args) {
        HashMap<String, Integer> hash = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hash.put("Dan" + i, i);
        }
        System.out.println("Values: " + hash.toString());
        System.out.println();
        System.out.print("Pick a Task=> 1:Add 2:ContainsKey 3:GetKeyValue 4:Clear 5:Remove 6:KeySet 7:ValueSet");
        Scanner input = new Scanner(System.in);
        int in = 0;
        while (input.hasNextInt()) {
            in = input.nextInt();
            if (in == 1) {
                System.out.print("Enter K V: ");
                String K = input.next();
                int V = input.nextInt();
                hash.put(K, V);
                System.out.println();
            } else if (in == 2) {
                System.out.print("Enter Key: ");
                System.out.println(hash.containsKey(input.next()));
            } else if (in == 3) {
                System.out.print("Enter Key: ");
                System.out.println("The value is " + hash.get(input.next()));
                System.out.println();
            } else if (in == 4) {
               hash.clear();
            } else if (in == 5) {
                System.out.print("Enter Key: ");
                System.out.println("The removed value is " + hash.remove(input.next()));
                System.out.println();
            } else if (in == 6) {
                System.out.print("Enter Key: ");
                System.out.println("Keys: " + hash.keySet());
                System.out.println();
            } else if (in == 7) {
                System.out.print("Enter Key: ");
                System.out.println("Values: " + hash.values());
                System.out.println();
            }
            System.out.println("Values: " + hash.toString());
            System.out.print("Pick Task: 1: Add 2:ContainsKey 3:GetKeyValue 4:Clear  5:Remove 6:KeySet 7:ValueSet");
            input = new Scanner(System.in);
        }
    }
}
