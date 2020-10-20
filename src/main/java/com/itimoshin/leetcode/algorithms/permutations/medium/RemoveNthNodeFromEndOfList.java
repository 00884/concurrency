package com.itimoshin.leetcode.algorithms.permutations.medium;

/**
 * 19. Remove Nth Node From End of List
 * Medium
 * <p>
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * Follow up: Could you do this in one pass?
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */

public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode result = new RemoveNthNodeFromEndOfList().removeNthFromEnd(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
                5);
        System.out.println();
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        ListNode delayedPointer = head;
        int i = 0;
        while (current.next != null) {
            if(i >= n) delayedPointer = delayedPointer.next;
            current = current.next;
            i++;
        }

        // remove the very first element
        if (i == n -1) {
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        } else {
            delayedPointer.next = delayedPointer.next.next;
            return head;
        }
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
    }
}
