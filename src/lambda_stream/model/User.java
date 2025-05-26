package lambda_stream.model;

public class User {
    public String name;
    public int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String toString() {
        return name + "(" + age + ")";
    }
}
