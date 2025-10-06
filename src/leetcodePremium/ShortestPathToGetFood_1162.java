package leetcodePremium;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 LeetCode 1162 — Shortest Path to Get Food

You are starving and you want to eat food as quickly as possible. You are given an m x n character matrix grid representing a map of the area:

'*' is your location. There is exactly one '*' cell.

'#' is a food cell. There may be multiple food cells.

'O' is free space, and you can travel through these cells.

'X' is an obstacle, and you cannot travel through these cells.

You can move up, down, left, or right from and to an adjacent cell.

Return the length of the shortest path for you to reach any food cell. If there is no path to any food, return -1.

Example:

Input: grid = [["X","X","X","X","X","X"],
               ["X","*","O","O","O","X"],
               ["X","O","O","#","O","X"],
               ["X","X","X","X","X","X"]]
Output: 3

Constraints:

m == grid.length

n == grid[i].length

1 <= m, n <= 200

grid[i][j] is '*', '#', 'O', or 'X'.

There is exactly one '*' in grid.
 * */
public class ShortestPathToGetFood_1162 {
    /*
     * BFS 從 * 開始去找最短路徑
     * 走過就標記成 X 不再走一次
     * */

    public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int step = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    queue.offerLast(new int[] { i, j });
                    break;
                }
            }
        }

        int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.pollFirst();
                int r = curr[0];
                int c = curr[1];
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (inGrid(grid, nr, nc)) {
                        if (grid[nr][nc] == '#') {
                            return step + 1;
                        } else if (grid[nr][nc] == 'O') {
                            queue.offerLast(new int[] { nr, nc });
                            grid[nr][nc] = 'X';
                        }
                    }
                }
            }
            
            step++;
        }

        return -1;
    }

    private boolean inGrid(char[][] grid, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X' }, { 'X', '*', 'O', 'O', 'O', 'X' },
                { 'X', 'O', 'O', '#', 'O', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X' } };

        ShortestPathToGetFood_1162 solution = new ShortestPathToGetFood_1162();
        System.out.println(solution.getFood(grid));
    }
}
