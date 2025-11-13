package leetcodePremium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.

List<String> ls(String path)
   * If path is a file path, returns a list that only contains this file’s name.
   * If path is a directory path, returns the list of file and directory names in this directory. The answer should be in lexicographic order.

void mkdir(String path) Makes a new directory given by the path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.

void addContentToFile(String filePath, String content)
   * If filePath does not exist, creates a file containing given content.
   * If filePath already exists, append the given content to original content.

String readContentFromFile(String filePath) Returns the content in the file at filePath.

Example 1:
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation:
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // returns []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // returns ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // returns "hello"

Constraints:

1 <= path.length, filePath.length <= 100

path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".

You can assume that all directory names and file names only contain lowercase letters, and the same names won’t exist in the same directory.

You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file content or list a directory or file that does not exist.

You can assume that the parent directory for the file in addContentToFile will exist.

1 <= content.length <= 50

At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 * */

/*
 * 題目說 You can assume that all operations will be passed valid parameters, 
 * and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * ls 或 addContentToFile 操作時 dir 一定存在
 * */
class FileSystem {
    private Node root;
    
    public FileSystem() {
        root = new Node();
    }

    public List<String> ls(String path) {
        String[] parts = path.split("/");
        Node node = root;
        
        for(String part : parts) {
            if(part.isEmpty()) { // "/a/b/c" split("/") 會有空字串，這邊需要判斷
                continue;
            }
            
            node = node.children.get(part);
        }
        
        if(node.isFile) { // 是檔案，return 檔名
            return Arrays.asList(parts[parts.length - 1]);
        }
        
        List<String> list = new ArrayList<>(node.children.keySet());
        list.sort((a, b) -> a.compareTo(b)); // 如果 children 用 TreeMap 就會自動排序
        
        return list;
    }

    public void mkdir(String path) {
        String[] parts = path.split("/");
        Node node = root;
        
        for(String part : parts) {
            if(part.isEmpty()) { // "/a/b/c" split("/") 會有空字串，這邊需要判斷
                continue;
            }
            
            node.children.putIfAbsent(part, new Node());
            node = node.children.get(part);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] parts = filePath.split("/");
        Node node = root;
        
        for(String part : parts) {
            if(part.isEmpty()) { // "/a/b/c" split("/") 會有空字串，這邊需要判斷
                continue;
            }
            
            node.children.putIfAbsent(part, new Node());
            node = node.children.get(part);
        }
        
        node.isFile = true;
        node.content += content; // 如果檔案存在，就 append content
    }

    public String readContentFromFile(String filePath) {
        String[] parts = filePath.split("/");
        Node node = root;

        for(String part : parts) {
            if(part.isEmpty()) { // "/a/b/c" split("/") 會有空字串，這邊需要判斷
                continue;
            }
            
            node = node.children.get(part);
        }
        
        return node.content;
    }
}

class Node {
    boolean isFile; // 是不是檔案
    String content; // 檔案內容
    Map<String, Node> children;
    
    public Node() {
        isFile = false;
        content = "";
        children = new HashMap<>();
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

public class DesignInMemoryFileSystem_588 {

    public static void main(String[] args) {
        FileSystem system = new FileSystem();
        
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.ls("/"));                         // returns []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fileSystem.ls("/"));                         // returns ["a"]
        System.out.println(fileSystem.readContentFromFile("/a/b/c/d")); // returns "hello"
    }

}
