package dataStructures.trie;

public class MyTrie {
    private TrieNode root;

    public MyTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            
            // 如果當前節點的子節點沒有這個字，就新增一個
            node.children.putIfAbsent(c, new TrieNode());

            node = node.children.get(c);
        }

        node.isEnd = true;
    }

    public boolean contains(String word) {
        TrieNode node = searchNode(word);

        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
        
    }
    
    public boolean delete(String word) {
        if(!contains(word)) return false;
        
        return deleteHelper(root, word, 0) ;
    }
    

    private TrieNode searchNode(String str) {
        TrieNode node = root;

        for (char c : str.toCharArray()) {
            if (node.children.get(c) == null) return null;

            node = node.children.get(c);
        }

        return node;
    }
    
    /**
     * 刪除操作分兩部分
     * 將單字的結尾標記 isEnd 改為 false ，表示這個字不再存在
     * 若該節點沒有其他子節點，也不是其他單字的結尾，就可以移除該節點
     * */
    private boolean deleteHelper(TrieNode curr, String word, int index) {
        char c = word.charAt(index);
        
        TrieNode childNode = curr.children.get(c);
        
        // 如果找不到對應子節點，表示字串不存在
        if(childNode == null) return false;
        
        // 若已到字串的最後一個字元，確認這是完整單字還是前綴
        if(index == word.length() - 1) {
            
            if(!childNode.isEnd) return false; // 該節點不是一個完整單字的結尾，表示只是其他字的前綴，無法刪除
            
            childNode.isEnd = false; // 如果是完整單字，將結尾標記取消，代表這個單字已被移除
            
            return childNode.children.isEmpty(); // 若該節點沒有子節點，回傳 true 表示可以刪除
        }
        
        // 繼續處理下一個字元，看能不移除下一個子節點
        boolean shouldDeleteChilNode = deleteHelper(childNode, word, index + 1);
        
        // 如果子節點可以移除，就將其移除，並檢查目前節點是否也能移除
        if(shouldDeleteChilNode) {
            curr.children.remove(c);
            
            // 如果當前節點沒有其他子節點，也不是其他字的結尾，就可以移除該節點
            return curr.children.isEmpty() && !curr.isEnd;
        }
        
        // 遞迴最後回到 root 節點時，表示整個刪除過程已經結束
        // 這裡需要確認單字是否已經成功刪除，使用 contains() 來檢查
        // 中途的 return 判斷是用來處理每一層節點的刪除邏輯
        // 而這裡的 return 才是最終回傳給 delete() 方法的結果
        // 所以必須確保只有當 word 完全移除後才回傳 true
        if(curr == root) return !contains(word);
        
        return false;
    }
    
    public static void main(String[] args) {
        MyTrie trie = new MyTrie();

        trie.insert("apple");
        trie.insert("app");
        trie.insert("bat");
        trie.insert("bath");

        System.out.println("測試 contains()");
        System.out.println(trie.contains("apple"));
        System.out.println(trie.contains("app"));

        System.out.println("測試 startWith()");
        System.out.println(trie.startsWith("ap"));
        System.out.println(trie.startsWith("bat"));
        System.out.println(trie.startsWith("ban"));

        System.out.println("測試 delete()");
        System.out.println(trie.delete("apple"));
        System.out.println(trie.delete("bat"));
        System.out.println(trie.delete("bath"));
    }
}
