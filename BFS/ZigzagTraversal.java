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