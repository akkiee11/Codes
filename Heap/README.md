# Heap Pattern

This section covers problems that utilize the Heap data structure. A heap is a specialized tree-based data structure that satisfies the heap property:
- In a max heap, for any given node C, if P is a parent node of C, then the key (value) of P is greater than or equal to the key of C.
- In a min heap, for any given node C, if P is a parent node of C, then the key (value) of P is less than or equal to the key of C.

## Problems by Difficulty

### Easy

#### 1. Last Stone Weight
- **Problem**: Find the weight of the last remaining stone after smashing together stones of different weights
- **Key Concepts**: Max Heap
- **Time Complexity**: O(N * log(N))
- **Applications**: Simulation problems

#### 2. Kth Largest Element in a Stream
- **Problem**: Design a class to find the kth largest element in a stream of numbers
- **Key Concepts**: Min Heap
- **Time Complexity**: O(log k) per addition
- **Applications**: Stream processing

### Medium

#### 1. K Closest Points to Origin
- **Problem**: Find the K closest points to the origin (0, 0) in a 2D plane
- **Key Concepts**: Min Heap, Euclidean Distance
- **Time Complexity**: O(N * log(K))
- **Applications**: Spatial data processing, Nearest neighbor problems

#### 2. Top K Frequent Elements
- **Problem**: Find the k most frequent elements in an array
- **Key Concepts**: Max Heap, Frequency Counter
- **Time Complexity**: O(N * log(k))
- **Applications**: Frequency analysis, Top-K problems

#### 3. K Pairs with Smallest Sums
- **Problem**: Find k pairs with smallest sums from two arrays
- **Key Concepts**: Min Heap, Two Arrays
- **Time Complexity**: O(k * log(k))
- **Applications**: Array combinations, K-way merge

#### 4. Task Scheduler
- **Problem**: Arrange tasks with cooldown period to minimize idle time
- **Key Concepts**: Max Heap, Greedy Algorithm
- **Time Complexity**: O(N * log(N))
- **Applications**: Process scheduling, Resource management

#### 5. Reorganize String
- **Problem**: Reorganize string so no adjacent characters are same
- **Key Concepts**: Max Heap, Character Frequency
- **Time Complexity**: O(N * log(26))
- **Applications**: String manipulation, Character rearrangement

### Hard

#### 1. Merge K Sorted Lists
- **Problem**: Merge k sorted linked lists into one sorted linked list
- **Key Concepts**: Min Heap, Priority Queue
- **Time Complexity**: O(N * log(k))
- **Applications**: Merging multiple sorted sequences

#### 2. Find Median from Data Stream
- **Problem**: Design a data structure that can find the median of a stream of numbers
- **Key Concepts**: Two Heaps (Max and Min), Balanced Heaps
- **Time Complexity**: O(log n) for insertion, O(1) for finding median
- **Applications**: Real-time median tracking, Statistics

#### 3. Kth Smallest Element in a Sorted Matrix
- **Problem**: Find the kth smallest element in a sorted matrix
- **Key Concepts**: Min Heap, Matrix Traversal
- **Time Complexity**: O(K * log(N))
- **Applications**: Matrix operations, K-way merge

## Common Heap Operations

1. **Insertion**: O(log n)
2. **Deletion**: O(log n)
3. **Peek (Get Top)**: O(1)
4. **Heapify**: O(n)

## When to Use Heap?

1. When you need to repeatedly find the minimum/maximum element
2. When you need to maintain a sorted sequence with frequent insertions
3. For problems involving the top/bottom K elements
4. When you need to efficiently maintain a median
5. For priority-based scheduling or processing

## Implementation Tips

1. In Java, use `PriorityQueue` for heap implementation
2. For custom objects, implement `Comparable` or provide a `Comparator`
3. Consider using a max heap for smaller elements and min heap for larger elements in median finding
4. Always check if the heap is empty before peek/poll operations
5. For top-K problems, use a min heap of size K for largest elements, or max heap for smallest elements