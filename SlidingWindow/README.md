# Sliding Window Pattern

## Introduction
The Sliding Window pattern is a computational technique that aims to reduce the use of nested loops in algorithms. It converts two nested loops into a single loop, reducing time complexity from O(n²) to O(n). This pattern is particularly useful for solving problems that involve arrays, strings, or linked lists where we need to find or calculate something among all the contiguous subarrays or sublists of a given size.

## When to Use Sliding Window?
- When dealing with contiguous sequence of elements (subarrays, substrings)
- When tracking a subset of elements in an array/string
- When the size of the window is fixed or variable based on certain conditions
- When needing to calculate something among all possible subarrays/substrings

## Types of Sliding Window
1. **Fixed Window Size**
   - Window size remains constant throughout
   - Example: Find maximum sum subarray of size k

2. **Variable Window Size**
   - Window size changes based on conditions
   - Example: Longest substring with k distinct characters

## Common Patterns
1. Initialize window pointers (start, end)
2. Expand window by moving end pointer
3. Process current window
4. Contract window by moving start pointer when needed
5. Update result based on window content

## Problems by Difficulty
### Easy
1. [**Maximum Sum Subarray of Size K**](MaxSumSubarray.java)
   - Problem: Find maximum sum of any contiguous subarray of size k
   - Learning: Fixed-size window technique

2. [**First Negative in Window**](FirstNegative.java)
   - Problem: Find first negative integer in every window of size k
   - Learning: Queue usage with sliding window

3. [**Count Occurrences of Anagrams**](CountAnagrams.java)
   - Problem: Count occurrences of anagrams of a pattern in a string
   - Learning: Fixed window with character frequency

4. [**Average of Subarrays**](AverageSubarrays.java)
   - Problem: Find averages of all contiguous subarrays of size k
   - Learning: Basic sliding window implementation

### Medium
1. [**Longest Substring Without Repeating Characters**](LongestSubstringNoRepeat.java)
   - Problem: Find longest substring without repeating characters
   - Learning: Variable-size window with HashSet

2. [**Fruit Into Baskets**](FruitBaskets.java)
   - Problem: Maximum fruits you can collect with two baskets
   - Learning: Variable window with limited distinct elements

3. [**Longest Repeating Character Replacement**](CharacterReplacement.java)
   - Problem: Longest substring with same letter after k replacements
   - Learning: Window with character frequency and constraints

4. [**Permutation in String**](PermutationString.java)
   - Problem: Find if string contains permutation of pattern
   - Learning: Fixed window with character frequency matching

5. [**Find All Anagrams**](FindAnagrams.java)
   - Problem: Find all anagrams of pattern in string
   - Learning: Fixed window with pattern matching

### Hard
1. [**Minimum Window Substring**](MinWindowSubstring.java)
   - Problem: Smallest substring containing all characters of pattern
   - Learning: Variable window with pattern matching

2. [**Substring with Concatenation**](SubstringConcatenation.java)
   - Problem: Find substrings containing concatenation of all words
   - Learning: Fixed window with word frequency

3. [**Longest Substring with At Most K Distinct**](LongestKDistinct.java)
   - Problem: Longest substring with at most k distinct characters
   - Learning: Variable window with distinct character constraint

4. [**Maximum Points from Cards**](MaximumCards.java)
   - Problem: Maximum points from taking cards from ends
   - Learning: Reverse sliding window technique

## Time Complexity Analysis
- Most sliding window solutions run in O(n) time complexity
- Space complexity typically O(1) for fixed window size
- Space complexity can be O(k) for problems tracking k distinct elements

## Common Mistakes to Avoid
1. Not handling edge cases (empty array/string)
2. Incorrect window size calculations
3. Forgetting to update window state when sliding
4. Not resetting window state when needed
5. Incorrect handling of window boundaries

## Tips for Solving Sliding Window Problems
1. Identify if the problem fits sliding window pattern
2. Determine if window size is fixed or variable
3. Choose appropriate data structure for window state
4. Handle edge cases first
5. Test with various input sizes and patterns