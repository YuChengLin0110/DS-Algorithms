package comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorForInteger {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 1, 9, 3, 7);
        
        Collections.sort(numbers); // 舊方法
        
        numbers.sort((a, b) -> a.compareTo(b)); // 排序的類別有實作 Comparable 則 可使用 compareTo
        numbers.sort(Comparator.reverseOrder()); // Integer 類別有實作 Comparable 也可使用 Comparator.reverseOrder() 降序
        
        /*
         * (a, b) -> a - b 跟 Integer.compare(a, b) 兩者相同 ， 推薦使用後者，不會溢位
         * */
        numbers.sort((a, b) -> a - b); // 正序 ， 不推薦使用減法 可能會溢位
        numbers.sort((a, b) -> b - a); // 倒序 ， 不推薦使用減法 可能會溢位
        
        numbers.sort((a, b) -> Integer.compare(a, b)); // 正
        numbers.sort((a, b) -> Integer.compare(b, a)); // 倒
        
        numbers.sort(Comparator.comparingInt(a -> a)); // 正
        numbers.sort(Comparator.comparingInt(a -> -a)); // 倒
        
        
        
        for(int i : numbers) {
            System.out.println(i);
        }
    }

}
