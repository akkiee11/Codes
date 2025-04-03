/**
 * Allocate Books
 * Problem: Given an array of integers A of size N and an integer B.
 * The books are arranged in ascending order of number of pages.
 * Every student is assigned to read some consecutive books.
 * The task is to assign books in such a way that the maximum number of pages assigned to a student is minimum.
 *
 * Example:
 * Input: A = [12, 34, 67, 90], B = 2
 * Output: 113
 */
public class AllocateBooks {
    public int allocateBooks(int[] A, int B) {
        if (A == null || A.length < B) return -1;
        int low = A[0];
        int high = 0;
        for (int pages : A) {
            low = Math.max(low, pages);
            high += pages;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isPossible(A, B, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isPossible(int[] A, int B, int maxPages) {
        int studentsRequired = 1;
        int currentPages = 0;
        for (int pages : A) {
            if (currentPages + pages > maxPages) {
                studentsRequired++;
                currentPages = pages;
                if (studentsRequired > B) return false;
            } else {
                currentPages += pages;
            }
        }
        return true;
    }

    // Test cases
    public static void main(String[] args) {
        AllocateBooks solution = new AllocateBooks();
        int[] A1 = {12, 34, 67, 90};
        System.out.println(solution.allocateBooks(A1, 2)); // Expected: 113

        int[] A2 = {10, 20, 30, 40};
        System.out.println(solution.allocateBooks(A2, 2)); // Expected: 60
    }
}