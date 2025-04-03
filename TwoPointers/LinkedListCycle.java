/**
 * Linked List Cycle Detection
 * 
 * Problem Description:
 * Given a linked list, determine if it has a cycle in it using Floyd's Cycle-Finding Algorithm
 * (also known as the "tortoise and hare" algorithm).
 * 
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1 (position where tail connects to)
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * 
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * 
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * 
 * Approach:
 * Two Pointer Technique (Floyd's Cycle-Finding Algorithm):
 * - Use two pointers: slow (moves one step) and fast (moves two steps)
 * - If there's a cycle, fast pointer will eventually meet slow pointer
 * - If there's no cycle, fast pointer will reach end of list
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class LinkedListCycle {
    
    // Definition for singly-linked list node
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        // Initialize slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        
        // Move slow one step and fast two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move one step
            fast = fast.next.next;      // Move two steps
            
            // If they meet, there's a cycle
            if (slow == fast) {
                return true;
            }
        }
        
        // If fast reaches end, no cycle
        return false;
    }
    
    // Helper method to create a linked list with a cycle
    private static ListNode createLinkedList(int[] values, int pos) {
        if (values == null || values.length == 0) {
            return null;
        }
        
        // Create nodes
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new ListNode(values[i]);
        }
        
        // Connect nodes
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        
        // Create cycle if pos is valid
        if (pos >= 0 && pos < values.length) {
            nodes[values.length - 1].next = nodes[pos];
        }
        
        return nodes[0];
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {3, 2, 0, -4},      // Standard case with cycle
            {1, 2},              // Two nodes with cycle
            {1},                 // Single node without cycle
            {1, 2, 3, 4, 5},    // Multiple nodes without cycle
            {1, 1, 1, 1}        // Same values with cycle
        };
        
        int[] positions = {1, 0, -1, -1, 2};
        
        // Run test cases
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            ListNode head = createLinkedList(testArrays[i], positions[i]);
            boolean hasCycle = hasCycle(head);
            System.out.println("Input array: " + java.util.Arrays.toString(testArrays[i]));
            System.out.println("Position to connect: " + positions[i]);
            System.out.println("Has cycle: " + hasCycle);
        }
    }
}