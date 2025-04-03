import java.util.Stack;

/**
 * NGL (Next Greater to Left) implementation using Stack
 * Problem: Given an array of integers, find the nearest greater element to the left of each element.
 * If no such element exists, return -1.
 *
 * Approach:
 * 1. Use a stack to keep track of potential greater elements
 * 2. Iterate through the array from left to right
 * 3. For each element, pop elements from stack until we find a greater element or stack becomes empty
 * 4. If stack is empty, no greater element exists to the left
 * 5. Push current element to stack for future comparisons
 *
 * Time Complexity: O(n) - Each element is pushed and popped from stack at most once
 * Space Complexity: O(n) - Worst case stack size
 */
public class NGL {
    /**
 * Finds the next greater element to the left for each element in the array
 * @param arr Input array of integers
 * @return Array containing next greater elements to the left
 */
public static int[] nextGreaterToLeft(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    // Test cases including edge cases
public static void main(String[] args) {
    // Normal case
    int[] arr1 = {4, 5, 2, 10, 8};
    int[] result1 = nextGreaterToLeft(arr1);
    System.out.println("Test Case 1:");
    for (int num : result1) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Edge case: Empty array
    int[] arr2 = {};
    int[] result2 = nextGreaterToLeft(arr2);
    System.out.println("Test Case 2 (Empty array):");
    for (int num : result2) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Edge case: All elements same
    int[] arr3 = {5, 5, 5, 5};
    int[] result3 = nextGreaterToLeft(arr3);
    System.out.println("Test Case 3 (All elements same):");
    for (int num : result3) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Edge case: Descending order
    int[] arr4 = {10, 8, 6, 4, 2};
    int[] result4 = nextGreaterToLeft(arr4);
    System.out.println("Test Case 4 (Descending order):");
    for (int num : result4) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Edge case: Ascending order
    int[] arr5 = {2, 4, 6, 8, 10};
    int[] result5 = nextGreaterToLeft(arr5);
    System.out.println("Test Case 5 (Ascending order):");
    for (int num : result5) {
        System.out.print(num + " ");
    }
    System.out.println();
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = nextGreaterToLeft(arr);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}