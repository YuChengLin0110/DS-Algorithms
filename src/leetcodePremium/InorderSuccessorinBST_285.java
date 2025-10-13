package leetcodePremium;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * You are given a binary search tree (BST) and a node p in it.
Find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

Return the in-order successor of the given node p. If there is no successor, return null.

Example 1
Input:
root = [2,1,3], p = 1

Output:
2

Explanation:

Given a binary search tree:  
    2
   / \
  1   3
  
The inorder traversal of the BST is [1, 2, 3]. The successor of node 1 is node 2.

Example 2
Input:
root = [5,3,6,2,4,null,null,1], p = 6

Output:
null

Explanation:
Given a binary search tree:
        5
       / \
      3   6
     / \   
    2   4
   /
  1
  
The inorder traversal of the BST is [1, 2, 3, 4, 5, 6]. Since node 6 has no right child, there is no successor.

Constraints

The number of nodes in the tree is in the range [1, 10^5].

-10^5 <= Node.val <= 10^5

All nodes have unique values.

p is a node in the BST and p != null.

 * */
public class InorderSuccessorinBST_285 {
//    TreeNode sucessor = null; 中序遍歷使用
//    TreeNode prev = null; 中序遍歷使用

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //inOrder(root, p); 中序遍歷使用
        TreeNode sucessor = null;
        TreeNode curr = root;
        
        // 利用 BST 的特性 不用遍歷整棵樹 -> O(H)  H : 樹高
        while(curr != null) {
            if(curr.val > p.val) { // 比 p 大
                sucessor = curr; // 可能的答案
                curr = curr.left; // 往左找 看有沒有更小的
            }else { // curr.val <= p.val
                curr = curr.right; // 往右找
            }
        }
        
        return sucessor;
    }
    
      // 中序遍歷解法 O(N)
//    private void inOrder(TreeNode node, TreeNode p) {
//        if (node == null || sucessor != null) {
//            return;
//        }
//        
//        inOrder(node.left, p);
//
//        if (prev == p) {
//            sucessor = node;
//            return;
//        }
//
//        prev = node;
//        inOrder(node.right, p);
//    }

    public static void main(String[] args) {
        InorderSuccessorinBST_285 solution = new InorderSuccessorinBST_285();
        Integer[] arr = new Integer[] { 2, 1, 3 };
        TreeNode root = TreeBuilder.buildTree(arr);
        TreeNode p = TreeNode.findNode(root, 1);
        System.out.println(solution.inorderSuccessor(root, p).toString());

    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return String.valueOf(val);
    }
    
    // 準備測資用 -> 找到目標節點 p 來當測資
    // 自己 new 會是不同物件
    public static TreeNode findNode(TreeNode root, int val) {
        if(root == null) {
            return null;
        }
        if(root.val == val) {
            return root;
        }
        
        TreeNode left = findNode(root.left, val);
        
        if(left != null) {
            return left;
        }
        
        return findNode(root.right, val);
    }
}

class TreeBuilder {
    // 層級遍歷來建樹
    public static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode curr = queue.pollFirst();

            if (arr[i] != null) {
                curr.left = new TreeNode(arr[i]);
                queue.offerLast(curr.left);
            }

            i++;
            if (i >= arr.length) {
                break;
            }

            if (arr[i] != null) {
                curr.right = new TreeNode(arr[i]);
                queue.offerLast(curr.right);
            }
            i++;

        }

        return root;
    }
}