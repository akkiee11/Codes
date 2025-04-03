/**
 * Merge K Sorted Lists
 *
 * Problem: You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * Example 2:
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 * Input: lists = [[]]
 * Output: []
 *
 * Approach:
 * 1. Use a min-heap (PriorityQueue) to keep track of the smallest element among all lists
 * 2. Initially add the first element from each list to the min-heap
 * 3. While heap is not empty:
 *    - Poll the smallest element
 *    - Add it to result list
 *    - If this node has next element, add it to heap
 * 4. Return the merged list
 *
 * Time Complexity: O(N * log(k)) where N is total number of nodes and k is number of linked lists
 * Space Complexity: O(k) for the priority queue
 */

import java.util.*;

public class MergeKSortedLists {
    // Definition for singly-linked list
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        // Create a min heap based on node values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );
        
        // Add the first node from each list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
        // Keep polling the smallest element and add its next node to heap
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            tail.next = node;
            tail = tail.next;
            
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    // Helper method to create a linked list from array
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }
    
    // Helper method to print a linked list
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();
        
        // Test Case 1: Multiple non-empty lists
        ListNode[] lists1 = new ListNode[]{
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        System.out.println("Test Case 1:");
        System.out.println("Input lists:");
        for (ListNode list : lists1) printList(list);
        System.out.println("Merged list:");
        printList(solution.mergeKLists(lists1));
        
        // Test Case 2: Empty array of lists
        ListNode[] lists2 = new ListNode[]{};
        System.out.println("\nTest Case 2:");
        System.out.println("Input: Empty array");
        System.out.println("Merged list:");
        printList(solution.mergeKLists(lists2));
        
        // Test Case 3: Array with empty list
        ListNode[] lists3 = new ListNode[]{null};
        System.out.println("\nTest Case 3:");
        System.out.println("Input: Array with empty list");
        System.out.println("Merged list:");
        printList(solution.mergeKLists(lists3));
    }
}