import java.util.Arrays;
import java.util.HashMap;

public class Permutation{
    public static void main(String args[]){
        Permutation tester = new Permutation();
        System.out.println(tester.permutationExists("HELLO WORLD", "world"));
    }

    public boolean permutationExists(String bigString, String smallString){
        HashMap<String, Boolean> encountered = new HashMap<>();
        return this.find(bigString.toLowerCase(), this.sortString(smallString.toLowerCase()), encountered);
    }

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

    private String sortString(String word){
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}