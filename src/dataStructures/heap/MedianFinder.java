package dataStructures.heap;

import java.util.PriorityQueue;

/**
 * Find Median from Data Stream - 設計一個數據流類別
 * 
 * 功能：
 * addNum(int num)：向數據流添加一個數字
 * findMedian()：快速查找目前所有數字的中位數
 *
 * 思路：
 * 使用兩個堆（最大堆 + 最小堆）來維護數據的左右兩邊
 *   maxHeap 保存「較小一半」的數字（堆頂是最大的小數）
 *   minHeap 保存「較大一半」的數字（堆頂是最小的大數）
 * 因為要快速取中位數，所以：
 *   最大堆存左半邊，最小堆存右半邊
 *   保持兩個堆大小平衡（最多差 1）
 *   這樣可以直接從堆頂 poll() / peek() 拿到中間值
 * 中位數查詢：
 *   如果總數為奇數，取 maxHeap 堆頂
 *   如果總數為偶數，取 maxHeap 和 minHeap 堆頂的平均值
 * 
 * 因為要快速取中位數，所以最大堆要存 左半邊 最小堆要存右半邊，這樣直接poll() 就會是中間
 */
public class MedianFinder {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    
    public MedianFinder() {
        // new PriorityQueue<>(Comparator.comparingInt(a -> -a));
        maxHeap = new PriorityQueue<>(((a, b) -> b - a));
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        // 先加到正確的堆
        if(maxHeap.size() == 0 || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        }else {
            minHeap.offer(num);
        }
        
        // 維持兩堆的大小平衡
        // 大的往 右半邊 最小堆放
        // 小的往 左半邊 最大堆放
        if(maxHeap.size() > minHeap.size() + 1) {
            int moved = maxHeap.poll();
            minHeap.offer(moved);
        }else if(minHeap.size() > maxHeap.size()) {
            int moved = minHeap.poll();
            maxHeap.offer(moved);
        }
    }
    
    public double findMedian() {
        
        if(maxHeap.size() == minHeap.size()) {
            // 這邊要使用 2.0 才會保留小數
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        
        // 奇數個數字時，maxHeap 會多一個元素
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        
    }
    
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
    }
}
