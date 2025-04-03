/**
 * Recover Binary Search Tree
 * 
 * Problem: Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * Example:
 * Input: [1,3,null,null,2]  (3 and 1 are swapped)
 *    1
 *   /
 *  3
 *   \
 *    2
 * Output: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * Approach:
 * 1. Use inorder traversal to identify violated BST property
 * 2. Keep track of previous node during traversal
 * 3. Find two nodes that violate BST property
 * 4. Swap the values of the identified nodes
 */

class RecoverBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
    
    private TreeNode first = null;  // First node to be swapped
    private TreeNode second = null; // Second node to be swapped
    private TreeNode prev = null;   // Previous node in inorder traversal
    
    public void recoverTree(TreeNode root) {
        // Find the two nodes
        findTwoSwapped(root);
        
        // Swap their values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    private void findTwoSwapped(TreeNode root) {
        if (root == null) return;
        
        // Inorder traversal: left, root, right
        findTwoSwapped(root.left);
        
        // Check if current node violates BST property
        if (prev != null && root.val < prev.val) {
            if (first == null) {
                // First violation, mark both nodes
                first = prev;
                second = root;
            } else {
                // Second violation, mark only current node
                second = root;
            }
        }
        prev = root;
        
        findTwoSwapped(root.right);
    }
    
    // Iterative solution using Morris Traversal
    // Space complexity: O(1)
    public void recoverTreeMorris(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                // Process current node
                if (prev != null && curr.val < prev.val) {
                    if (first == null) {
                        first = prev;
                        second = curr;
                    } else {
                        second = curr;
                    }
                }
                prev = curr;
                curr = curr.right;
            } else {
                // Find the inorder predecessor
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Create temporary link
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    // Remove temporary link
                    predecessor.right = null;
                    
                    // Process current node
                    if (prev != null && curr.val < prev.val) {
                        if (first == null) {
                            first = prev;
                            second = curr;
                        } else {
                            second = curr;
                        }
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        
        // Swap the values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // Time Complexity: O(N) where N is number of nodes
    // Space Complexity: O(H) where H is height of tree for recursive, O(1) for Morris
    public static void main(String[] args) {
        RecoverBinarySearchTree solution = new RecoverBinarySearchTree();
        
        // Create a BST with swapped nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        
        System.out.println("Before recovery:");
        printInorder(root);
        
        solution.recoverTree(root);
        
        System.out.println("\nAfter recovery:");
        printInorder(root);
    }
    
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }
}