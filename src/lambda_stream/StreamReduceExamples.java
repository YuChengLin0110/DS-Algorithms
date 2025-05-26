package lambda_stream;

import java.util.List;

import lambda_stream.model.User;

/*
 * 1. 計算總年齡
 * 2. 找最年長的人
 * */
public class StreamReduceExamples {

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Alice", 17),
                new User("Andy", 20),
                new User("Bob", 22),
                new User("Bella", 30),
                new User("Charlie", 16),
                new User("Cathy", 25)
        );
        
        // 1. 計算總年齡
        int totalAge = users.stream()
                            .map(u -> u.age)
                            .reduce(0, (a, b) -> a + b); // reduce 有初始值 0 ， 一定會回傳一個值
        
        System.out.println("Total age : " + totalAge);
        
        
        //2. 找最年長的人
        users.stream()
             .reduce((u1, u2) -> u1.age > u2.age ? u1 : u2) // reduce 沒有初始值 ， 回傳 Optional<T>
             .ifPresent(old -> System.out.println("old : " + old));
    }
}