import java.util.*;

public class RightSideView {
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
     * Given the root of a binary tree, imagine yourself standing on the right side of it.
     * Return the values of the nodes you can see ordered from top to bottom.
     * 
     * Example 1:
     * Input: root = [1,2,3,null,5,null,4]
     * Output: [1,3,4]
     * Explanation: The right side view includes nodes 1, 3, and 4.
     * 
     * Example 2:
     * Input: root = [1,null,3]
     * Output: [1,3]
     * 
     * Example 3:
     * Input: root = []
     * Output: []
     * 
     * Constraints:
     * - The number of nodes in the tree is in the range [0, 100]
     * - -100 <= Node.val <= 100
     * 
     * Intuition:
     * - Use BFS to traverse the tree level by level
     * - At each level, the rightmost node is visible from the right side
     * - Process nodes from left to right, keeping track of the last node at each level
     * 
     * Approach:
     * 1. Use a queue for level-order traversal
     * 2. For each level:
     *    - Track the size of the level
     *    - Process all nodes in the level
     *    - Add the last node's value to result (rightmost node)
     *    - Add children to queue for next level
     * 3. Return the list of visible nodes
     * 
     * Time Complexity: O(n), where n is the number of nodes in the tree
     * Space Complexity: O(w), where w is the maximum width of the tree
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                
                // If this is the rightmost node at this level, add to result
                if (i == levelSize - 1) {
                    result.add(current.val);
                }
                
                // Add children to queue for next level processing
                // Add left child first so right child will be processed last
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Create a sample binary tree
        //      1
        //     / \
        //    2   3
        //     \   \
        //      5   4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        
        // Test the right side view
        List<Integer> rightView = rightSideView(root);
        System.out.println("Right Side View: " + rightView);
        // Expected output: [1, 3, 4]
    }
}