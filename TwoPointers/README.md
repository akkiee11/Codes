# Two Pointers Pattern Problems

This repository contains a collection of problems that can be solved using the Two Pointers pattern. The problems are organized by difficulty level to help you progressively master this important algorithmic technique.

## What is the Two Pointers Pattern?

The Two Pointers pattern is an algorithmic technique that uses two pointers to traverse an array or linked list. These pointers can move towards each other, in the same direction at different speeds, or follow other patterns depending on the problem requirements.

### Common Variations

1. **Fast and Slow Pointers (Floyd's Cycle Detection)**
   - Used for cycle detection in linked lists or arrays
   - One pointer moves twice as fast as the other
   - Applications: Finding cycles, middle elements, or duplicates

2. **Left and Right Pointers**
   - Start from opposite ends of an array
   - Move towards each other based on conditions
   - Applications: Two Sum, container problems, palindrome verification

3. **Multiple Pointers**
   - Use more than two pointers (e.g., Three Sum problem)
   - Track multiple positions simultaneously
   - Applications: Finding triplets, quadruplets

## Problems by Difficulty

### Easy
1. [**Valid Palindrome**](ValidPalindrome.java)
   - Problem: Check if a string is a valid palindrome
   - Learning: Basic two-pointer technique with string manipulation

2. [**Remove Element**](RemoveElement.java)
   - Problem: Remove all instances of a value from an array
   - Learning: In-place array modification with two pointers

3. [**Remove Duplicates from Sorted Array**](RemoveDuplicatesSorted.java)
   - Problem: Remove duplicates from a sorted array in-place
   - Learning: Handling duplicates with two pointers

4. [**Move Zeroes**](MoveZeroes.java)
   - Problem: Move all zeroes to the end while maintaining relative order
   - Learning: Array manipulation with two pointers

5. [**Intersection of Two Arrays**](IntersectionArrays.java)
   - Problem: Find common elements between two arrays
   - Learning: Using sorted arrays with two pointers

6. [**Reverse String**](ReverseString.java)
   - Problem: Reverse a string in-place
   - Learning: Basic two-pointer technique for string manipulation

7. [**Squares of Sorted Array**](SquaresSortedArray.java)
   - Problem: Square the elements of a sorted array and maintain sorting
   - Learning: Two-pointer technique with sorted array manipulation

8. [**Merge Sorted Arrays**](MergeSortedArrays.java)
   - Problem: Merge two sorted arrays into one
   - Learning: Two-pointer technique for merging sorted arrays

### Medium
1. [**Two Sum II (Sorted Array)**](TwoSumSorted.java)
   - Problem: Find two numbers that add up to target in sorted array
   - Learning: Efficient searching in sorted arrays

2. [**Three Sum**](ThreeSum.java)
   - Problem: Find triplets that sum to zero
   - Learning: Combining sorting with multiple pointers

3. [**Container With Most Water**](ContainerWithMostWater.java)
   - Problem: Find two lines that contain the most water
   - Learning: Optimizing area calculation with two pointers

4. [**Sort Colors**](SortColors.java)
   - Problem: Sort an array of 0s, 1s, and 2s in-place
   - Learning: Dutch National Flag algorithm

5. [**Linked List Cycle**](LinkedListCycle.java)
   - Problem: Detect cycle in a linked list
   - Learning: Floyd's Cycle Detection algorithm

6. [**Four Sum**](FourSum.java)
   - Problem: Find quadruplets that sum to target
   - Learning: Multiple pointer technique with sorting

7. [**Partition List**](PartitionList.java)
   - Problem: Partition linked list around a value
   - Learning: Two-pointer technique with linked list

8. [**Palindrome Linked List**](PalindromeLinkedList.java)
   - Problem: Check if linked list is palindrome
   - Learning: Fast/slow pointers with reversal

9. [**Longest Word by Deleting**](LongestWordDeleting.java)
   - Problem: Find longest word that can be formed by deleting characters
   - Learning: Two-pointer technique with string comparison

### Hard
1. [**Minimum Window Substring**](MinWindowSubstring.java)
   - Problem: Find smallest substring containing all characters
   - Learning: Sliding window with two pointers

2. [**Trapping Rain Water**](TrappingRainWater.java)
   - Problem: Calculate water trapped between buildings
   - Learning: Complex two-pointer technique with auxiliary calculations

3. [**Find the Duplicate Number**](FindDuplicateNumber.java)
   - Problem: Find duplicate in array using Floyd's algorithm
   - Learning: Advanced cycle detection in arrays

4. [**Circular Array Loop**](CircularArrayLoop.java)
   - Problem: Detect cycles in a circular array
   - Learning: Complex cycle detection with direction constraints

## Key Takeaways

1. **Pattern Recognition**
   - Identify when to use two pointers vs other techniques
   - Understand which variation of two pointers to apply

2. **Space Efficiency**
   - Most two-pointer solutions use O(1) extra space
   - In-place modifications when possible

3. **Time Complexity**
   - Usually achieves O(n) or O(n log n) time complexity
   - More efficient than brute force approaches

4. **Edge Cases**
   - Handle empty inputs, single elements
   - Consider boundary conditions
   - Manage duplicates appropriately

## Tips for Solving Two Pointer Problems

1. **Start Simple**
   - Begin with easier problems to understand basic patterns
   - Progress to more complex variations

2. **Visualize Movement**
   - Draw out how pointers move through the data structure
   - Understand convergence conditions

3. **Consider Direction**
   - Determine if pointers should move towards or away from each other
   - Handle cases where pointers move at different speeds

4. **Test Thoroughly**
   - Use various test cases including edge cases
   - Verify pointer movement and termination conditions