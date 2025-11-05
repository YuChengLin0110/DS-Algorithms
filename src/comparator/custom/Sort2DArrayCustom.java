package comparator.custom;

import java.util.Arrays;

public class Sort2DArrayCustom {
    /*
     * 先按第一欄升序
     * 
     * 如果第一欄相同 → 按第二欄降序
     */
    public static void main(String[] args) {
        int[][] arr = { { 3, 5 }, { 1, 9 }, { 3, 2 }, { 1, 2 }, { 2, 7 } };
        
        // int[] nums -> nums[i] 是 int 基本型別 -> 不能直接用 Comparator
        // int[][] arr -> arr[i] 是 int[] 物件 -> 可以傳 Comparator 比較元素
        // 所以 二維陣列 不需要裝箱
        Arrays.sort(arr, (a, b) -> {
            int result = Integer.compare(a[0], b[0]);
            
            if(result == 0) {
                return Integer.compare(a[1], b[1]);
            }
            
            return result;
        });
        
        System.out.println(Arrays.deepToString(arr));
    }
}
