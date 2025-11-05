package comparator.advanced;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortStringsByLengthAndFrequency {
    /*
     * 單詞長度升序
     * 
     * 長度相同 → 出現次數降序
     * 
     * 出現次數相同 → 字典序升序
     */
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "pear", "apple", "kiwi", "pear", "banana", "kiwi",
                "kiwi");
        
        Map<String, Integer> wordsFreq = new HashMap<>();
        for(String word : words) {
            wordsFreq.put(word, wordsFreq.getOrDefault(word, 0) + 1);
        }
        
        // 寫法 1
        words.sort((a, b) -> {
            int result = Integer.compare(a.length(), b.length());
            
            if(result == 0) {
                result = Integer.compare(wordsFreq.get(b), wordsFreq.get(a));
            }
            
            if(result == 0) {
                result = a.compareTo(b);
            }
            
            return result;
        });
        
        System.out.println(words);
        
        // 寫法 2
        words.sort(Comparator.comparingInt(String::length)
                .thenComparing((a, b) -> Integer.compare(wordsFreq.get(b), wordsFreq.get(a)))
                .thenComparing(String::compareTo));
        
        System.out.println(words);
    }
}
