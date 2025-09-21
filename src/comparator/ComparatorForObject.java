package comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorForObject {
    
    public static void main(String args[]) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 20),
                new Person("Charlie", 25)
                );
        
        people.sort(Comparator.comparingInt(p -> p.age)); // 升
        people.sort(Comparator.comparingInt(p -> -p.age)); // 降
        
        people.sort((a, b) -> Integer.compare(a.age, b.age)); // 升
        people.sort((a, b) -> Integer.compare(b.age, a.age)); // 降
        
        people.sort(Comparator.comparing(p -> p.name)); // 字典升
        
        /*
         * 對物件排序，尤其用 comparing 或 reversed()，最好用方法引用
         * 方法引用：型別清楚、簡潔、不容易出錯
         * Lambda：要寫明型別，否則可能 Cannot infer type argument(s)
         * */
        // 用方法引用 Person::getName，型別明確、編譯器推斷穩定
        people.sort(Comparator.comparing(Person :: getName).reversed()); // 字典降
        // 也可以用 lambda (p -> p.name)，但要明確指定型別 (Person p)，否則編譯器可能推不出泛型
        people.sort(Comparator.comparing((Person p) -> p.name).reversed()); // 字典降
        
        people.sort((a, b) -> a.name.compareTo(b.name)); // 字典降
    }

}

class Person { 
    String name; 
    int age; 
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}