package comparator.basic;

import java.util.Arrays;

public class SortStringsIgnoreCase {

    public static void main(String[] args) {
        /*
         * 先按 忽略大小寫排序
         * 如果忽略大小寫後相同，再按 原本大小寫排序
         * */
        String[] words = {"banana", "Apple", "cherry", "Date", "apple"};
        
        Arrays.sort(words, (a, b) -> {
            int result = a.compareToIgnoreCase(b); // 忽略大小寫
            if(result == 0) {
                return a.compareTo(b);
            }
            
            return result;
        });
        
        System.out.println(Arrays.toString(words));
    }
}
