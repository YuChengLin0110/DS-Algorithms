package lambda_stream;

/*
 * 自訂 Functional Interface 並用 Lambda 實作
 */

// 加上 @FunctionalInterface 會檢查是否真的只寫了一個抽象方法，避免不小心多寫方法導致不能用 Lambda
@FunctionalInterface
interface MyCalculator {
    int calculate(int a, int b);
}

public class BasicFunctionalInterfaces {

    public static void main(String[] args) {
        MyCalculator add = (a, b) -> a + b;
        MyCalculator multiply = (a, b) -> a * b;
        
        System.out.println("add : " + add.calculate(2, 5));
        System.out.println("multiply : " + multiply.calculate(2, 5));
    }
}
