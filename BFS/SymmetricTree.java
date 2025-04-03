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