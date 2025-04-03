/**
 * Problem: Same Tree
 * 
 * Given the roots of two binary trees p and q, write a function to check if they are
 * the same or not. Two binary trees are considered the same if they are structurally
 * identical, and the nodes have the same value.
 * 
 * Example:
 *     1         1
 *    / \       / \
 *   2   3     2   3
 * 
 * Output: true
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(h) where h is the height of the tree
 */

public class SameTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both trees are null, they are the same
        if (p == null && q == null) return true;
        
        // If one tree is null and the other isn't, they are different
        if (p == null || q == null) return false;
        
        // Check if current nodes have same value and their subtrees are the same
        return (p.val == q.val)
            && isSameTree(p.left, q.left)
            && isSameTree(p.right, q.right);
    }
    
    public static void main(String[] args) {
        SameTree solution = new SameTree();
        
        // Test Case 1: Same Trees
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);
        
        System.out.println("Test Case 1: " + solution.isSameTree(p1, q1)); // Should print true
        
        // Test Case 2: Different Trees
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        
        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);
        
        System.out.println("Test Case 2: " + solution.isSameTree(p2, q2)); // Should print false
    }
}