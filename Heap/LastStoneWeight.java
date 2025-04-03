/**
 * Last Stone Weight
 *
 * Problem: We have a collection of stones, each stone has a positive integer weight.
 * Each turn, we choose the two heaviest stones and smash them together.
 * If x == y, both stones are destroyed.
 * If x != y, stone of weight x is destroyed, and stone of weight y has new weight y-x.
 * Return the weight of the last remaining stone, or 0 if there are no stones left.
 *
 * Example 1:
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation: 
 * - We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1]
 * - We combine 2 and 4 to get 2 so the array converts to [2,1,1,1]
 * - We combine 2 and 1 to get 1 so the array converts to [1,1,1]
 * - We combine 1 and 1 to get 0 so the array converts to [1]
 * - Only one stone left; return 1
 *
 * Example 2:
 * Input: stones = [1]
 * Output: 1
 *
 * Approach:
 * 1. Use a max heap to always get the two heaviest stones
 * 2. While heap has at least 2 stones:
 *    - Remove two heaviest stones
 *    - If they're different, add back their difference
 * 3. Return remaining stone weight or 0 if no stones left
 *
 * Time Complexity: O(N * log(N)) where N is the number of stones
 * Space Complexity: O(N) for the heap
 */

import java.util.*;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        // Create max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            Collections.reverseOrder()
        );
        
        // Add all stones to heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        // Process stones
        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();
            
            if (stone1 != stone2) {
                maxHeap.offer(stone1 - stone2);
            }
        }
        
        // Return remaining stone weight or 0 if no stones left
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
    
    public static void main(String[] args) {
        LastStoneWeight solution = new LastStoneWeight();
        
        // Test Case 1
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        System.out.println("Test Case 1:");
        System.out.println("Input: stones = [2,7,4,1,8,1]");
        System.out.println("Output: " + solution.lastStoneWeight(stones1));
        
        // Test Case 2
        int[] stones2 = {1};
        System.out.println("\nTest Case 2:");
        System.out.println("Input: stones = [1]");
        System.out.println("Output: " + solution.lastStoneWeight(stones2));
        
        // Test Case 3
        int[] stones3 = {2, 2};
        System.out.println("\nTest Case 3:");
        System.out.println("Input: stones = [2,2]");
        System.out.println("Output: " + solution.lastStoneWeight(stones3));
    }
}