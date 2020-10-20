package com.itimoshin.leetcode.algorithms.permutations.medium;

/**
 * 33. Search in Rotated Sorted Array
 * Medium
 * <p>
 * You are given an integer array nums sorted in ascending order, and an integer target.
 * <p>
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * If target is found in the array return its index, otherwise, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * All values of nums are unique.
 * nums is guranteed to be rotated at some pivot.
 * -10^4 <= target <= 10^4
 */

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int result = new SearchInRotatedSortedArray().search(new int[]{5, 1, 3}, 1);
        System.out.println(result);
    }

    // 10,11,12,  4,6,8,9

    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        boolean iterateForward = nums[0] <= target;
        for (int i = 0; i < nums.length; i++) {
            int cur = iterateForward ? nums[i] : nums[nums.length - 1 - i];
            if (cur == target) {
                return iterateForward ? i : nums.length -1 - i;
            }
            if (i > 0) {
                int prev = iterateForward ? nums[i - 1] : nums[nums.length - i];
                if (iterateForward && prev > cur || !iterateForward && prev < cur) {
                    return -1;
                }
            }

        }
        return -1;
    }
}
