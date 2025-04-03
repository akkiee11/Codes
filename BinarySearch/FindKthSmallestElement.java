/**
 * Find K-th Smallest Element
 * Problem: Given an array of integers nums and an integer k, return the k-th smallest element in the array.
 * Note that it is the k-th smallest element in the sorted order, not the k-th distinct element.
 *
 * Example:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 2
 */
import java.util.PriorityQueue;

public class FindKthSmallestElement {
    public int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
        }
        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    // Test cases
    public static void main(String[] args) {
        FindKthSmallestElement solution = new FindKthSmallestElement();
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        System.out.println(solution.findKthSmallest(nums1, 2)); // Expected: 2

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(solution.findKthSmallest(nums2, 4)); // Expected: 3
    }
}