/**
 * Palindrome Linked List
 * 
 * Problem Description:
 * Given the head of a singly linked list, determine if the linked list is a palindrome.
 * A palindrome is a sequence that reads the same forward and backward.
 * 
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 * 
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 * 
 * Constraints:
 * - The number of nodes in the list is in the range [1, 10^5]
 * - 0 <= Node.val <= 9
 * 
 * Approach:
 * Two Pointers with In-place Reversal:
 * 1. Find the middle of the linked list using slow and fast pointers
 * 2. Reverse the second half of the linked list
 * 3. Compare first half with reversed second half
 * 4. Restore the linked list (optional)
 * - Time Complexity: O(n)
 * - Space Complexity: O(1)
 */

public class PalindromeLinkedList {
    
    // Definition for singly-linked list node
    public static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse the second half
        ListNode secondHalf = reverseList(slow.next);
        
        // Compare first half with reversed second half
        ListNode firstHalf = head;
        ListNode secondHalfPointer = secondHalf;
        boolean isPalindrome = true;
        
        while (secondHalfPointer != null) {
            if (firstHalf.val != secondHalfPointer.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalfPointer = secondHalfPointer.next;
        }
        
        // Restore the linked list by reversing the second half again
        slow.next = reverseList(secondHalf);
        
        return isPalindrome;
    }
    
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        
        return prev;
    }
    
    // Helper method to create a linked list from array
    private static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    // Helper method to print a linked list
    private static String linkedListToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 2, 2, 1},          // True - Even length palindrome
            {1, 2, 3, 2, 1},        // True - Odd length palindrome
            {1, 2},                 // False - Not a palindrome
            {1},                    // True - Single element
            {1, 2, 3, 3, 2, 1},     // True - Longer palindrome
            {1, 2, 3, 4, 5}         // False - Not a palindrome
        };
        
        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            ListNode head = createLinkedList(testCases[i]);
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Input: " + linkedListToString(head));
            boolean result = isPalindrome(head);
            System.out.println("Output: " + result);
            // Verify the list is still intact after checking palindrome
            System.out.println("List after check: " + linkedListToString(head));
        }
    }
}