package lambda_stream;

import java.util.Comparator;
import java.util.List;

import lambda_stream.model.User;

public class StreamMethodReferenceExamples {

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Alice", 17),
                new User("Andy", 20),
                new User("Bob", 22),
                new User("Bella", 30),
                new User("Charlie", 16),
                new User("Cathy", 25)
        );
        
        // 1. 找出最大年齡（使用 map + max + Integer::compareTo)
        users.stream()
             .map(user -> user.age)
             .max(Integer::compareTo)
             .ifPresent(age -> System.out.println("Max age : " + age));
        
        
        // 2. 列出所有名字（用 map + forEach + System.out::println）
        users.stream()
             .map(user -> user.name)
             .forEach(System.out::println);
        
        // 3. 依照名字排序使用者
        users.stream()
             .sorted(Comparator.comparing(User::getName))
             .forEach(System.out::println);
    }
}