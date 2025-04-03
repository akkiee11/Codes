import java.util.*;

public class MaximumWidth {
    // TreeNode class definition
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        // Queue for BFS traversal
        // Each entry is a pair: [node, position]
        // Position helps track the index of each node in its level
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int levelStart = queue.peek().getValue(); // Position of first node in level
            int levelEnd = 0; // Will store position of last node in level
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.getKey();
                int position = current.getValue();
                
                // Update the position of the last node in this level
                levelEnd = position;
                
                // For left child: 2*position
                // For right child: 2*position + 1
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * position));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * position + 1));
                }
            }
            
            // Update max width (add 1 because width is inclusive)
            maxWidth = Math.max(maxWidth, levelEnd - levelStart + 1);
        }
        
        return maxWidth;
    }
    
    // Simple Pair class implementation
    static class Pair<K, V> {
        private K key;
        private V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
    }
    
    public static void main(String[] args) {
        // Test case 1
        //      1
        //     / \
        //    3   2
        //   /     \
        //  5       9
        //         / \
        //        6   7
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.right.right = new TreeNode(9);
        root1.right.right.left = new TreeNode(6);
        root1.right.right.right = new TreeNode(7);
        
        System.out.println("Test case 1 - Maximum width: " + widthOfBinaryTree(root1)); // Expected: 8
        
        // Test case 2
        //      1
        //     / \
        //    3   2
        //   /     \
        //  5       9
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(5);
        root2.right.right = new TreeNode(9);
        
        System.out.println("Test case 2 - Maximum width: " + widthOfBinaryTree(root2)); // Expected: 4
    }
}