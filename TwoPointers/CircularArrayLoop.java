/**
 * Circular Array Loop
 * 
 * Problem Description:
 * You are playing with a circular array where each element points to the next element in the array.
 * The value at each index represents the number of steps to move forward (positive) or backward (negative).
 * A cycle in the array exists if, starting from any index, following the jumps, you can reach back to the
 * same index AND the cycle length is greater than 1.
 * 
 * Example 1:
 * Input: nums = [2,-1,1,2,2]
 * Output: true
 * Explanation: There is a cycle from index 0 -> 2 -> 3 -> 0
 * 
 * Example 2:
 * Input: nums = [-1,2]
 * Output: false
 * Explanation: The sequence goes: 1 -> 1 (self loop)
 * 
 * Example 3:
 * Input: nums = [-2,1,-1,-2,-2]
 * Output: false
 * Explanation: The sequence alternates between positive and negative indices
 * 
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - -1000 <= nums[i] <= 1000
 * - nums[i] != 0
 * 
 * Approach:
 * Two Pointer (Fast and Slow) Technique:
 * 1. For each index, try to detect a cycle starting from that index
 * 2. Use fast and slow pointers similar to Floyd's Cycle Detection
 * 3. Keep track of direction - all elements in cycle must have same sign
 * 4. Mark visited elements to avoid revisiting in future iterations
 * - Time Complexity: O(n) where n is the length of array
 * - Space Complexity: O(1) as we modify the input array to mark visited elements
 */

import java.util.*;

public class CircularArrayLoop {
    
    public static boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        
        // Check each position as a potential start of a cycle
        for (int i = 0; i < n; i++) {
            // Skip if this element was already visited
            if (nums[i] == 0) continue;
            
            // Slow/fast pointers initialization
            int slow = i;
            int fast = i;
            
            // Store the direction of movement (positive or negative)
            boolean isForward = nums[i] > 0;
            
            // Move pointers until we find a cycle or hit an invalid state
            do {
                // Move slow pointer one step
                slow = getNextPosition(nums, slow, isForward);
                if (slow == -1) break; // Invalid move
                
                // Move fast pointer two steps
                fast = getNextPosition(nums, fast, isForward);
                if (fast == -1) break; // Invalid move
                fast = getNextPosition(nums, fast, isForward);
                if (fast == -1) break; // Invalid move
                
            } while (slow != fast);
            
            // If we found a valid cycle
            if (slow != -1 && slow == fast) {
                // Verify cycle length > 1
                int next = getNextPosition(nums, slow, isForward);
                if (next != slow) return true;
            }
            
            // Mark this path as visited by setting elements to 0
            markVisited(nums, i, isForward);
        }
        
        return false;
    }
    
    private static int getNextPosition(int[] nums, int current, boolean isForward) {
        // Check if current element matches the required direction
        boolean currentIsForward = nums[current] > 0;
        if (currentIsForward != isForward) return -1;
        
        // Calculate next position
        int next = (current + nums[current]) % nums.length;
        if (next < 0) next += nums.length; // Handle negative indices
        
        // Check for self loop
        if (next == current) return -1;
        
        return next;
    }
    
    private static void markVisited(int[] nums, int start, boolean isForward) {
        int current = start;
        while (true) {
            int next = (current + nums[current]) % nums.length;
            if (next < 0) next += nums.length;
            if (nums[current] == 0 || (nums[current] > 0) != isForward) break;
            nums[current] = 0;
            current = next;
        }
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {2, -1, 1, 2, 2},          // Should return true
            {-1, 2},                    // Should return false
            {-2, 1, -1, -2, -2},        // Should return false
            {3, 1, 2},                  // Should return true
            {1, 1, 1, 1},               // Should return true
            {2, 2, -1, 2},              // Should return true
            {1, -1},                    // Should return false
            {1, 2, 3, 4, 5}             // Should return false
        };
        
        // Run test cases
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Array: " + Arrays.toString(testArrays[i]));
            boolean result = circularArrayLoop(testArrays[i].clone());
            System.out.println("Has cycle: " + result);
        }
    }
}