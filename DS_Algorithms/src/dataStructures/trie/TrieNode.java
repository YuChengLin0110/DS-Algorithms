package dataStructures.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character ,TrieNode> children; // 子節點， key 是當前字符，value 是對應字符的下一個 TrieNode
    boolean isEnd; // 某個單字結尾標記
    
    public TrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}
