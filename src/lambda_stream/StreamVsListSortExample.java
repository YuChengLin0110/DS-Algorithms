package lambda_stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lambda_stream.model.User;

public class StreamVsListSortExample {

    public static void main(String[] args) {
        List<User> original = new ArrayList<>(List.of(
                
                new User("Alice", 17),
                new User("Bob", 22),
                new User("Charlie", 16),
                new User("David", 30)
        ));
        
        // 1. 使用 List.sort 會改變原本的排序
        List<User> user1 = new ArrayList<>(original); // 複製一份
        user1.sort(Comparator.comparingInt(u -> u.age)); // 改變 users1 排序順序
        System.out.println("List.sort() 排序");
        user1.forEach(System.out::println);
        
        // 2. 使用 stream().sorted()，不改變原本的資料
        List<User> user2 = original.stream()
                                   .sorted(Comparator.comparingInt(u -> u.age))
                                   .collect(Collectors.toList()); // 創造新排序好的 List ， original 不變
        System.out.println("Stream().sort 排序");
        user2.forEach(System.out::println);
        
        // 3.
        System.out.println("原 original");
        original.forEach(System.out::println);
    }

}
