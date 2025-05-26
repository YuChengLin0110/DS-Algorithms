package lambda_stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lambda_stream.model.User;


/* 1. 分為是否成年並排序
 * 2. 按照姓名首字母分組，計算人數
 * */
public class StreamPartitionAndGroup {

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Alice", 17),
                new User("Andy", 20),
                new User("Bob", 22),
                new User("Bella", 30),
                new User("Charlie", 16),
                new User("Cathy", 25)
        );
        
        // 1. 分為是否成年並排序
        // 分組 ， partitioningBy 只能分 true false
        Map<Boolean, List<User>> part = users.stream()
                                             .collect(Collectors.partitioningBy(u -> u.age >= 18));
        
        //排序
        part.forEach((isAdult, group) -> group.sort(Comparator.comparingInt(u -> u.age)));
        
        part.forEach((isAdult, group) -> System.out.println("isAdult : " + isAdult + " => " + group));
        
        
        // 2. 按照姓名首字母分組，計算人數
        //分組
        Map<String, Long> group = users.stream()
                                       .collect(Collectors.groupingBy(u -> u.name.substring(0,1), Collectors.counting()));
        
        group.forEach((k, v) -> System.out.println(k + "開頭 : " + v));
    }
}