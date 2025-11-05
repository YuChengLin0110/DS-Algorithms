package comparator.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortStudentsMapCustom {
    /*
     * { 
     * "Alice": {"Math": 90, "English": 85, "Science": 95}, 
     * "Bob": {"Math": 80, "English": 90, "Science": 88},
     * "Charlie": {"Math": 90, "English": 75, "Science": 92}
     *  }
     * 
     * 依總分降序 排序
     * 
     * 總分相同時，依 英文分數高者優先
     * 
     * 若仍相同，依姓名 字母序升序
     * 
     * 
     * Map 本身無序 無法被排序
     * 如果要排序
     *  map.entrySet() → 取得 Set<Map.Entry<K,V>>
     *  轉成 List<Map.Entry<K,V>>
     *  對 List 用 .sort()
     */
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> scores = new HashMap<>();

        scores.put("Alice", new HashMap<>(Map.of(
                "Math", 90,
                "English", 85,
                "Science", 95
        )));

        scores.put("Bob", new HashMap<>(Map.of(
                "Math", 80,
                "English", 90,
                "Science", 88
        )));

        scores.put("Charlie", new HashMap<>(Map.of(
                "Math", 90,
                "English", 75,
                "Science", 92
        )));
        
        List<Map.Entry<String, Map<String, Integer>>> list = new ArrayList<>(scores.entrySet());
        
        list.sort((a, b) -> {
            int sumA = 0;
//            int sumA = a.getValue().values().stream().mapToInt(Integer::intValue).sum();
            int sumB = 0;
//            int sumB = b.getValue().values().stream().mapToInt(Integer::intValue).sum();
            
            for(int score : a.getValue().values()) {
                sumA += score;
            }
            
            for(int score : b.getValue().values()) {
                sumB += score;
            }
            
            int result = Integer.compare(sumB, sumA);
            
            if(result == 0) {
                result = Integer.compare(a.getValue().get("English"), b.getValue().get("English"));
            }
            
            if(result == 0) {
                result = a.getKey().compareTo(b.getKey());
            }
            
            return result;
        });
        
        for(Map.Entry<String, Map<String, Integer>> entry : list) {
           System.out.println(entry.getKey() + "  " + entry.getValue());
        }
    }
}
