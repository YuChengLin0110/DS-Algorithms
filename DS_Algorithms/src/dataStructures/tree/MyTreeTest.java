package dataStructures.tree;

public class MyTreeTest {

	public static void main(String[] args) {
		Integer[] values = {1, 2, 3, null, 5, 6, 7};
		MyTree root = MyTree.buildTreeFromLevelOrder(values);

	    System.out.println("層序 : " + root);
	    System.out.println("height : " + root.height());
	    System.out.println("minDepth : " + TreeAlgorithms.minDepth(root));
	    System.out.println("maxDepth : " + TreeAlgorithms.maxDepth(root));
	    System.out.println("節點 5 的深度 : " + TreeAlgorithms.findDepth(root, 5));
	    System.out.println("root is leaf ? " + root.isLeaf());
	    System.out.println("root.left.right is leaf ? " + root.left.right.isLeaf());
	    
	    System.out.println("是否對稱 ? " + TreeAlgorithms.isSymmetric(root));

	    System.out.println("是否有路徑總和為 11 ? " + TreeAlgorithms.hasPathSum(root, 11));

	    System.out.println("總和為 11 的路徑 : " + TreeAlgorithms.pathSum(root, 11));

	    MyTree a = root.left; // 節點 2
	    MyTree b = root.right.right; // 節點 7
	    MyTree lca = TreeAlgorithms.lowestCommonAncestor(root, a, b);
	    System.out.println("節點 2 與 節點 7 的LCA : " + lca.val);
	}
}
