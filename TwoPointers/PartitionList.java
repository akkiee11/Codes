/**
 * Partition List
 * 
 * Problem Description:
 * Given the head of a linked list and a value x, partition it such that all nodes less than x
 * come before nodes greater than or equal to x. You should preserve the original relative
 * order of the nodes in each of the two partitions.
 * 
 * Example 1:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * 
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [0, 200]
 * - -100 <= Node.val <= 100
 * - -200 <= x <= 200
 * 
 * Approach:
 * Two Pointer Technique with Two Lists:
 * 1. Create two separate lists: one for nodes < x and one for nodes >= x
 * 2. Traverse the original list and add nodes to appropriate list
 * 3. Connect the two lists
 * - Time Complexity: O(n)
 * - Space Complexity: O(1) as we reuse existing nodes
 */

public class PartitionList {
    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public static ListNode partition(ListNode head, int x) {
        // Create dummy nodes for both lists
        ListNode beforeDummy = new ListNode(0);
        ListNode afterDummy = new ListNode(0);
        ListNode before = beforeDummy;
        ListNode after = afterDummy;
        
        // Traverse original list and partition nodes
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        
        // Connect the two lists
        after.next = null;  // Set the end of after list to null
        before.next = afterDummy.next;  // Connect before list to after list
        
        return beforeDummy.next;
    }
    
    // Helper method to create a linked list from array
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
    
    // Helper method to convert linked list to string
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(",");
            }
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {1, 4, 3, 2, 5, 2},  // Standard case
            {2, 1},               // Two elements
            {1},                  // Single element
            {5, 4, 3, 2, 1},      // Descending order
            {1, 2, 3, 4, 5},      // Ascending order
            {3, 3, 3, 3},         // All equal values
        };
        
        int[] partitionValues = {3, 2, 2, 3, 3, 3};
        
        // Run test cases
        for (int i = 0; i < testArrays.length; i++) {
            ListNode head = createList(testArrays[i]);
            int x = partitionValues[i];
            
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Original list: " + listToString(head));
            System.out.println("Partition value x = " + x);
            
            ListNode result = partition(head, x);
            System.out.println("Partitioned list: " + listToString(result));
        }
    }
}