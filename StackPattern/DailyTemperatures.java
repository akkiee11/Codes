import java.util.Stack;

/*
Problem Statement:
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]

Constraints:
1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100

Approach:
We use a monotonic decreasing stack to keep track of indices of temperatures. For each day, we pop from the stack until we find a warmer temperature or the stack is empty, then push the current index.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];
        
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prev = stack.pop();
                result[prev] = i - prev;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        DailyTemperatures solution = new DailyTemperatures();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = solution.dailyTemperatures(temperatures);
        System.out.print("Daily Temperatures: ");
        for (int r : result) {
            System.out.print(r + " ");
        }
        System.out.println(); // Output: 1 1 4 2 1 1 0 0
    }
}