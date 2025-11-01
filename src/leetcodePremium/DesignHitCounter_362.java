package leetcodePremium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * Design a hit counter which counts the number of hits received in the past 5 minutes. 
 * Each function accepts a timestamp parameter (in seconds granularity) 
 * and you may assume that calls are being made to the system 
 * in chronological order (ie, the timestamp is monotonically increasing). 
 * You may assume that the earliest timestamp starts at 1. 
 * It is possible that several hits arrive roughly at the same time. 
 * 
 * Example: HitCounter counter = new HitCounter(); 
 * // hit at timestamp 1. 
 *      counter.hit(1); 
 * 
 * // hit at timestamp 2. 
 *      counter.hit(2); 
 * 
 * // hit at timestamp 3. 
 *      counter.hit(3); 
 * 
 * // get hits at timestamp 4, should return 3. 
 *      counter.getHits(4); 
 *      
 * // hit at timestamp 300. 
 *      counter.hit(300); 
 *      
 * // get hits at timestamp 300, should return 4. 
 *      counter.getHits(300); 
 *      
 * // get hits at timestamp 301, should return 3. 
 *      counter.getHits(301); 
 * 
 * Follow up: 
 * What if the number of hits per second could be very large? 
 * Does your design scale? 
 * 
 * */

/* 題目要求：
 * 設計一個 HitCounter，計算過去 5 分鐘 (300 秒) 的 hits 次數
 * 每個函數都會接收 timestamp（秒為單位），且 timestamp 保證單調遞增
 * 
 * LeetCode 假設（重要）
 * 1. timestamp 單調遞增：
 *      you may assume that calls are being made to the system
 *      in chronological order (ie, the timestamp is monotonically increasing).
 *      每次 hit() 或 getHits() 的 timestamp ≥ 上一次呼叫的 timestamp
 *      getHits() 永遠不會早於最後一次 hit() 的時間
 * 
 * 2. 對 Basic / Follow-up 解法的影響
 *      queue / total 的延遲清理是安全的
 *      過期的 hits 永遠只會往前（小於 start），因此可以安全 poll 或減 total
 *      即使很久沒呼叫 getHits()，只要呼叫一次，滑動窗口仍正確
 * 
 * 
 * 三種情況的解法：
 * 
 * 1. Basic 基本解法（LeetCode 原題假設：時間單調遞增）
 * 
 * Queue<Integer> 存放每次 hit 的 timestamp
 * 
 * hit(timestamp)：加入 queue
 * 
 * getHits(timestamp)：
 *      計算 start = timestamp - 299
 *      將 queue 裡小於 start 的 timestamp poll 掉
 *      剩下的 queue.size() 就是最近 5 分鐘的 hits
 * 
 * 如果要查任意區間或不保證單調時間，queue.size() 方式不適用
 * 
 * */
class HitCounterBasic {
    Deque<Integer> queue;

    public HitCounterBasic() {
        queue = new ArrayDeque<>();
    }

    public void hit(int timestamp) {
        queue.offerLast(timestamp);
    }

    public int getHits(int timestamp) {
        int start = timestamp - 299;
        while (!queue.isEmpty() && queue.peekFirst() < start) {
            queue.pollFirst();
        }

        return queue.size();
    }
}

/*
 * 2. Follow-up：同一秒可能有大量 hits
 * 
 * Queue<int[]> -> [timestamp, hit count]
 * int total 保存總 hits
 * 
 * hit(timestamp)：
 *      如果 queue 最後一個元素的 timestamp 與當前相同，count++
 *      否則新增 int[]{timestamp, 1}
 *      total++
 * 
 * getHits(timestamp)：
 *      計算 start = timestamp - 299
 *      將 start 以前的元素 poll 出來，並從 total 減掉對應 count
 *      total 剩下的就是最近 5 分鐘的 hits
 *      
 * 支援高頻 hits，但仍不支援任意 timestamp 區間查詢
 * */
class HitCounterFollowUp {
    Deque<int[]> queue;
    int total;

    public HitCounterFollowUp() {
        queue = new ArrayDeque<>();
        total = 0;
    }

    public void hit(int timestamp) {
        if (!queue.isEmpty() && queue.peekLast()[0] == timestamp) {
            queue.peekLast()[1]++;
        } else {
            queue.offerLast(new int[] { timestamp, 1 });
        }

        total++;
    }

    public int getHits(int timestamp) {
        int start = timestamp - 299;
        while (!queue.isEmpty() && queue.peekFirst()[0] < start) {
            total -= queue.pollFirst()[1];
        }

        return total;
    }
}

/*
 * 3. 支援任意區間查詢（任意 start-end）
 * 
 * 使用 TreeMap 保存每秒的 hits 次數
 *      key = timestamp
 *      value = hit 次數
 * 
 * TreeMap 會自動對 key 排序（紅黑樹實作）
 * 利用 TreeMap 的 subMap(start, true, end, true) -> (起始 key, 是否包含 start, 結束 key, 是否包含 end)
 * 可以取得區間內的 hits，然後累加計算總數
 * 
 * TreeMap / SortedMap 特性：
 * TreeMap 是 SortedMap 的實作，key 自動排序
 * subMap 方法回傳的是 SortedMap 視圖（view）：
 *      遍歷或讀取 subMap 不會改變原本 TreeMap
 *      如果對 subMap 做 put/remove，會改變原本 TreeMap
 * */
class HitCounter {
    TreeMap<Integer, Integer> treeMap;

    public HitCounter() {
        treeMap = new TreeMap<>();
    }

    public void hit(int timestamp) {
        treeMap.put(timestamp, treeMap.getOrDefault(timestamp, 0) + 1);
    }

    public int getHits(int timestamp) {
        int total = 0;
        int start = timestamp - 299;

        SortedMap<Integer, Integer> sub = treeMap.subMap(start, true, timestamp, true);

        for (int count : sub.values()) {
            total += count;
        }

        return total;
    }
}

public class DesignHitCounter_362 {

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        System.out.println(counter.getHits(4));
        counter.hit(300);
        System.out.println(counter.getHits(300));
        System.out.println(counter.getHits(301));
    }

}
