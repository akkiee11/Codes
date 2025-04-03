# Prefix Sum Pattern

This folder contains implementations of common prefix sum problems with detailed explanations, test cases, and approaches.

## Problems

### 1. Range Sum Query
**Problem Statement:**
Given an integer array nums, handle multiple queries of the following type:
- Calculate the sum of the elements of nums between indices left and right inclusive.

**Approach:**
- Precompute the prefix sum array where each element at index i is the sum of all elements from index 0 to i.
- For each query, the sum can be calculated as prefix[right] - prefix[left - 1].

**Time Complexity:**
- O(n) for preprocessing, O(1) per query.

**Space Complexity:**
- O(n) for the prefix sum array.

**Test Cases:**
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```
Input: nums = [1, 2, 3, 4, 5], queries = [[1, 3], [0, 4]]
Output: [9, 15]
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```

### 2. Subarray Sum Equals K
**Problem Statement:**
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

**Approach:**
- Use a hash map to store the frequency of prefix sums.
- Iterate through the array, computing the prefix sum and checking if (prefix_sum - k) exists in the hash map.

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [1, 1, 1], k = 2
Output: 2
Explanation: The subarrays [1, 1] and [1, 1] have sum 2.
```
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```
Input: nums = [1, 1, 1], k = 2
Output: 2
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```

### 3. Minimum Size Subarray Sum
**Problem Statement:**
Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray of which the sum is greater than or equal to target.

**Approach:**
- Use sliding window with prefix sum
- Expand window until sum >= target, then shrink from left

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length of 2 with sum >= 7.
```
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```
Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```

### 4. Product of Array Except Self
**Problem Statement:**
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

**Approach:**
- Use two prefix product arrays: one from the left and one from the right.
- The result for each index is the product of the left and right prefix products at that index.

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [1, 2, 3, 4]
Output: [24, 12, 8, 6]
Explanation: The products except self are [2*3*4, 1*3*4, 1*2*4, 1*2*3].
```
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```
Input: nums = [1, 2, 3, 4]
Output: [24, 12, 8, 6]
```

### 5. Maximum Subarray
**Problem Statement:**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

**Approach:**
- Use Kadane's algorithm with prefix sum concept
- Track maximum sum ending at each position

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(1)

**Test Cases:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

### 6. Continuous Subarray Sum
**Problem Statement:**
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

**Approach:**
- Use prefix sum with modulo operation
- Store remainders in hash map

**Time Complexity:**
- O(n)

**Space Complexity:**
- O(n)

**Test Cases:**
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2,4] is a continuous subarray of size 2 whose elements sum up to 6
```

### 7. Split Array with Same Average
**Problem Statement:**
Given an integer array nums, return true if it is possible to split nums into two subsets such that the average of the elements in both subsets is equal.

**Approach:**
- Calculate total sum and use prefix sum to check possible splits
- Apply dynamic programming approach

**Time Complexity:**
- O(n * sum)

**Space Complexity:**
- O(n * sum)

**Test Cases:**
```
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], both with average 4.5
```