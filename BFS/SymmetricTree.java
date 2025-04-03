import java.util.*;

public class SymmetricTree {
    // TreeNode class definition
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    /*
     * Problem Statement:
     * Given the root of a binary tree, check whether it is a mirror of itself
     * (i.e., symmetric around its center). A binary tree is symmetric if the left
     * and right subtrees are mirror images of each other.
     * 
     * Example 1:
     * Input: root = [1,2,2,3,4,4,3]
     * Output: true
     * Explanation: The binary tree is symmetric
     *      1
     *     / \
     *    2   2
     *   / \ / \
     *  3  4 4  3
     * 
     * Example 2:
     * Input: root = [1,2,2,null,3,null,3]
     * Output: false
     * Explanation: The binary tree is not symmetric
     *      1
     *     / \
     *    2   2
     *     \   \
     *      3    3
     * 
     * Constraints:
     * - The number of nodes in the tree is in the range [0, 1000]
     * - -100 <= Node.val <= 100
     * 
     * Intuition:
     * - Use BFS to process nodes level by level
     * - Compare pairs of nodes from opposite sides
     * - For symmetry, values should match and structure should mirror
     * - Queue helps process nodes in correct order
     * 
     * Approach:
     * 1. Start with root's left and right children in queue
     * 2. Process nodes in pairs:
     *    - Both null: continue (symmetric)
     *    - One null or values different: return false
     *    - Add children in mirrored order:
     *      a. Outer pair: left.left and right.right
     *      b. Inner pair: left.right and right.left
     * 3. If all pairs match, return true
     * 
     * Time Complexity: O(n), where n is the number of nodes in the tree
     * Space Complexity: O(w), where w is the maximum width of the tree
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            
            // If both null, continue checking next pair
            if (left == null && right == null) continue;
            
            // If one is null or values don't match, tree is not symmetric
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            
            // Add outer pairs
            queue.offer(left.left);
            queue.offer(right.right);
            
            // Add inner pairs
            queue.offer(left.right);
            queue.offer(right.left);
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Test Case 1: Symmetric Tree
        //      1
        //     / \
        //    2   2
        //   / \ / \
        //  3  4 4  3
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);
        
        System.out.println("Test Case 1 (Symmetric Tree): " + isSymmetric(root1));
        
        // Test Case 2: Non-symmetric Tree
        //      1
        //     / \
        //    2   2
        //     \   \
        //      3    3
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        
        System.out.println("Test Case 2 (Non-symmetric Tree): " + isSymmetric(root2));
    }
}