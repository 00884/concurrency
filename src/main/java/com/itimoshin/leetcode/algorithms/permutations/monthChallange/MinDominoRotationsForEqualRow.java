package com.itimoshin.leetcode.algorithms.permutations.monthChallange;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * <p>
 * We may rotate the ith domino, so that A[i] and B[i] swap values.
 * <p>
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * <p>
 * If it cannot be done, return -1.
 * <p>
 * Example 1: https://assets.leetcode.com/uploads/2019/03/08/domino.png
 * Input: A = [2,1,2,4,2,2],
 * B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 * <p>
 * Example 2:
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 * <p>
 * Constraints:
 * 2 <= A.length == B.length <= 2 * 104
 * 1 <= A[i], B[i] <= 6
 */

public class MinDominoRotationsForEqualRow {

    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 6;

    public static void main(String[] args) {
        new MinDominoRotationsForEqualRow().run(new int[] {1,2,1,1,1,2,2,2}, new int[] {2,1,2,2,2,2,2,2});
    }

    public int run(int[] A, int[] B) {
        int len = A.length;
        int currentMinRotation = -1;
        for (int dominoVal = MIN_VAL; dominoVal <= MAX_VAL; dominoVal++) {
            int rotationsForAEq = 0;
            int rotationsForBEq = 0;
            boolean cannotComputeForCurrentVal = false;
            for (int j = 0; j < len; j++) {
                if (A[j] == dominoVal || B[j] == dominoVal) {
                    if (A[j] == dominoVal) {
                        rotationsForAEq++;
                    }
                    if (B[j] == dominoVal) {
                        rotationsForBEq++;
                    }
                } else {
                    cannotComputeForCurrentVal = true;
                    break;
                }
            }
            if(!cannotComputeForCurrentVal) {
                rotationsForAEq = Math.min(Math.min(rotationsForAEq, len - rotationsForAEq), Math.min(rotationsForBEq, len - rotationsForBEq));
                currentMinRotation = currentMinRotation == -1 ? rotationsForAEq : Math.min(currentMinRotation, rotationsForAEq);
            }

        }

        return currentMinRotation;
    }
}
