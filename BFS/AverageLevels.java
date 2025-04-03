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