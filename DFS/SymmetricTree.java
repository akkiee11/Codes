/**
 * Problem: Symmetric Tree
 * 
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * 
 * Example:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 
 * Output: true
 * 
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree
 */

public class SymmetricTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode left, TreeNode right) {
        // If both nodes are null, they are symmetric
        if (left == null && right == null) return true;
        
        // If only one node is null, they are not symmetric
        if (left == null || right == null) return false;
        
        // Check if values are equal and their subtrees are mirrors
        return (left.val == right.val)
            && isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }
    
    public static void main(String[] args) {
        SymmetricTree solution = new SymmetricTree();
        
        // Test Case 1: Symmetric Tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);
        
        System.out.println("Test Case 1: " + solution.isSymmetric(root1)); // Should print true
        
        // Test Case 2: Non-symmetric Tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        
        System.out.println("Test Case 2: " + solution.isSymmetric(root2)); // Should print false
    }
}