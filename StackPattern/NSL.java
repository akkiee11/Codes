import java.util.Stack;

/**
 * NSL (Next Smaller to Left) implementation using Stack
 * 
 * Problem Statement:
 * Given an array of integers, find the nearest smaller element to the left of every element.
 * If no smaller element exists to the left, return -1.
 * 
 * Approach:
 * 1. Use a stack to maintain potential candidates for next smaller elements
 * 2. Iterate through the array from left to right
 * 3. While stack is not empty and top element is >= current element, pop from stack
 * 4. If stack is empty, no smaller element exists to the left
 * 5. Else, the top element is the next smaller element
 * 6. Push current element to stack for future comparisons
 * 
 * Time Complexity: O(n) - Each element is pushed and popped from stack at most once
 * Space Complexity: O(n) - Stack space in worst case
 */
public class NSL {
    /**
 * Finds the next smaller element to the left for each element in the array
 * 
 * @param arr Input array of integers
 * @return Array containing next smaller elements to the left
 */
public static int[] nextSmallerToLeft(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    // Test cases including edge cases
public static void main(String[] args) {
    // Test case 1: Regular input
    int[] arr1 = {4, 5, 2, 10, 8};
    int[] result1 = nextSmallerToLeft(arr1);
    System.out.println("Test Case 1: ");
    for (int num : result1) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Test case 2: Empty array
    int[] arr2 = {};
    int[] result2 = nextSmallerToLeft(arr2);
    System.out.println("Test Case 2: ");
    for (int num : result2) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Test case 3: All elements same
    int[] arr3 = {5, 5, 5, 5};
    int[] result3 = nextSmallerToLeft(arr3);
    System.out.println("Test Case 3: ");
    for (int num : result3) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Test case 4: Descending order
    int[] arr4 = {5, 4, 3, 2, 1};
    int[] result4 = nextSmallerToLeft(arr4);
    System.out.println("Test Case 4: ");
    for (int num : result4) {
        System.out.print(num + " ");
    }
    System.out.println();

    // Test case 5: Ascending order
    int[] arr5 = {1, 2, 3, 4, 5};
    int[] result5 = nextSmallerToLeft(arr5);
    System.out.println("Test Case 5: ");
    for (int num : result5) {
        System.out.print(num + " ");
    }
    System.out.println();
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = nextSmallerToLeft(arr);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}