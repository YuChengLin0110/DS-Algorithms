package comparator.objects;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortStudentsCustom {
    /*
     * 先按 score 降序
     * 
     * score 相同 → 按 age 升序
     * 
     * score 和 age 相同 → 按 name 字典序升序
     */
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student("Alice", 90, 20), 
                new Student("Bob", 90, 18),
                new Student("Charlie", 85, 22), 
                new Student("Dave", 90, 22));
        
        // 寫法 1
        students.sort((a, b) -> {
            int result = Integer.compare(b.score, a.score); // score 降序
            
            if(result == 0) { // score 相同
                result = Integer.compare(a.age, b.age); // age 升序
            }
            
            if(result == 0) { // score 和 age 相同
                result = a.name.compareTo(b.name); // name 字典序升序
            }
            
            return result;
        });
        
        System.out.println(students);
        
        
        // 寫法 2
        students.sort(Comparator.comparing(Student::getScore).reversed() // score 降序
                .thenComparing(Student::getAge) // age 升序
                .thenComparing(Student::getName)); // name 字典序升序
        
        System.out.println(students);
    }
}

class Student {
    String name;
    int score;
    int age;

    public Student(String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "(" + score + "," + age + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
