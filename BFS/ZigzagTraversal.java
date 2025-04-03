import java.util.*;

public class ZigzagTraversal {
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
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
     * (i.e., from left to right, then right to left for the next level and alternate between).
     * 
     * Example 1:
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[3],[20,9],[15,7]]
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
     * - -100 <= Node.val <= 100
     * 
     * Intuition:
     * - Use BFS with a queue for level-by-level traversal
     * - Use a boolean flag to track direction (left-to-right or right-to-left)
     * - Use LinkedList for easy insertion at both ends
     * 
     * Approach:
     * 1. Initialize queue with root and direction flag
     * 2. For each level:
     *    - Create a LinkedList for current level
     *    - Process nodes based on direction flag
     *    - Add nodes to start/end of list based on direction
     *    - Add children to queue for next level
     *    - Toggle direction flag
     * 3. Return result list containing all levels
     * 
     * Time Complexity: O(n), where n is the number of nodes
     * Space Complexity: O(w), where w is the maximum width of the tree
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                
                // Add the node to current level list based on direction
                if (leftToRight) {
                    currentLevel.addLast(current.val);
                } else {
                    currentLevel.addFirst(current.val);
                }
                
                // Add children to queue
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight;  // Toggle direction for next level
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
        
        // Test the zigzag level order traversal
        List<List<Integer>> result = zigzagLevelOrder(root);
        System.out.println("Zigzag Level Order Traversal:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Level " + i + ": " + result.get(i));
        }
    }
}