package com.itimoshin.leetcode.algorithms.permutations.medium;

/**
 * 825. Friends Of Appropriate Ages
 *
 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.
 *
 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
 *
 * age[B] <= 0.5 * age[A] + 7
 * age[B] > age[A]
 * age[B] > 100 && age[A] < 100
 * Otherwise, A will friend request B.
 *
 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
 *
 * How many total friend requests are made?
 *
 * Example 1:
 *
 * Input: [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * Example 2:
 *
 * Input: [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * Example 3:
 *
 * Input: [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 *
 * Notes:
 *
 * 1 <= ages.length <= 20000.
 * 1 <= ages[i] <= 120.
 */

public class FriendsOfAppropriateAges {

    public static void main(String[] args) {
        int result = new FriendsOfAppropriateAges().numFriendRequests(new int[] {16, 16});
        System.out.println();
    }

    public int numFriendRequests(int[] ages) {
        int[] agesCount = new int[120];
        for (int i = 0; i < ages.length; i++) {
            agesCount[ages[i] - 1]++;
        }

        int result = 0;
        for (int i = 0; i < agesCount.length; i++) {
            int aCount = agesCount[i];
            for (int j = 0; j < agesCount.length; j++) {
                int bCount = agesCount[j];
                if (checkARequestsB(i + 1, j + 1)) {
                    result += agesCount[i] * agesCount[j];
                    if( i == j) {
                        result -= agesCount[i];
                    }
                }

            }
        }
        return result;
    }

    private boolean checkARequestsB(int a, int b) {
        return !((b <= 0.5 * a + 7) || (b > a));
    }
}
