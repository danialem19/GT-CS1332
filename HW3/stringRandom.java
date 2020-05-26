/**
 * Created by Danny on 9/7/17.
 */
import java.util.*;

public class stringRandom {
    /**

     */
    public static void main(String[] args) {
       shuffle("Daniel");

    }

    /**
     *
     * @param input asdfasdfsad
     */
    public static void shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0){
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }

        System.out.println(output.toString());
    }
}
