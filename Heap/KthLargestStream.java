/**
 * Kth Largest Element in a Stream
 *
 * Problem: Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 * - KthLargest(int k, int[] nums) Initializes the object with k and initial array.
 * - int add(int val) Appends val to the stream and returns the kth largest element.
 *
 * Example:
 * Input:
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output:
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation:
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4 (stream: [4, 5, 8, 2, 3])
 * kthLargest.add(5);   // return 5 (stream: [4, 5, 8, 2, 3, 5])
 * kthLargest.add(10);  // return 5 (stream: [4, 5, 8, 2, 3, 5, 10])
 * kthLargest.add(9);   // return 8 (stream: [4, 5, 8, 2, 3, 5, 10, 9])
 * kthLargest.add(4);   // return 8 (stream: [4, 5, 8, 2, 3, 5, 10, 9, 4])
 *
 * Approach:
 * 1. Maintain a min heap of size k to store k largest elements
 * 2. For each new element:
 *    - If heap size < k, add element
 *    - If element > heap top, remove top and add new element
 * 3. The top of heap is always the kth largest element
 *
 * Time Complexity: 
 * - Constructor: O(N * log(k))
 * - Add: O(log(k))
 * Space Complexity: O(k)
 */

import java.util.*;

public class KthLargestStream {
    private final PriorityQueue<Integer> minHeap;
    private final int k;
    
    public KthLargestStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();
        
        // Initialize heap with array elements
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        // Add new value if heap size is less than k
        if (minHeap.size() < k) {
            minHeap.offer(val);
        }
        // If new value is larger than smallest value in heap
        else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        
        return minHeap.peek();
    }
    
    public static void main(String[] args) {
        // Test Case 1
        System.out.println("Test Case 1:");
        KthLargestStream kthLargest1 = new KthLargestStream(3, new int[]{4, 5, 8, 2});
        System.out.println("KthLargest(3, [4, 5, 8, 2])");
        System.out.println("add(3): " + kthLargest1.add(3));
        System.out.println("add(5): " + kthLargest1.add(5));
        System.out.println("add(10): " + kthLargest1.add(10));
        System.out.println("add(9): " + kthLargest1.add(9));
        System.out.println("add(4): " + kthLargest1.add(4));
        
        // Test Case 2
        System.out.println("\nTest Case 2:");
        KthLargestStream kthLargest2 = new KthLargestStream(1, new int[]{});
        System.out.println("KthLargest(1, [])");
        System.out.println("add(3): " + kthLargest2.add(3));
        System.out.println("add(1): " + kthLargest2.add(1));
        System.out.println("add(5): " + kthLargest2.add(5));
    }
}