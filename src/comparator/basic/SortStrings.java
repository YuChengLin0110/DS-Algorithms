package comparator.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortStrings {

    public static void main(String[] args) {
        /*
         * String[]
         * */
        String[] words = {"banana", "apple", "cherry", "date"};
        
        // 升序
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));
        
        // 降序
        Arrays.sort(words, (a, b) -> b.compareTo(a));
        System.out.println(Arrays.toString(words));
        
        /*
         * List<String>
         * */
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        
        // 升序
        wordList.sort((a, b) -> a.compareTo(b));
        System.out.println(wordList);
        
        // 降序
        wordList.sort((a, b) -> b.compareTo(a));
        System.out.println(wordList);
    }
}
