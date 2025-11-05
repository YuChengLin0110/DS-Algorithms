package comparator.advanced;

import java.util.Arrays;
import java.util.Map;

public class SortMixedArrayMap {
    /*
     * 二維陣列 + Map 混合排序練習
     * 每列資料格式： {學生姓名, Map<科目, 分數>}
     * 
     * 排序規則：
     * 1. 總分降序
     * 2. 總分相同 → English 成績升序
     * 3. 總分與 English 相同 → 姓名字典序升序
     * 
     * 
     * Object[][] 每個元素都是 Object[]
     * 所以 a[1] / b[1] 被視為 Object
     * 要呼叫 Map 方法或 Stream，必須先強制轉型成 Map<String, Integer>
     */
    public static void main(String[] args) {
        Object[][] data = {
                {"Alice", Map.of("Math", 90, "English", 85, "Science", 95)},
                {"Bob", Map.of("Math", 80, "English", 90, "Science", 88)},
                {"Charlie", Map.of("Math", 90, "English", 75, "Science", 92)}
            };
        
        Arrays.sort(data, (a, b) -> {
            Map<String, Integer> scoresA = (Map<String, Integer>) a[1];
            Map<String, Integer> scoresB = (Map<String, Integer>) b[1];
            
            int sumA = scoresA.values().stream().mapToInt(Integer::intValue).sum();
            int sumB = scoresB.values().stream().mapToInt(Integer::intValue).sum();
            
            int result = Integer.compare(sumB, sumA);
            
            if(result == 0) {
                result = Integer.compare(scoresA.get("English"), scoresB.get("English"));
            }
            
            if(result == 0) {
                String nameA = (String) a[0];
                String nameB = (String) b[0];
                
                result = nameA.compareTo(nameB);
            }
            
            return result;
        });
        
        System.out.println(Arrays.deepToString(data));
    }
}
