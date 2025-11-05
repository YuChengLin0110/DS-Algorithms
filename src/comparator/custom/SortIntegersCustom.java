package comparator.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortIntegersCustom {
    /*
     * 1. 升序排序
     * 2. 降序排序
     * 3. 把奇數排在前面，偶數排在後面，內部再升序
     * */
    public static void main(String[] args) {
        // Array
        int[] numsArray = {5, 2, 9, 1, 3};
        
        // 1.升序
        Arrays.sort(numsArray);
        System.out.println(Arrays.toString(numsArray));
        
        // 2.降序
        Integer[] boxedNums = new Integer[numsArray.length];
        for(int i = 0 ; i < numsArray.length ; i++) {
            boxedNums[i] = numsArray[i];
        }
        Arrays.sort(boxedNums, (a, b) -> b.compareTo(a));
        System.out.println(Arrays.toString(boxedNums));
        
        // 3.把奇數排在前面，偶數排在後面，內部再升序
        Arrays.sort(boxedNums, (a, b) -> {
            if(a % 2 != b % 2) { // 判斷 a、b 奇偶性是否不同
                return (a % 2 != 0) ? -1 : 1; // 奇數排前，偶數排後
            }
            
            return a.compareTo(b); // a、b 都是奇數或都偶數，就直接升序
        });
        
        System.out.println(Arrays.toString(boxedNums));
        
        // List
        List<Integer> numsList = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 3));
        
        // 1.升序
        numsList.sort((a, b) -> a.compareTo(b));
//        numsList.sort(Integer::compareTo); 也可以這樣寫
        System.out.println(numsList);
        
        // 2.降序
        numsList.sort((a, b) -> b.compareTo(a));
//        numsList.sort(Comparator.reverseOrder()); 也可以這樣寫
        System.out.println(numsList);
        
        // 3.把奇數排在前面，偶數排在後面，內部再升序
        numsList.sort((a, b) -> {
            if( a % 2 != b % 2) { // 判斷 a、b 奇偶性是否不同
                return (a % 2 != 0) ? -1 : 1; // 奇數排前，偶數排後
            }
            
            return a.compareTo(b); // a、b 都是奇數或都偶數，就直接升序
        });
        
        System.out.println(numsList);
        
    }
}
