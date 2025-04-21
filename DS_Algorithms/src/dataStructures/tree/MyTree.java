package dataStructures.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MyTree {
	int val;
	MyTree left;
	MyTree right;

	public MyTree() {
	}

	public MyTree(int val) {
		this.val = val;
	}

	public MyTree(int val, MyTree left, MyTree right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return levelOrderToString(this);
	}
	
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
	
	public int height() {
		int leftHeight = left != null ? left.height() : 0;
		int rightHeight = right != null ? right.height() : 0;
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	// 中左右
	public List<Integer> preOrder(){
		List<Integer> result = new ArrayList<>();
		preOrderHelper(this, result);
		
		return result;
	}
	
	// 左中右
	public List<Integer> inOrder(){
		List<Integer> result = new ArrayList<>();
		inOrderHelper(this, result);
		
		return result;
	}
	
	// 左右中
	public List<Integer> postOrder(){
		List<Integer> result = new ArrayList<>();
		postOrderHelper(this, result);
		
		return result;
	}
	
	// 層序
	public List<Integer> levelOrder(){
		List<Integer> result = new ArrayList<>();
		Deque<MyTree> queue = new ArrayDeque<>();
		queue.offerLast(this);
		
		while(!queue.isEmpty()) {
			MyTree node = queue.pollFirst();
			
			if(node != null) {
				result.add(node.val);
				
				if(node.left != null) {
					queue.offerLast(node.left);
				}
				
				if(node.right != null) {
					queue.offerLast(node.right);
				}
			}
		}
		
		return result;
	}
	
	private void preOrderHelper(MyTree node, List<Integer> result) {
		if(node == null) {
			return;
		}
		
		result.add(node.val);
		preOrderHelper(node.left, result);
		preOrderHelper(node.right, result);
	}
	
	private void inOrderHelper(MyTree node, List<Integer> result) {
		if(node == null) {
			return;
		}
		
		inOrderHelper(node.left, result);
		result.add(node.val);
		inOrderHelper(node.right, result);
	}
	
	private void postOrderHelper(MyTree node, List<Integer> result) {
		if(node == null) {
			return;
		}
		
		postOrderHelper(node.left, result);
		postOrderHelper(node.right, result);
		result.add(node.val);
	}

	private String levelOrderToString(MyTree root) {
		if (root == null) {
			return "null";
		}
			
		StringBuilder sb = new StringBuilder();
		Deque<MyTree> queue = new LinkedList<>();
		queue.offerLast(root);

		while (!queue.isEmpty()) {
			MyTree node = queue.pollFirst();

			if (node != null) {
				sb.append(node.val).append(", ");
				queue.offerLast(node.left);
				queue.offerLast(node.right);
			} else {
				sb.append("null").append(", ");
			}
		}

		while (sb.length() >= 6 && sb.substring(sb.length() - 6).equals("null, ")) {
			sb.setLength(sb.length() - 6);
		}

		if (sb.length() > 2 && sb.substring(sb.length() - 2).equals(", ")) {
			sb.setLength(sb.length() - 2);
		}

		return sb.toString();
	}
	
	// 層序遍歷建構樹
	public static MyTree buildTreeFromLevelOrder(Integer[] values) {
		if (values == null || values.length == 0)
			return null;

		MyTree root = new MyTree(values[0]);
		Deque<MyTree> queue = new LinkedList<>();
		queue.offerLast(root);
		int i = 1;

		while (!queue.isEmpty() && i < values.length) {
			MyTree node = queue.pollFirst();

			if (values[i] != null) {
				node.left = new MyTree(values[i]);
				queue.offerLast(node.left);
			}

			i++;

			if (i < values.length && values[i] != null) {
				node.right = new MyTree(values[i]);
				queue.offerLast(node.right);
			}

			i++;
		}
		return root;
	}
}
