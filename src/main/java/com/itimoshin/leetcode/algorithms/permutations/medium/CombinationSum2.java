package com.itimoshin.leetcode.algorithms.permutations.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */


public class CombinationSum2 {

    public static void main(String[] args) {
        List<List<Integer>> result = new CombinationSum2().combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationRecursive(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void combinationRecursive(int[] candidates, int curIndex, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = curIndex; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                if (i == curIndex || candidates[i] != candidates[i - 1]) {
                    current.add(candidates[i]);
                    combinationRecursive(candidates, i + 1, target - candidates[i], current, result);
                    current.remove(Integer.valueOf(candidates[i]));
                }
            }
        }
    }

}
