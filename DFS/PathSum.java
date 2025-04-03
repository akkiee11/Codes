/**
 * Problem: Path Sum
 * 
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a 
 * root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 * 
 * Example:
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * 
 * targetSum = 22
 * Output: true (5 + 4 + 11 + 2 = 22)
 * 
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree
 */

public class PathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: empty tree
        if (root == null) return false;
        
        // If it's a leaf node, check if the remaining sum equals the node's value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // Recursively check left and right subtrees with reduced target sum
        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }
    
    public static void main(String[] args) {
        PathSum solution = new PathSum();
        
        // Test Case 1: Path exists
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.right.right = new TreeNode(1);
        
        System.out.println("Test Case 1: " + solution.hasPathSum(root1, 22)); // Should print true
        
        // Test Case 2: No path exists
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        System.out.println("Test Case 2: " + solution.hasPathSum(root2, 5)); // Should print false
    }
}