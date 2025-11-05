package comparator.basic;

import java.util.Arrays;

public class SortIntegers {

    public static void main(String[] args) {
        int[] nums = {5, 2, 9, 1, 3};
        
        // 升序 (primitive array 內建升序)
        // primitive array Arrays.sort() 預設 升序
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        
        // 降序 (需要 Comparator -> 先裝箱成 Integer[])
        // primitive array Arrays.sort() 不能接受 Comparator
        // 要先裝箱 才能用 Lambda Comparator
        Integer[] numsBoxed = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsBoxed[i] = nums[i];
        }
        
        // 自訂 Comparator 降序
        Arrays.sort(numsBoxed, (a, b) -> Integer.compare(b, a));
        System.out.println(Arrays.toString(numsBoxed));
    }
}
