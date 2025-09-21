package comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorForString {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("kiwi", "apple", "banana");
        words.sort((a, b) -> b.compareTo(a)); // 字典序降序 ， 排序的類別有實作 Comparable 則 可使用 compareTo
        words.sort(Comparator.comparing(s -> s)); // 字典序升序
        words.sort(Comparator.reverseOrder()); // String 類別有實作 Comparable 也可使用 Comparator.reverseOrder() 降序
        
        /*
         * 對物件排序，尤其用 comparing 或 reversed()，最好用方法引用
         * 方法引用：型別清楚、簡潔、不容易出錯
         * Lambda：要寫明型別，否則可能 Cannot infer type argument(s)
         * */
        // 用方法引用 String :: toString，型別明確、編譯器推斷穩定
        words.sort(Comparator.comparing(String :: toString).reversed()); // 字典序降序
        // 也可以用 lambda (p -> p.name)，但要明確指定型別 (Person p)，否則編譯器可能推不出泛型
        words.sort(Comparator.comparing((String s) -> s).reversed()); // 字典序降序
        
        
        
        for(String s : words) {
            System.out.println(s);
        }

    }

}
