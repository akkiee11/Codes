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