package lambda_stream;

import java.util.List;
import java.util.OptionalDouble;

/* 給定 List<Integer>，取出偶數 → 平方 → 由小到大排序 → 印出
 * 
 * */
public class BasicStreamOperations {

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        
        nums.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * n)
            .sorted()
            .forEach(n -> System.out.println(n));
        
        OptionalDouble avg = nums.stream()
                                 .mapToInt(n -> n)
                                 .average();
        
        avg.ifPresent(n -> System.out.println(n));
    }

}
