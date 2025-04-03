/**
 * Binary Tree Maximum Path Sum
 *
 * Problem: Find the maximum path sum in a binary tree. The path may start and end at any node
 * in the tree. A path is defined as any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections.
 *
 * Example:
 * Input: [1,2,3]
 *    1
 *   / \
 *  2   3
 * Output: 6 (Path: 2 -> 1 -> 3)
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *      / \
 *     15  7
 * Output: 42 (Path: 15 -> 20 -> 7)
 *
 * Approach:
 * 1. Use DFS to traverse the tree
 * 2. For each node, calculate:
 *    - Maximum path that can be formed using the node as the highest point (split path)
 *    - Maximum path that can be extended to parent (single path)
 * 3. Keep track of global maximum path sum
 * 4. Handle negative values carefully
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeMaxPathSum {
    private int maxSum;
    
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfs(root);
        return maxSum;
    }
    
    /**
     * Returns the maximum path sum starting from the given node and going down one path
     * (either left or right). Updates maxSum with the maximum path sum possible including
     * split paths.
     */
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        
        // Get maximum path sum from left and right subtrees
        // If path sum is negative, we don't include it (hence Math.max with 0)
        int leftGain = Math.max(dfs(node.left), 0);
        int rightGain = Math.max(dfs(node.right), 0);
        
        // Current node's value plus best paths from left and right
        // This represents a complete path through current node
        int currentPathSum = node.val + leftGain + rightGain;
        
        // Update global maximum if current path is better
        maxSum = Math.max(maxSum, currentPathSum);
        
        // Return maximum single path that can be extended to parent
        // We can only extend one path (either left or right) to parent
        return node.val + Math.max(leftGain, rightGain);
    }
    
    public static void main(String[] args) {
        BinaryTreeMaxPathSum solution = new BinaryTreeMaxPathSum();
        
        // Test Case 1: [1,2,3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Test Case 1 Result: " + solution.maxPathSum(root1)); // Expected: 6
        
        // Reset maxSum for next test case
        solution = new BinaryTreeMaxPathSum();
        
        // Test Case 2: [-10,9,20,null,null,15,7]
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("Test Case 2 Result: " + solution.maxPathSum(root2)); // Expected: 42
    }
}