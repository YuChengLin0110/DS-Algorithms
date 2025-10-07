package leetcodePremium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Problem:
You have a graph of n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes). 
Write a function to check whether these edges make up a valid tree.

Example 1:
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false

Note:

You can assume that no duplicate edges will appear in edges.

Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 * */
public class GraphValidTree_261 {
    
    /*
     * Tree的定義
     * 1. n 節點的樹，必定只有 n - 1 條邊 (這也隱含了無環，但還是需要另外判斷是否無環)
     * 2. 連通 : 所有節點可以互相到達
     * 3. 無環
     * */
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) { // // 邊數不符 -> 不可能是樹
            return false;
        }
        
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        
        for(int i = 0 ; i < n ; i++) {
            adjList.put(i, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        visited.add(0);
        
        if(!dfs(0, -1, adjList, visited)) {
            return false;
        }
        
        return visited.size() == n; // 全部節點都有遍歷到 -> 連通
    }
    
    private boolean dfs(int node, int parent, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        visited.add(node);
        
        for(int nei : adjList.get(node)) {
            if(!visited.contains(nei)) {
                if(!dfs(nei, node, adjList, visited)) {
                    return false;
                }
            }else if(nei != parent) { // 遇到已訪問過的節點，且此節點不是當前節點的相連節點(parent)，表示有環
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        int[][] edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4}};
        int n = 5;
        
        GraphValidTree_261 solution = new GraphValidTree_261();
        System.out.println(solution.validTree(n, edges));

    }

}
