package com.itimoshin.leetcode.algorithms.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */

public class Permutations {

    public static void main(String[] args) {
        List<List<Integer>> result = new Permutations().permute(new int[]{1, 2, 3});
        System.out.println(result.size());
    }

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        generate(nums.length, Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return result;
    }

    private void generate(int k, List<Integer> array) {
        if (k == 1) {
            result.add(array);
            return;
        }
        generate(k-1, array);
        for (int i = 0; i < k-1; i++) {
            List<Integer> toBeSwapped = new ArrayList<>(array);
            Collections.swap(toBeSwapped, i, k-1);
            generate(k - 1, toBeSwapped);
        }
    }
}
