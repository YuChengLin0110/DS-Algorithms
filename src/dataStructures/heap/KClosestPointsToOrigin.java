package dataStructures.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * K Closest Points to Origin
 * 給定一個點的集合 points，每個點是 [x, y]
 * 要找出距離原點 (0, 0) 最近的 k 個點
 * */
public class KClosestPointsToOrigin {
    
    public static int[][] kClosestPointsToOrigin(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> distance(b) - distance(a));
        int[][] result = new int[k][2];
        
        for(int[] point : points) {
            if(heap.size() < k) {
                heap.offer(point);
            }else if(distance(point) < distance(heap.peek())) {
                heap.poll(); // 堆滿了而且新點比較近，踢掉最遠的
                heap.offer(point);
            }
        }
        
        for(int i = 0 ; i < k ; i++) {
            int[] curr = heap.poll();
            result[i][0] = curr[0];
            result[i][1] = curr[1];
        }
        
        return result;
    }
    
    // 計算從原點出發的平方距離
    private static int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
    public static void main(String[] args) {
        int[][] points = { {1, 3}, {-2, 2}, {2, -2} };
        int k = 2;

        int[][] result = kClosestPointsToOrigin(points, k);

        for (int[] p : result) {
            System.out.println(Arrays.toString(p));
        }
    }
}
