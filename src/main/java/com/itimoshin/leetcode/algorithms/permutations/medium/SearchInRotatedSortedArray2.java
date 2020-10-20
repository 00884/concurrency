package com.itimoshin.leetcode.algorithms.permutations.medium;

/**
 * 81. Search in Rotated Sorted Array II
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 */

public class SearchInRotatedSortedArray2 {

    public static void main(String[] args) {
        boolean result = new SearchInRotatedSortedArray2().search(new int[] {5,1,3}, 0);
        System.out.println();
    }

    public boolean search(int[] nums, int target) {
        if (nums.length ==0) return false;
        if (nums.length == 1) return nums[0] == target;

        boolean iterateForward = nums[0] <= target;
        for (int i = 0; i < nums.length; i++) {
            int cur = iterateForward ? nums[i] : nums[nums.length - 1 - i];
            if (cur == target) {
                return true;
            }
            if (i > 0) {
                int prev = iterateForward ? nums[i - 1] : nums[nums.length - i];
                if (iterateForward && prev > cur || !iterateForward && prev < cur) {
                    return false;
                }
            }

        }
        return false;
    }
}
