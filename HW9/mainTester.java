import java.util.List;
import java.util.Map;

/**
 * Created by Danny on 4/15/17.
 */
public class mainTester {
    public static void main (String[] args) {
        String text = "asdfasdfaeasdfhkmg";
        Map<Character, Integer> lastTable = new java.util.HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (!lastTable.containsKey(text.charAt(i))) {
                int last = i;
                for (int j = i + 1; j < text.length(); j++) {
                    if (text.charAt(j) == text.charAt(i)) {
                        last = j;
                    }
                }
                lastTable.put(text.charAt(last), last);
            }
        }
        lastTable.put('*', -1);
        for (Map.Entry<Character, Integer> entry : lastTable.entrySet())
        {
            System.out.println("("+ entry.getKey() + "," + entry.getValue() +")");
        }
    }

}
