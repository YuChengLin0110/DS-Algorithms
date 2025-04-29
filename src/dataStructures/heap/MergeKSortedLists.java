package dataStructures.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合併 K 個已排序的鏈表 (Merge K Sorted Lists)
 * 給定 k 個排序好的鏈表，每個鏈表的元素都是升序排列的。請將所有鏈表合併成一個排序好的鏈表
 * 返回合併後的排序鏈表
 * 輸入：
 *  [
      1 -> 4 -> 5,
      1 -> 3 -> 4,
      2 -> 6
    ]
   
   輸出：1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
    
 * */
public class MergeKSortedLists {
    
    // 定義鏈表節點
    class ListNode{
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        } 
    }
    
    public ListNode mergeKSortedLists(List<ListNode> list) {
        // 最小堆，堆內的元素會按照節點的值升序排列
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(listNode -> listNode.val));
        
        // 將所有鏈表的頭節點放入堆中
        for(ListNode node : list) {
            if(node != null) {
                heap.offer(node);
            }
        }
        
        // 建立一個虛擬頭節點 dummyHead ，來構造鏈表
        ListNode dummyHead = new ListNode(0);
        // curr 用來追蹤當前合併位置
        ListNode curr = dummyHead;
        
        while(!heap.isEmpty()) {
            // 從堆中取出最小的節點
            ListNode node = heap.poll();
            // 把最小節點接到結果鏈表的尾部
            curr.next = node;
            curr = curr.next;
            
            // 如果該節點還有下一個節點，將下一個節點放入堆中
            if(node.next != null) {
                heap.offer(node.next);
            }
        }
        
        // dummyHead 是虛擬頭節點，所以要返回它的下一個節點開始
        return dummyHead.next;
    }
    
    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();
        
        ListNode l1 = solution.new ListNode(1);
        l1.next = solution.new ListNode(4);
        l1.next.next = solution.new ListNode(5);
        
        ListNode l2 = solution.new ListNode(1);
        l2.next = solution.new ListNode(3);
        l2.next.next = solution.new ListNode(4);
        
        ListNode l3 = solution.new ListNode(2);
        l3.next = solution.new ListNode(6);
        
        List<ListNode> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        
        ListNode result = solution.mergeKSortedLists(lists);
        
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
