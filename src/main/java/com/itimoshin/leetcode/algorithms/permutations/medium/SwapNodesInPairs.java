package com.itimoshin.leetcode.algorithms.permutations.medium;

/**
 * 24. Swap Nodes in Pairs
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1]
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */

public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode result = new SwapNodesInPairs().swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
        System.out.println();
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0, new ListNode(0, head));
        ListNode delayed = dummyHead;
        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            if (i % 2 == 1) {
                ListNode prev = delayed.next;
                delayed.next.next = cur.next;
                delayed.next = cur;
                cur.next = prev;
                cur = cur.next.next;
            } else {
                cur = cur.next;
            }
            delayed = delayed.next;
            i++;
        }
        return dummyHead.next.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
