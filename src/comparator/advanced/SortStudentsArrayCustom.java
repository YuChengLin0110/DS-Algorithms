package comparator.advanced;

import java.util.Arrays;

public class SortStudentsArrayCustom {
    /*
     * 先按 dept 升序
     * dept 相同 → 按三科總分降序
     * 總分相同 → 按第一科 score1 降序
     * */
    public static void main(String[] args) {
        // {dept, score1, score2, score3}
        int[][] data = {
                {2, 80, 90, 85},
                {1, 70, 75, 80},
                {2, 85, 80, 90},
                {1, 80, 85, 80}
            };
        
       Arrays.sort(data, (a, b) -> {
           int result = Integer.compare(a[0], b[0]); // 按 dept 升序
           
           if(result == 0) {
               // 按三科總分降序
               int sumA = 0;
//               skip(1) -> 從 index 1 開始, limit(3) -> 只取 3 個元素
//               int sumA = Arrays.stream(a).skip(1).limit(3).sum();
               int sumB = 0;
//               int sumB = Arrays.stream(b).skip(1).limit(3).sum();
               
               for(int i = 1 ; i < a.length ; i++) {
                   sumA += a[i];
                   sumB += b[i];
               }
               
               result = Integer.compare(sumB, sumA);
           }
           
           if(result == 0) {
               result = Integer.compare(b[1], a[1]); // 按第一科 score1 降序
           }
           
           return result;
       });
       
       System.out.println(Arrays.deepToString(data));
    }
}
