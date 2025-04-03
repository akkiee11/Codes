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
    
    /*
     * Problem Statement:
     * Given the root of a binary tree, return the maximum width of the given tree.
     * The maximum width of a tree is the maximum width among all levels.
     * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
     * where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
     * 
     * Example 1:
     * Input: root = [1,3,2,5,null,null,9,6,null,7]
     * Output: 8
     * Explanation: The maximum width exists in the fourth level with length 8 (6,null,null,null,null,null,null,7)
     * 
     * Example 2:
     * Input: root = [1,3,2,5]
     * Output: 4
     * Explanation: The maximum width exists in the second level with length 4 (3,null,null,2)
     * 
     * Constraints:
     * - The number of nodes in the tree is in the range [1, 3000]
     * - -100 <= Node.val <= 100
     * 
     * Intuition:
     * - Use BFS to traverse the tree level by level
     * - Assign position numbers to nodes: left child = 2*pos, right child = 2*pos + 1
     * - Track the leftmost and rightmost nodes at each level
     * - Width = rightmost position - leftmost position + 1
     * 
     * Approach:
     * 1. Use a queue to perform level-order traversal
     * 2. For each node, store its position value along with the node
     * 3. At each level:
     *    - Track the position of first and last nodes
     *    - Calculate width as (last position - first position + 1)
     *    - Update maximum width if current width is larger
     * 4. Return the maximum width found
     * 
     * Time Complexity: O(n), where n is the number of nodes in the tree
     * Space Complexity: O(w), where w is the maximum width of the tree
     */
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