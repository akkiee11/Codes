# Binary Search Pattern

This section covers problems that utilize the Binary Search algorithm. Binary Search is an efficient algorithm for searching a sorted array by repeatedly dividing the search interval in half.

## Key Characteristics

- Input array must be sorted (in most cases)
- Reduces time complexity from O(n) to O(log n)
- Can be used on both arrays and search spaces
- Requires careful handling of boundary conditions

## Problems by Difficulty

### Easy

#### 1. Binary Search
- **Problem**: Search for a target value in a sorted array
- **Key Concepts**: Basic binary search
- **Time Complexity**: O(log n)
- **Applications**: Finding element in sorted array

#### 2. First Bad Version
- **Problem**: Find first bad version in a sequence of versions
- **Key Concepts**: Binary search with condition
- **Time Complexity**: O(log n)
- **Applications**: Version control, finding first occurrence

### Medium

#### 1. Search in Rotated Sorted Array
- **Problem**: Search in sorted array rotated at unknown pivot
- **Key Concepts**: Modified binary search, array rotation
- **Time Complexity**: O(log n)
- **Applications**: Rotated array operations

#### 2. Find First and Last Position
- **Problem**: Find first and last position of element in sorted array
- **Key Concepts**: Binary search boundaries
- **Time Complexity**: O(log n)
- **Applications**: Range queries

#### 3. Search in 2D Matrix
- **Problem**: Search in 2D matrix where each row and column is sorted
- **Key Concepts**: 2D binary search
- **Time Complexity**: O(log(m*n))
- **Applications**: Matrix searching

#### 4. Find Peak Element
- **Problem**: Find peak element in array
- **Key Concepts**: Binary search with comparison
- **Time Complexity**: O(log n)
- **Applications**: Local maxima finding

### Hard
#### 3. Split Array Largest Sum
- **Problem**: Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
- **Key Concepts**: Binary search, dynamic programming
- **Time Complexity**: O(n log n)
- **Applications**: Load balancing, resource allocation

#### 1. Median of Two Sorted Arrays
- **Problem**: Find median of two sorted arrays
- **Key Concepts**: Binary search with two arrays
- **Time Complexity**: O(log(min(m,n)))
- **Applications**: Statistical operations

#### 2. Split Array Largest Sum
- **Problem**: Assign books to students such that the maximum number of pages assigned is minimized
- **Key Concepts**: Binary search on answer
- **Time Complexity**: O(n log n)
- **Applications**: Load balancing

#### 2. Split Array Largest Sum
- **Problem**: Split array into m subarrays to minimize largest sum
- **Key Concepts**: Binary search on answer
- **Time Complexity**: O(n log(sum))
- **Applications**: Array partitioning

## Common Binary Search Templates

1. **Basic Binary Search**
```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (array[mid] == target) return mid;
    if (array[mid] < target) left = mid + 1;
    else right = mid - 1;
}
```

2. **Left Boundary Binary Search**
```java
while (left < right) {
    int mid = left + (right - left) / 2;
    if (array[mid] < target) left = mid + 1;
    else right = mid;
}
```

3. **Right Boundary Binary Search**
```java
while (left < right) {
    int mid = left + (right - left + 1) / 2;
    if (array[mid] > target) right = mid - 1;
    else left = mid;
}
```

## Implementation Tips

1. Always use `left + (right - left) / 2` instead of `(left + right) / 2` to prevent integer overflow
2. Be careful with boundary conditions (left <= right vs left < right)
3. Consider whether to use inclusive or exclusive bounds
4. Handle duplicates carefully
5. For rotated arrays, always identify which half is sorted

## When to Use Binary Search?

1. When searching in a sorted array
2. When the problem can be reduced to searching for a value
3. When the search space can be divided into two parts
4. When looking for a minimum/maximum value that satisfies certain conditions
5. When time complexity requirement is logarithmic