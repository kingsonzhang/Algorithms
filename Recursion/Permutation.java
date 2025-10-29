import java.util.Arrays;
import java.util.HashMap;

public class Permutation{
    public boolean permutationExists(String bigString, String smallString){
        HashMap<String, Boolean> encountered = new HashMap<>();
        return this.find(bigString.toLowerCase(), this.sortString(smallString.toLowerCase()), encountered);
    }

    /**
     * Recursive call to find if permutation of smallString exists in bigString
     * Recursive call shrinks bigString by one character on both left and right to check
     * @param bigString Large string to check if smallString permutation exists inside
     * @param smallString Small string to check if permutation exists inside bigString
     * @param memoized Record of Strings checked in order to save on recursive calls
     * @return true if permutation of smallString exists in bigString, otherwise false
     */
    private boolean find(String bigString, String smallString, HashMap<String, Boolean> memoized) {
        if (memoized.containsKey(bigString))
            return memoized.get(bigString);
        else if (bigString.length() == smallString.length()){
            String sorted = this.sortString(bigString);
            boolean found = sorted.equals(smallString);
            memoized.put(bigString, found);
            return found;
        }
        else 
            return this.find(bigString.substring(1), smallString, memoized) || this.find(bigString.substring(0, bigString.length() - 1), smallString, memoized);
    }

    /**
     * Helper function to sort a String word into alphabetical order
     * @param word to be alphabetized
     * @return String of alphabetized word
     */
    private String sortString(String word){
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}