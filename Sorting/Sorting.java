import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sorting{
    public static void main(String args[]){
        Sorting tester = new Sorting();
        MergeSort mergerSorter = new MergeSort();
        HeapSort heapSorter = new HeapSort();
        HashMap<String, List<String>> anagrams = new HashMap<>();

        String[] words = {"bucket", "rat", "mango", "tango", "ogtan", "tar"};
        for(int i = 0; i < words.length; i++){
            int[] temp = tester.stringToValue(words[i]);
            mergerSorter.mergeSort(temp);
            String sorted = tester.charArrayToString(temp);
            if (anagrams.get(sorted) == null)
                anagrams.put(sorted, new ArrayList<>());
            anagrams.get(sorted).add(words[i]);
        }

        String[] moreWords = {"below", "elbow", "peach", "night", "thing", "cheap", "goman", "tame"};
        for(int i = 0; i < moreWords.length; i++){
            int[] temp = tester.stringToValue(moreWords[i]);
            heapSorter.sort(temp);
            String sorted = tester.charArrayToString(temp);
            if (anagrams.get(sorted) == null)
                anagrams.put(sorted, new ArrayList<>());
            anagrams.get(sorted).add(moreWords[i]);
        }
        System.out.println(Arrays.toString(anagrams.values().toArray()));
    }

    public int[] stringToValue(String word){
        char[] letters = word.toCharArray();
        int[] letterValues = new int[letters.length];
        for (int i = 0; i < letters.length; i++)
            letterValues[i] = (int) letters[i];
        return letterValues;
    }

    public String charArrayToString(int[] letters){
        String word = "";
        for (int i = 0; i < letters.length; i++)
            word = word + (char) letters[i];
        return word;
    }
    
}