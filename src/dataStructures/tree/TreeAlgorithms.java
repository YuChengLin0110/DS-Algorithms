package dataStructures.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeAlgorithms {
	
	public static int maxDepth(MyTree root) {
		if(root == null) {
			return 0;
		}
		
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		return Math.max(leftDepth, rightDepth) + 1;
	}
	
	// 根到最近的葉節點
	public static int minDepth(MyTree root) {
		if(root == null) {
			return 0;
		}
		
		// 如果左子樹為 null，表示右子樹可能存在，此節點可能不是葉節點
		// 需要計算右子樹的最小深度並加 1
		// 避免直接取 min 會錯誤計算成 0
		// 如果兩邊都是 null 那最後就是 0 + 1
		if(root.left == null) {
			return minDepth(root.right) + 1;
		}
		
		// 如果右子樹為 null，表示左子樹可能存在，此節點可能不是葉節點
		// 需要計算左子樹的最小深度並加 1
		// 避免直接取 min 會錯誤計算成 0
		// 如果兩邊都是 null 那最後就是 0 + 1
		if(root.right == null) {
			return minDepth(root.left) + 1;
		}
		
		
		
		int leftDepth = minDepth(root.left);
		int rightDepth = minDepth(root.right);
		
		return Math.min(leftDepth, rightDepth) + 1;
	}
	
	public static int findDepth(MyTree root, int target) {
		return findDepthHelper(root, target, 0);
	}
	
	public static boolean isSameTree(MyTree a, MyTree b) {
		if(a == null && b == null) {
			return true;
		}
		
		if(a == null || b == null || a.val != b.val) {
			return false;
		}
		
		return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
	}
	
	public static boolean isSymmetric(MyTree root) {
		if(root == null) {
			return true;
		}
		
		return isMirror(root.left, root.right);
	}
	
	// 是否存在 從 根節點 到 葉節點 的路徑，該路徑上的節點值總和等於 targetSum
	public static boolean hasPathSum(MyTree root, int targetSum) {
		if(root == null) {
			return false;
		}
		
		if(root.left == null && root.right == null) {
			return targetSum == root.val;
		}
		
		int current = targetSum - root.val;
		
		return hasPathSum(root.left, current) || hasPathSum(root.right, current);
	}
	
	// 找出所有從根節點到葉節點的路徑中，節點總和等於 targetSum 的所有路徑
	public static List<List<Integer>> pathSum(MyTree root, int targetSum) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		
		pathSumHelper(root, result, path, targetSum);
		
		return result;
	}
	
	/**
	 * 如果 a 和 b 分別在左右子樹，這個 root 就是 LCA
	 * 如果 a 或 b 其中之一就是 root ，這個 root 就是 LCA
	 **/
	public static MyTree lowestCommonAncestor(MyTree root, MyTree a, MyTree b) {
		
		// root == null 表示到底了，沒找到
		// root == a || root == b 表示有找到目標之一
		if(root == null || root == a || root == b) {
			return root;
		}
		
		MyTree left = lowestCommonAncestor(root.left, a, b);
		MyTree right = lowestCommonAncestor(root.right, a, b);
		
		if(left != null && right != null) {
			return root;
		}
		
		return left != null ? left : right;
	}
	
	private static int findDepthHelper(MyTree node, int target, int depth) {
		if(node == null) {
			return -1;
		}
		
		if(node.val == target) {
			return depth;
		}
		
		// 先往左找
		int leftDepth = findDepthHelper(node.left, target, depth + 1);
		
		if(leftDepth != -1) {
			return leftDepth;
		}
		
		// 左邊沒有，再往右找
		return findDepthHelper(node.right, target, depth + 1);
	}
	
	private static boolean isMirror(MyTree left, MyTree right) {
		if(left == null && right == null) {
			return true;
		}
		
		if(left == null || right == null) {
			return false;
		}
		
		if(left.val != right.val ) {
			return false;
		}
		
		return isMirror(left.left, right.right) && isMirror(left.right, right.left);
	}
	
	private static void pathSumHelper(MyTree node, List<List<Integer>> result, List<Integer> path, int targetSum) {
		if(node == null) {
			return;
		}
		
		path.add(node.val);
		int curr = targetSum - node.val;
		
		// 遇到葉節點且目標總和為 0 就加入 result
		if(node.left == null && node.right == null && curr == 0) {
			result.add(new ArrayList<>(path));
		}else {
			pathSumHelper(node.left, result, path, curr);
			pathSumHelper(node.right, result, path, curr);
		}
		
		// 回到上一層時，移除這層加入的節點
		path.remove(path.size() - 1);
	}
}
