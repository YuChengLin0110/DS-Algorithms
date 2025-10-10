package leetcodePremium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 Derive the order of letters in this language.

Example 1:
Input: ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:
Input: ["z","x"]
Output: "zx"

Example 3:
Input: ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
 * */
public class AlienDictionary_269 {
    /*
     * 先依序從字串找出第一個不同的字母，判斷順序
     * "wrt","wrf" -> t < f -> 建圖 t 指向 f
     * 
     * Topological Sort BFS
     * 依序構建答案
     * */
    
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adjList = new HashMap<>(); // value 用 set 比較安全，可以避免有重複的邊的情況
        Map<Character, Integer> indegree = new HashMap<>();
        
        for(String word : words) {
            for(char c : word.toCharArray()) {
                adjList.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }
        
        // 建圖，只看相鄰單字的第一個不同字母
        for(int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());
            
            for(int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if(c1 != c2) {
                    adjList.get(c1).add(c2);
                    indegree.put(c2, indegree.get(c2) + 1);
                    break; // 只看第一個不同字母 -> 找到第一個不同就 break
                }
            }
        }
        
        Deque<Character> queue = new ArrayDeque<>();
        for(char c : indegree.keySet()) {
            if(indegree.get(c) == 0) {
                queue.offer(c);   
            }
        }
        
        StringBuilder res = new StringBuilder();
        while(!queue.isEmpty()) {
            char c = queue.pollFirst();
            res.append(c);
            for(char nei : adjList.get(c)) {
                indegree.put(nei, indegree.get(nei) - 1);
                if(indegree.get(nei) == 0) {
                    queue.offer(nei);   
                }
            }
        }
        
        // 如果結果長度 != 字母數，表示有 cycle
        if(res.length() != indegree.size()) return ""; // 拓樸都要記得判斷環
        
        return res.toString();
    }

    public static void main(String[] args) {
        AlienDictionary_269 solution = new AlienDictionary_269();
        String[] words = new String[] {"wrt","wrf","er","ett","rftt"};
        System.out.println(solution.alienOrder(words));
    }
}
