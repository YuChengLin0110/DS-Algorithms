package leetcodePremium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, 
then one square in an orthogonal direction.

Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.

y
 2 | .  X  .  X  .
 1 | X  .  .  .  X
 0 | .  .  S  .  .
-1 | X  .  .  .  X
-2 | .  X  .  X  .
    -2 -1  0  1  2  x
    
S 為 騎士位置 ， X 為 可移動到的點

Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:

|x| + |y| <= 300 

 * */
public class MinimumKnightMoves_1197 {
    /*
     * bidirectional BFS 雙向 BFS
     * 比一般 BFS 更快
     * */
public int minKnightMoves(int x, int y) {
    // 利用棋盤對稱性，把範圍限制在右上 -> 距離只和絕對值有關
    x = Math.abs(x);
    y = Math.abs(y);
    
    // visited 用 String 表示 (r,c)，因為 int[] 沒有覆寫 equals 和 hashCode ， 不能 contains
    Set<String> visitedStart = new HashSet<>();
    Set<String> visitedEnd = new HashSet<>();
    Deque<int[]> queueStart = new ArrayDeque<>();
    Deque<int[]> queueEnd = new ArrayDeque<>();
    int step = 0;
    
    queueStart.offerLast(new int[] {0, 0});
    queueEnd.offerLast(new int[] {x, y});
    visitedStart.add(0 + "," + 0);
    visitedEnd.add(x + "," + y);
    
    // 騎士八個可能的 L 形移動方向
    int[][] dirs = new int[][] {{-1, 2},{1, 2},{-1, -2},{1, -2},{-2, 1},{-2, -1},{2, 1},{2, -1}};
    
    while(!queueStart.isEmpty() && !queueEnd.isEmpty()) {
        // 每次選擇節點較少的一邊 BFS 擴展
        if(queueStart.size() > queueEnd.size()) {
            Deque<int[]> temp = queueStart;
            queueStart = queueEnd;
            queueEnd = temp;
        }
        
        int size = queueStart.size();
        for(int i = 0 ; i < size ; i++) {
            int[] curr = queueStart.pollFirst();
            int r = curr[0];
            int c = curr[1];
            
            for(int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                String pos = nr + "," + nc;
                
                // 只搜索第一象限，利用對稱性
                if(nr < 0 || nc < 0) {
                    continue;
                }
                
                if(!visitedStart.contains(pos)) { 
                    queueStart.offerLast(new int[] {nr, nc});
                    visitedStart.add(pos);
                }
                
                if(visitedEnd.contains(pos)) { // 如果兩邊 BFS 相遇 → 找到最短步數
                    return step + 1; // 下一步才會遇到 所以 +1
                }
            }
        }
        
        step++;
    }
    
    return -1;
        
    }

    public static void main(String[] args) {
        int x = 5;
        int y = 5;
        MinimumKnightMoves_1197 solution = new MinimumKnightMoves_1197();
        System.out.println(solution.minKnightMoves(x, y));

    }

}
