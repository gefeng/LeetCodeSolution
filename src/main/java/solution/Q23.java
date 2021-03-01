package solution;

import annotations.Problem;
import data_structure.ListNode;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;

@Problem(
        title = "Merge k Sorted Lists",
        difficulty = QDifficulty.HARD,
        tag = QTag.LINKED_LIST,
        url = "https://leetcode.com/problems/merge-k-sorted-lists/"
)
public class Q23 {
    ListNode mergeKLists(ListNode[] lists) {
        return mergeOneByOne(lists);
    }

    // max length of list is m
    // n list in lists
    // time complexity: 2m + 3m + 4m + ... + nm = O(mn^2)
    private ListNode mergeOneByOne(ListNode[] lists) {
        ListNode head = null;
        for(int i = 0; i < lists.length; i++) {
            head = mergeTwoLists(head, lists[i]);
        }

        return head;
    }

    private ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while(p1 != null || p2 != null) {
            if(p1 == null) {
                p.next = p2;
                p2 = p2.next;
            } else if(p2 == null) {
                p.next = p1;
                p1 = p1.next;
            } else {
                if(p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
            }
            p = p.next;
        }
        return dummyHead.next;
    }

    // heap (priority queue)
    private ListNode mergeAll(ListNode[] lists) {
        PriorityQueue<ListNode> pQueue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        // O(nlogn) build heap
        for(ListNode node : lists) {
            if(node != null)
                pQueue.offer(node);
        }

        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;

        // remove smallest from queue O(1)
        // add next to queue O(logn)
        // n * m operations
        // time complexity : O(mnlogn) better than O(mn^2)
        while(!pQueue.isEmpty()) {
            ListNode minNode = pQueue.poll();
            if(minNode.next != null)
                pQueue.offer(minNode.next);
            p.next = minNode;
            p = p.next;
        }

        return dummyHead.next;
    }
}
