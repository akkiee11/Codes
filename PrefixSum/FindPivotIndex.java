package PrefixSum;

/**
 * Problem Statement:
 * Given an array of integers nums, calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 * If no such index exists, return -1.
 * 
 * Approach Intuition:
 * 1. Calculate the total sum of all elements in the array
 * 2. Iterate through the array, maintaining a running sum of elements to the left
 * 3. At each index, check if left sum equals (total sum - left sum - current element)
 * 4. If condition matches, return current index as pivot
 * 5. If no pivot found after iteration, return -1
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        FindPivotIndex solution = new FindPivotIndex();
        
        // Test Case 1: Pivot exists in the middle
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println(solution.pivotIndex(nums1)); // Expected: 3
        
        // Test Case 2: No pivot exists
        int[] nums2 = {1, 2, 3};
        System.out.println(solution.pivotIndex(nums2)); // Expected: -1
        
        // Test Case 3: Pivot at the beginning
        int[] nums3 = {2, 1, -1};
        System.out.println(solution.pivotIndex(nums3)); // Expected: 0
        
        // Test Case 4: Pivot at the end
        int[] nums4 = {-1, -1, -1, 0, 1, 1};
        System.out.println(solution.pivotIndex(nums4)); // Expected: 5
        
        // Test Case 5: Single element array
        int[] nums5 = {10};
        System.out.println(solution.pivotIndex(nums5)); // Expected: 0
    }
}