import java.util.*;

public class LevelOrderTraversal {
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
     * Given the root of a binary tree, return the level order traversal of its nodes' values
     * (i.e., from left to right, level by level).
     * 
     * Example 1:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[9,20],[15,7]]
     * 
     * Example 2:
     * Input: root = [1]
     * Output: [[1]]
     * 
     * Example 3:
     * Input: root = []
     * Output: []
     * 
     * Constraints:
     * - The number of nodes in the tree is in the range [0, 2000]
     * - -1000 <= Node.val <= 1000
     * 
     * Intuition:
     * - Use BFS with a queue to process nodes level by level
     * - Queue size at each iteration represents number of nodes at current level
     * - Process all nodes at current level before moving to next level
     * 
     * Approach:
     * 1. Initialize a queue with root node and result list
     * 2. While queue is not empty:
     *    - Get current level size
     *    - Process all nodes at current level
     *    - Add their children to queue for next level
     * 3. Return the result list containing all levels
     * 
     * Time Complexity: O(n), where n is the number of nodes in the tree
     * Space Complexity: O(w), where w is the maximum width of the tree
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        // Queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);
                
                // Add children to queue for next level processing
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Create a sample binary tree
        //      3
        //     / \
        //    9  20
        //       / \
        //      15  7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        
        // Test the level order traversal
        List<List<Integer>> levels = levelOrder(root);
        System.out.println("Level Order Traversal:");
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("Level " + i + ": " + levels.get(i));
        }
    }
}