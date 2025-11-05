package comparator.objects;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortEmployeeCustom {
    /*
     * 先按 dept 升序
     * 
     * 部門相同 → 按 三科總分降序
     * 
     * 部門與總分相同 → 按 name 字典序升序
     */
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 2, new int[] { 85, 90, 80 }),
                new Employee("Bob", 1, new int[] { 80, 90, 85 }), 
                new Employee("Charlie", 2, new int[] { 85, 85, 90 }),
                new Employee("Dave", 1, new int[] { 80, 95, 80 }));
        
        employees.sort((a, b) -> {
            int result = Integer.compare(a.dept, b.dept); // 按 dept 升序
            
            if(result == 0) {
                // 按 三科總分降序
                int sumA = 0;
//                int sumA = Arrays.stream(a.scores).sum(); 改用 stream 就不用手寫迴圈
                int sumB = 0;
//                int sumB = Arrays.stream(b.scores).sum(); 改用 stream 就不用手寫迴圈
                
                for(int i = 0 ; i < a.scores.length ; i++) {
                    sumA += a.scores[i];
                    sumB += b.scores[i];
                }
                
                result = Integer.compare(sumB, sumA);
            }
            
            if(result == 0) {
                result = a.name.compareTo(b.name); // 按 name 字典序升序
            }
            
            return result;
        });
           
        System.out.println(employees);
        
    }
}

class Employee {
    String name;
    int dept;
    int[] scores;

    public Employee(String name, int dept, int[] scores) {
        this.name = name;
        this.dept = dept;
        this.scores = scores;
    }

    @Override
    public String toString() {
        return name + "(D" + dept + ", " + Arrays.toString(scores) + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }
}