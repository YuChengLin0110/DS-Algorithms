package leetcodePremium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to find the number of connected components in an undirected graph.

Example 1:

     0        3

     |        |

     1 — 2    4

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:

     0       4

     |       |

     1 — 2 — 3

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

 Note:

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

 * */
public class NumberofConnectedComponentsinanUndirectedGraph_323 {
    /*
     * 從任意未訪問節點開始 DFS，遍歷它所有的 可互相到達的節點群
     * 每完成一次 DFS，就計數 1
     * 再從剩下未訪問的節點中挑一個開始新的 DFS，找到新的 可互相到達的節點群
     * 直到所有節點都被訪問
     * */
    
    public int countComponents(int n, int[][] edges) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int count = 0;
        
        for(int i = 0 ; i < n ; i++) {
            adjList.put(i, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        // 遍歷每個節點
        for(int i = 0 ; i < n ; i++) {
            if(!visited.contains(i)) { // 如果節點還沒被訪問過，表示找到一個新的 可互相到達的節點群
                dfs(i, adjList, visited);
                count++; // 每完成一次 DFS 就算找到一個新的群組
            }
        }
        
        return count;
    }
    
    private void dfs(int node, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        visited.add(node);
        for(int nei : adjList.get(node)) {
            if(!visited.contains(nei)) {
                dfs(nei, adjList, visited);   
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {3, 4}
        };
        
        NumberofConnectedComponentsinanUndirectedGraph_323 solution = new NumberofConnectedComponentsinanUndirectedGraph_323();
        System.out.println(solution.countComponents(n, edges));

    }

}
