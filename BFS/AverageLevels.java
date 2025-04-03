import java.util.*;

public class AverageLevels {
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
     * Given the root of a binary tree, return the average value of the nodes on each level
     * in the form of an array. Answers within 10^-5 of the actual answer will be accepted.
     * 
     * Example 1:
     * Input: root = [3,9,20,15,7]
     * Output: [3.00000,14.50000,11.00000]
     * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
     * 
     * Example 2:
     * Input: root = [1]
     * Output: [1.00000]
     * 
     * Constraints:
     * - The number of nodes in the tree is in the range [1, 10^4]
     * - -2^31 <= Node.val <= 2^31 - 1
     * 
     * Intuition:
     * - Use BFS to traverse the tree level by level
     * - For each level, calculate sum of node values and count of nodes
     * - Average = sum / count for each level
     * 
     * Approach:
     * 1. Initialize queue with root node and result list
     * 2. For each level:
     *    - Track level size and calculate sum of node values
     *    - Process all nodes at current level
     *    - Add their children to queue for next level
     *    - Calculate average = sum / size and add to result
     * 3. Return list of averages
     * 
     * Time Complexity: O(n), where n is the number of nodes in the tree
     * Space Complexity: O(w), where w is the maximum width of the tree
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                levelSum += current.val;
                
                // Add children to queue for next level
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            // Calculate and add the average for current level
            result.add(levelSum / levelSize);
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
        
        // Test the average of levels
        List<Double> averages = averageOfLevels(root);
        System.out.println("Average of Levels:");
        for (int i = 0; i < averages.size(); i++) {
            System.out.printf("Level %d: %.2f%n", i, averages.get(i));
        }
    }
}