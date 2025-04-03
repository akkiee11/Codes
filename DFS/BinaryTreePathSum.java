import java.util.*;

public class BinaryTreePathSum {
    
    /*
     * Problem Statement:
     * Given the root of a binary tree and an integer targetSum, return true if the tree has a 
     * root-to-leaf path such that adding up all the values along the path equals targetSum.
     * A leaf is a node with no children.
     * 
     * Example 1:
     *     5
     *    / \
     *   4   8
     *  /   / \
     * 11  13  4
     * /  \      \
     * 7   2      1
     * 
     * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * Output: true
     * Explanation: The root-to-leaf path 5->4->11->2 has sum = 22
     * 
     * Intuition:
     * - Use DFS to explore all root-to-leaf paths
     * - Keep track of sum along current path
     * - When reaching a leaf, check if current sum equals target
     * 
     * Approach using DFS:
     * 1. Start DFS from root with current sum = 0
     * 2. At each node:
     *    - Add node's value to current sum
     *    - If leaf node, check if sum equals target
     *    - Recursively check left and right subtrees
     * 
     * Time Complexity: O(N) where N is number of nodes
     * Space Complexity: O(H) where H is height of tree (recursion stack)
     * 
     * Key DFS Concepts Demonstrated:
     * - Tree traversal using DFS
     * - Path sum tracking during traversal
     * - Leaf node detection
     * - Early termination on success
     */
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        
        // If it's a leaf node, check if current path sum equals target
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // Recursively check left and right subtrees with remaining sum
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || 
               hasPathSum(root.right, remainingSum);
    }
    
    // Alternative implementation that prints the path when found
    public static boolean hasPathSumWithPath(TreeNode root, int targetSum, List<Integer> path) {
        if (root == null) {
            return false;
        }
        
        path.add(root.val);
        
        // If it's a leaf node and sum matches
        if (root.left == null && root.right == null && targetSum == root.val) {
            System.out.println("Path found: " + path);
            return true;
        }
        
        // Recursively check subtrees
        int remainingSum = targetSum - root.val;
        boolean found = hasPathSumWithPath(root.left, remainingSum, path) || 
                       hasPathSumWithPath(root.right, remainingSum, path);
        
        // Backtrack by removing current node from path
        path.remove(path.size() - 1);
        return found;
    }
    
    public static void main(String[] args) {
        // Test case 1: Tree from example
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);
        
        int targetSum1 = 22;
        System.out.println("Test case 1 - Has path sum " + targetSum1 + ": " + 
                          hasPathSum(root1, targetSum1));
        System.out.println("Path for test case 1:");
        hasPathSumWithPath(root1, targetSum1, new ArrayList<>());
        
        // Test case 2: Simple tree with no valid path
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        int targetSum2 = 5;
        System.out.println("\nTest case 2 - Has path sum " + targetSum2 + ": " + 
                          hasPathSum(root2, targetSum2));
        System.out.println("Path for test case 2:");
        hasPathSumWithPath(root2, targetSum2, new ArrayList<>());
        
        // Test case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        int targetSum3 = 1;
        System.out.println("\nTest case 3 - Has path sum " + targetSum3 + ": " + 
                          hasPathSum(root3, targetSum3));
        System.out.println("Path for test case 3:");
        hasPathSumWithPath(root3, targetSum3, new ArrayList<>());
    }
}