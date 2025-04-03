/**
 * Find Median from Data Stream
 *
 * Problem: Design a data structure that supports the following two operations:
 * 1. addNum(int num) - Add a integer number from the data stream to the data structure.
 * 2. findMedian() - Return the median of all elements so far.
 *
 * Example:
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.findMedian(); // return 1.0
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5
 * medianFinder.addNum(3);    // arr = [1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * Approach:
 * 1. Use two heaps:
 *    - maxHeap for the smaller half of numbers
 *    - minHeap for the larger half of numbers
 * 2. Maintain balance so that:
 *    - maxHeap.size() == minHeap.size() (even numbers)
 *    - maxHeap.size() == minHeap.size() + 1 (odd numbers)
 * 3. When adding a number:
 *    - Add to maxHeap if number is smaller than maxHeap.peek()
 *    - Otherwise add to minHeap
 *    - Rebalance heaps if needed
 * 4. To find median:
 *    - If sizes are equal, average of both heap tops
 *    - Otherwise, top of maxHeap
 *
 * Time Complexity:
 * - addNum: O(log n)
 * - findMedian: O(1)
 * Space Complexity: O(n)
 */

import java.util.*;

public class MedianFinder {
    // Max heap for the smaller half of numbers
    private PriorityQueue<Integer> maxHeap;
    // Min heap for the larger half of numbers
    private PriorityQueue<Integer> minHeap;
    
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // max heap
        minHeap = new PriorityQueue<>(); // min heap
    }
    
    public void addNum(int num) {
        // Add to appropriate heap
        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        
        // Rebalance if needed
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.isEmpty()) {
            return 0.0;
        }
        
        // If total size is odd, median is the middle element
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        
        // If total size is even, median is average of middle elements
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
    
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        
        // Test Case 1: Adding numbers one by one
        System.out.println("Test Case 1:");
        medianFinder.addNum(1);
        System.out.println("After adding 1: " + medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println("After adding 2: " + medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println("After adding 3: " + medianFinder.findMedian());
        
        // Test Case 2: New instance with different sequence
        System.out.println("\nTest Case 2:");
        MedianFinder medianFinder2 = new MedianFinder();
        medianFinder2.addNum(2);
        System.out.println("After adding 2: " + medianFinder2.findMedian());
        medianFinder2.addNum(3);
        System.out.println("After adding 3: " + medianFinder2.findMedian());
        medianFinder2.addNum(1);
        System.out.println("After adding 1: " + medianFinder2.findMedian());
        
        // Test Case 3: Adding same numbers
        System.out.println("\nTest Case 3:");
        MedianFinder medianFinder3 = new MedianFinder();
        medianFinder3.addNum(1);
        System.out.println("After adding 1: " + medianFinder3.findMedian());
        medianFinder3.addNum(1);
        System.out.println("After adding 1: " + medianFinder3.findMedian());
        medianFinder3.addNum(1);
        System.out.println("After adding 1: " + medianFinder3.findMedian());
    }
}