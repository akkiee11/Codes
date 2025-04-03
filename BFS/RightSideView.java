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